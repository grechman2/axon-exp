package exchange.load;


import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate()
@RequiredArgsConstructor
public class Load {

    private LoadValidator loadValidator;

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
                .loadInfo(LoadInfo
                        .builder()
                        .loadStatus(LoadStatus.POSTED)
                        .from(command.getPostLoadDetails().getFrom())
                        .to(command.getPostLoadDetails().getTo())
                        .owner(command.getPostLoadDetails().getOwner())
                        .shouldBeDeliveredOn(command.getPostLoadDetails().getShouldBeDeliveredOn())
                        .price(command.getPostLoadDetails().getPrice())
                        .build())
                .build());
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

    private void verifyInitialLoadPost(PostLoadCommand postLoadCommand) {
        loadValidator.verifyOwnerHasNoPostedLoadsYet(postLoadCommand.getPostLoadDetails().getOwner());
        Objects.requireNonNull(postLoadCommand.getPostLoadDetails().getFrom(),
                "Load should have departure address (From)");
        Objects.requireNonNull(postLoadCommand.getPostLoadDetails().getTo(),
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

    public static class LoadId{
        private String id;

        public LoadId(String id) {
            this.id = id;
        }

        public LoadId() {
        }

        @Override
        public String toString() {
            if (Objects.isNull(id)) {
                return UUID.randomUUID().toString();
            }
            return id;
        }
    }
}
