package exchange.query.projection.loadvalidation.redis;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("LoadHasOneLoad")
@Data
@Builder
public class OwnerHasOneLoad implements Serializable {

    @Id
    private String owner;
    private String loadId;
    private String loadStatus;
}
