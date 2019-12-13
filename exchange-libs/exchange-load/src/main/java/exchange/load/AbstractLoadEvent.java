package exchange.load;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class AbstractLoadEvent {
    private Load.LoadId aggregateIdentifier;
}
