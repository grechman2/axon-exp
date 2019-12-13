package exchange.commands;

import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;

@Aggregate
@NoArgsConstructor
public class GiftCard {

    @AggregateIdentifier
    private String id;
    private Integer balance;

    @CommandHandler
    @Autowired(required = false)
    public GiftCard(IssueCommand cmd) {
        if (cmd.getAmount() <= 0) {
            throw new IllegalArgumentException("Amont <= 0");
        }

        AggregateLifecycle.apply(new IssuedEvent(cmd.getId(), cmd.getAmount()));
    }

    @CommandHandler
    public void handle(RedeemCommand cmd){
        if(cmd.getAmount()<=0){
            throw new IllegalArgumentException("Amount <= 0");
        }

        if(cmd.getAmount() > balance){
            throw new IllegalArgumentException("amount > balance");
        }

        AggregateLifecycle.apply(new RedeemedEvent(cmd.getId(), cmd.getAmount()));
    }

    @EventSourcingHandler
    public void on(IssuedEvent evt){
        id = evt.getId();
        balance = evt.getAmount();
    }

    @EventSourcingHandler
    public void on(RedeemedEvent evt){
        balance = balance - evt.getAmount();
    }


}
