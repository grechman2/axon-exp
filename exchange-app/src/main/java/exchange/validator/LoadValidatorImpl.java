package exchange.validator;

import exchange.query.projection.loadvalidation.DoesOwnerHasLoadJpaQuery;
import exchange.query.projection.loadvalidation.DoesOwnerHasLoadRedisQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class LoadValidatorImpl implements LoadValidator {

    private final QueryGateway queryGateway;

    @Override
    public void verifyOwnerHasNoPostedLoadsYet(String owner) {
        CompletableFuture<Boolean> resp =
                queryGateway.query(DoesOwnerHasLoadRedisQuery.builder().owner(owner).build(), Boolean.class);
        try {
            if(resp.get()) {
                throw new ValidationException("Owner not allowed to post more than one load");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
