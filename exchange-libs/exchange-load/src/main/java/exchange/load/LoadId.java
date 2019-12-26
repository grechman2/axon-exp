package exchange.load;

import java.io.Serializable;
import java.util.UUID;

public class LoadId implements Serializable {
    private String id;

    public LoadId(String id) {
        this.id = id;
    }

    public LoadId() {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return id;
    }
}

