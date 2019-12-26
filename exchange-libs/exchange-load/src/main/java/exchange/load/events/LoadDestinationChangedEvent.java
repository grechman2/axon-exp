package exchange.load.events;

import exchange.load.LoadId;
import lombok.Getter;
import org.axonframework.serialization.Revision;

@Getter
@Revision("1")
public class LoadDestinationChangedEvent extends AbstractLoadEvent {
    private String newDestination;

    public LoadDestinationChangedEvent(LoadId aggregateIdentifier, String newDestination) {
        super(aggregateIdentifier);
        this.newDestination = newDestination;
    }
}
