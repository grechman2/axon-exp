package org.grechman.axonexp.commands;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class RedeemCommand {

    @TargetAggregateIdentifier
    private String id;
    private Integer amount;
}
