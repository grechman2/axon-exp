package exchange.load.events;

import exchange.load.LoadId;
import lombok.Getter;
import org.axonframework.serialization.Revision;

@Getter
@Revision("2")
public class LoadDestinationChangedEvent extends AbstractLoadEvent {
    private String to;

    public LoadDestinationChangedEvent(LoadId aggregateIdentifier, String to) {
        super(aggregateIdentifier);
        this.to = to;
    }
}
