package exchange.query.projection.loadvalidation.redis;

import exchange.load.LoadPostedEvent;
import exchange.query.projection.loadvalidation.DoesOwnerHasLoadJpaQuery;
import lombok.AllArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@ProcessingGroup("loadValidationRedisProjection")
public class OwnerHasOneLoadRedisProjection {

    private final OwnerHasOneLoadRedisRepository redisRepository;

    @EventHandler
    public void on(LoadPostedEvent loadPostedEvent) {
        redisRepository.save(OwnerHasOneLoad.builder()
                .loadId(loadPostedEvent.getAggregateIdentifier().toString())
                .loadStatus(loadPostedEvent.getLoadInfo().getLoadStatus().name())
                .owner(loadPostedEvent.getLoadInfo().getOwner())
                .build());
    }

    @QueryHandler
    public Boolean doesOwnerHasLoad(DoesOwnerHasLoadJpaQuery query) {
        return redisRepository.findById(query.getOwner()).isPresent();
    }
}
