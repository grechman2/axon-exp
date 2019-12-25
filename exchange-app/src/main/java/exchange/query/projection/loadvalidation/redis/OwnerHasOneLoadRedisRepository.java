package exchange.query.projection.loadvalidation.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerHasOneLoadRedisRepository extends CrudRepository<OwnerHasOneLoad, String> {
}
