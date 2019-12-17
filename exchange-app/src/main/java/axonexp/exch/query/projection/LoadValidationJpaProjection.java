package axonexp.exch.query.projection;

import exchange.load.LoadPostedEvent;
import lombok.AllArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.data.annotation.Persistent;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Service
@AllArgsConstructor
@ProcessingGroup("loadValidationProjection")
public class LoadValidationJpaProjection {

    private final QueryGateway queryGateway;

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
    public Boolean doesOwnerHasLoad(DoesOwnerHasLoadQuery query){
            TypedQuery<Integer> q = entityManager.createQuery("SELECT COUNT(l) from LoadValidation l WHERE l.owner = :owner", Integer.class);
            q.setParameter("owner", query.getOwner());
            return q.getSingleResult().intValue() > 1;
    }
}
