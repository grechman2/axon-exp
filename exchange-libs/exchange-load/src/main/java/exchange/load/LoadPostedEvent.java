package exchange.load;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
public class LoadPostedEvent extends AbstractLoadEvent {
    private LoadInfo loadInfo;

    @Builder
    public LoadPostedEvent(Load.LoadId aggregateIdentifier, LoadInfo loadInfo) {
        super(aggregateIdentifier);
        this.loadInfo = loadInfo;
    }
}
