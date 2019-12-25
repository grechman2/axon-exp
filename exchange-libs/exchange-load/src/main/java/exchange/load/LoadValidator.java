package exchange.load;

public interface LoadValidator {

    void verifyOwnerHasNoPostedLoadsYet(String owner);
}
