package org.grechman.axonexp.ui.api;

import org.axonframework.queryhandling.SubscriptionQueryResult;
import org.grechman.axonexp.query.CardSummary;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.springframework.http.codec.ServerSentEvent;
import reactor.core.publisher.Flux;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.Collection;
import java.util.List;

public interface ServerSentEventProducerSupport {
    Logger logger();

    /**
     * Turn {@link SubscriptionQueryResult}s into a flux of {@link ServerSentEvent}s of type T, with heartbeat
     * events merged in.
     *
     * @param queryResult subscription query results
     * @param seconds     heartbeat period; the length of the interval duration with which "ping" events are emitted.
     * @param <T>         the query model type.
     * @return flux of server sent events
     */
    default <T> Flux<ServerSentEvent<?>> toSSEFlux(
            HttpServletResponse response, SubscriptionQueryResult<T, T> queryResult, long seconds
    ) {

        return Flux.merge(toSSEFlux(response, queryResult), toSSEHeartbeatFlux(seconds))
                .doFinally(signalType -> logger().debug("SSE merged with heartbeat finalized; signalType: {}", signalType));
    }

    default <T> Flux<ServerSentEvent<T>> toSSEFlux(
            HttpServletResponse response, SubscriptionQueryResult<T, T> queryResult
    ){
        // refer to: https://serverfault.com/questions/801628/for-server-sent-events-sse-what-nginx-proxy-configuration-is-appropriate
        response.addHeader("Cache-Control", "no-cache");
        response.addHeader("X-Accel-Buffering", "no");

        return Flux.<T>create(emitter -> {
            queryResult.initialResult()
                    .doOnError(error -> logger().warn("Initial result error", error))
                    .doFinally(signalType -> logger().debug("Initial result finalized; signalType: {}"))
                    .subscribe(emitter::next);

            queryResult.updates().buffer(Duration.ofMillis(500)).map(modelList -> modelList.get(modelList.size() - 1))
                    .doOnError(error -> logger().warn("Updates error", error))
                    .doFinally(signalType -> logger().debug("Updates finalized; signalType: {}", signalType))
                    .doOnComplete(emitter::complete);
        }).doFinally( signalType -> logger().debug("SSE finalized; signalType:{}", signalType))
          .map(data -> ServerSentEvent.<T>builder().data(data).event("message").build());
    }


    default <T> Flux<ServerSentEvent<T>> toSSEFluxForInitialCollection(
            HttpServletResponse response, SubscriptionQueryResult<List<T>, T> queryResult
    ) {
        // refer to: https://serverfault.com/questions/801628/for-server-sent-events-sse-what-nginx-proxy-configuration-is-appropriate
        response.addHeader("Cache-Control", "no-cache");
        response.addHeader("X-Accel-Buffering", "no");

        return Flux.<T>create(emitter -> {
            queryResult.initialResult()
                    .doOnError(error -> logger().warn("Initial result error", error))
                    .doFinally(signalType -> logger().debug("Initial result finalized; signalType: {}"))
                    .flatMapMany(Flux::fromIterable)
                    .subscribe(items -> ((List<T>) items).forEach(emitter::next));

            queryResult.updates().buffer(Duration.ofMillis(500))
                    .map(modelList -> modelList.get(modelList.size() - 1))
                    .doOnError(error -> logger().warn("Updates error", error))
                    .doFinally(signalType -> logger().debug("Updates finalized; signalType: {}", signalType))
                    .doOnComplete(emitter::complete);
        }).doFinally(signalType -> logger().debug("SSE finalized; signalType:{}", signalType))
                .map(data ->
                        {
                            System.out.println(data);
                        return ServerSentEvent.<T>builder().data(data).event("message").build();
                    });
    }

    /**
     * Turn the given number of seconds into an infinite flux of "ping" events with an interval duration (period) equal to seconds.
     * @param seconds
     * @return
     */
    default Flux<ServerSentEvent<?>> toSSEHeartbeatFlux(long seconds) {
        return Flux.interval(Duration.ofSeconds(seconds))
                .doFinally(signalType -> logger().debug("Heartbeat finalized; signalType: {}", signalType))
                .map(i -> ServerSentEvent.builder().event("ping").build());
    }

}
