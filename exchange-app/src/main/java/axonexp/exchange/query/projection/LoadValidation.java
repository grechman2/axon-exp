package axonexp.exchange.query.projection;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Builder
public class LoadValidation {

    @Id
    private Integer id;
    private String loadId;
    private String owner;
    private String loadStatus;
}
