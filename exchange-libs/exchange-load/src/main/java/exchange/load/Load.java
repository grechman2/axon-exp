package exchange.load;


import exchange.load.commands.ChangeLoadDestinationCommand;
import exchange.load.commands.PostLoadCommand;
import exchange.load.events.LoadDestinationChangedEvent;
import exchange.load.events.LoadPostedEvent;
import lombok.Builder;
import lombok.Value;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import javax.validation.ValidationException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate()
public class Load {

    @AggregateIdentifier
    private LoadId loadId;
    private LoadStatus loadStatus;
    private String from;
    private String to;
    private Date shouldBeDeliveredOn;
    private String owner;
    private Order order;



    /**
     * This constructor is marked as a 'CommandHandler' for the [PostLoadCommand].
     * This command can be used to construct new instances of the Aggregate. If
     * successful a new [LoadPostedEvent] is 'applied' to the aggregate using the
     * Axon 'apply' method. The apply method appears to also propagate the Event to
     * any other registered 'Event Listeners', who may take further action.
     */
    @CommandHandler
    public Load(PostLoadCommand command) {
        verifyInitialLoadPost(command);
        apply(LoadPostedEvent.builder()
                .aggregateIdentifier(command.loadId)
                .loadInfo(LoadInfo2
                        .builder()
                        .loadStatus(LoadStatus.POSTED)
                        .from(command.getFrom())
                        .to(command.getTo())
                        .owner(command.getOwner())
                        .shouldBeDeliveredOn(command.getShouldBeDeliveredOn())
                        .price(command.getPrice())
                        .build())
                .build());
    }

    public Load() {
    }

    @CommandHandler
    public void changeLoadDestination(ChangeLoadDestinationCommand command){
        verifyChangeLoadDestination(command);
        apply(new LoadDestinationChangedEvent(command.getLoadId(), command.getTo()));
    }

    private void verifyChangeLoadDestination(ChangeLoadDestinationCommand command) {
        if(!LoadStatus.POSTED.equals(this.loadStatus)){
            throw new ValidationException("You can't change destination for load not in POSTED status!");
        }
    }


    @EventSourcingHandler
    public void on(LoadPostedEvent loadPostedEvent) {
        this.loadId = loadPostedEvent.getAggregateIdentifier();
        this.from = loadPostedEvent.getLoadInfo().getFrom();
        this.to = loadPostedEvent.getLoadInfo().getTo();
        this.loadStatus = loadPostedEvent.getLoadInfo().getLoadStatus();
        this.owner = loadPostedEvent.getLoadInfo().getOwner();
        this.shouldBeDeliveredOn = loadPostedEvent.getLoadInfo().getShouldBeDeliveredOn();
        this.order = Order
                .builder()
                .price(loadPostedEvent.getLoadInfo().getPrice())
                .build();
    }

    @EventSourcingHandler
    public void on(LoadDestinationChangedEvent event){
        this.to = event.getTo();
    }

    private void verifyInitialLoadPost(PostLoadCommand postLoadCommand) {
        Objects.requireNonNull(postLoadCommand.getFrom(),
                "Load should have departure address (From)");
        Objects.requireNonNull(postLoadCommand.getTo(),
                "Load should have destination address (To)");
    }

    @Value
    @Builder
    static class Order {
        private BigDecimal price;
        private String executor;
        private Date allocationDate;
        private Date deliveredDate;
        private Date completionDate;
    }


}
