package exchange.load.events;

import exchange.load.Load;
import lombok.Getter;
import org.axonframework.serialization.Revision;

@Getter
@Revision("1")
public class LoadDestinationChangedEvent extends AbstractLoadEvent{
    private String newDestination;

    public LoadDestinationChangedEvent(Load.LoadId aggregateIdentifier, String newDestination) {
        super(aggregateIdentifier);
        newDestination = newDestination;
    }
}
