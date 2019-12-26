package exchange.load.commands;

import exchange.load.LoadId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class AbstractLoadCommand {

    @TargetAggregateIdentifier
    public LoadId loadId;

    public LoadId getLoadId() {
        return loadId;
    }

    public AbstractLoadCommand setLoadId(LoadId loadId) {
        this.loadId = loadId;
        return this;
    }

    public AbstractLoadCommand(LoadId loadId) {
        this.loadId = loadId;
    }
}
