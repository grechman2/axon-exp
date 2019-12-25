package exchange.load.events;

import exchange.load.Load;
import exchange.load.LoadInfo2;
import exchange.load.events.AbstractLoadEvent;
import lombok.Builder;
import lombok.Getter;
import org.axonframework.serialization.Revision;

@Getter
@Revision("2")
public class LoadPostedEvent extends AbstractLoadEvent {
    private LoadInfo2 loadInfo;

    @Builder
    public LoadPostedEvent(Load.LoadId aggregateIdentifier, LoadInfo2 loadInfo) {
        super(aggregateIdentifier);
        this.loadInfo = loadInfo;
    }
}
