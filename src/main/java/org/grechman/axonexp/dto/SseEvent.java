package org.grechman.axonexp.dto;

import lombok.Data;

@Data
public class SseEvent<T> {
    String type;
    T payload;
}
