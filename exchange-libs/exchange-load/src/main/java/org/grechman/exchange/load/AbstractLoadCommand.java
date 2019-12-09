package org.grechman.exchange.load;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class AbstractLoadCommand {

    @TargetAggregateIdentifier
    Load.LoadId loadId;

    public AbstractLoadCommand(Load.LoadId loadId) {
        this.loadId = loadId;
    }
}
