package org.grechman.exchange.load;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.Date;

@Value
@Builder
public class LoadPostedEvent extends AbstractLoadEvent {
    private LoadInfo loadInfo;
}
