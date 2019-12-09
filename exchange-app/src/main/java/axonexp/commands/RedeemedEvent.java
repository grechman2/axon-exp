package axonexp.commands;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class RedeemedEvent {
    private String id;
    private Integer amount;
}
