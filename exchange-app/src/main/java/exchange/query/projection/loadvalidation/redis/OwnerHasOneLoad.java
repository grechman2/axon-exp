package exchange.query.projection.loadvalidation.redis;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@RedisHash("LoadValidation")
@Data
@Builder
public class OwnerHasOneLoad implements Serializable {


    @Id
    private String owner;
    private String loadId;
    private String loadStatus;
}
