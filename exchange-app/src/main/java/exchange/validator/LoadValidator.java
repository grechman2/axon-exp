package exchange.validator;

public interface LoadValidator {

    void verifyOwnerHasNoPostedLoadsYet(String owner);
}
