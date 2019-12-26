package exchange.load.events;

import exchange.load.LoadId;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public abstract class AbstractLoadEvent implements Serializable{
    protected LoadId aggregateIdentifier;
}
