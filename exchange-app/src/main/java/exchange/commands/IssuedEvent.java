package exchange.commands;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class IssuedEvent {
    private String id;
    private Integer amount;
}
