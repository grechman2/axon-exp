package exchange.load;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class AbstractLoadCommand {

    @TargetAggregateIdentifier
    protected Load.LoadId loadId;

    public Load.LoadId getLoadId() {
        return loadId;
    }

    public AbstractLoadCommand setLoadId(Load.LoadId loadId) {
        this.loadId = loadId;
        return this;
    }

    public AbstractLoadCommand(Load.LoadId loadId) {
        this.loadId = loadId;
    }
}
