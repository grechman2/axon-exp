package org.grechman.axonexp.query;

import lombok.Data;

@Data
public class DataQuery {
    private Integer offset;
    private Integer limit;
}
