package axonexp.query;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
        @NamedQuery(name = "CardSummary.fetch",
            query = "SELECT c FROM CardSummary c"),
        @NamedQuery(name = "CardSummary.count",
            query = "SELECT COUNT(c) FROM CardSummary c WHERE c.id LIKE CONCAT(:idStartsWith, '%')"
        )
})
@ToString
@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class CardSummary {

    @EqualsAndHashCode.Include
    @Id
    private String id;
    private Integer initialBalance = 0;
    private Integer remainingBalance = 0;
}
