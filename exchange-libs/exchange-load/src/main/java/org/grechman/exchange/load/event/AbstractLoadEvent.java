package org.grechman.exchange.load.event;


import exchange.load.Load;

public abstract class AbstractLoadEvent {
    protected Load.LoadId aggregateIdentifier;

    public Load.LoadId getAggregateIdentifier() {
        return aggregateIdentifier;
    }
}
