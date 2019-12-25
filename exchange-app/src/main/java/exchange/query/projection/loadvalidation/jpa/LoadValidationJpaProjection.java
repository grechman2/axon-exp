package exchange.query.projection.loadvalidation.jpa;

import exchange.load.events.LoadPostedEvent;
import exchange.query.projection.loadvalidation.DoesOwnerHasLoadJpaQuery;
import lombok.AllArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.data.annotation.Persistent;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Service
@AllArgsConstructor
@ProcessingGroup("loadValidationProjection2")
public class LoadValidationJpaProjection {

    @Persistent
    EntityManager entityManager;

    @EventHandler
    public void on(LoadPostedEvent loadPostedEvent){
        entityManager.persist(LoadValidation.builder()
                .loadId(loadPostedEvent.getAggregateIdentifier().toString())
                .loadStatus(loadPostedEvent.getLoadInfo().getLoadStatus().name())
                .owner(loadPostedEvent.getLoadInfo().getOwner())
                .build());
    };

    @QueryHandler
    public Boolean doesOwnerHasLoad(DoesOwnerHasLoadJpaQuery query){
            TypedQuery<Integer> q = entityManager.createQuery("SELECT COUNT(l) from LoadValidation l WHERE l.owner = :owner", Integer.class);
            q.setParameter("owner", query.getOwner());
            return q.getSingleResult().intValue() > 1;
    }
}
