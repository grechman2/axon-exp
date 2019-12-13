package exchange.query;


import exchange.commands.IssuedEvent;
import exchange.commands.RedeemedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class CardSummaryProjection {

    private final EntityManager entityManager;
    private final QueryUpdateEmitter queryUpdateEmitter;
    private final SimpMessageSendingOperations simpMessageSendingOperations;

    @EventHandler
    public void on(IssuedEvent event) {
        log.trace("projecting {}", event);
        entityManager.persist(new CardSummary(event.getId(), event.getAmount(), event.getAmount()));
        broadcastUpdates();
    }

    @EventHandler
    public void on(RedeemedEvent event) {
        log.trace("projecting {}", event);
        CardSummary summary = entityManager.find(CardSummary.class, event.getId());
        summary.setRemainingBalance(summary.getRemainingBalance() - event.getAmount());
        broadcastUpdates();
    }

    @QueryHandler
    public List<CardSummary> handle(FetchCardSummariesQuery query) {
        log.trace("handling {}", query);
        TypedQuery<CardSummary> jpaQuery = entityManager.createNamedQuery("CardSummary.fetch", CardSummary.class);
//        jpaQuery.setParameter("idStartsWith", query.getFilter().getIdStartsWith());
        jpaQuery.setFirstResult(query.getOffSet());
        jpaQuery.setMaxResults(query.getLimit());
        return jpaQuery.getResultList();
    }

    @QueryHandler
    public Integer handle(CountCardSummariesQuery query) {
        log.trace("handling {}", query);
        TypedQuery<Long> jpaQuery = entityManager.createNamedQuery("CardSummary.count", Long.class);
        jpaQuery.setParameter("idStartsWith", query.getFilter().getIdStartsWith());
        return jpaQuery.getSingleResult().intValue();
    }

    private void broadcastUpdates(){
        simpMessageSendingOperations.convertAndSend("/topic/cardsummary.updates", Arrays.asList());
    }
}
