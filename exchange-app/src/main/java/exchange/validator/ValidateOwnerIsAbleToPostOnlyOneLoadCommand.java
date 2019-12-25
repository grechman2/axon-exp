package exchange.validator;

import lombok.Value;

@Value
public class ValidateOwnerIsAbleToPostOnlyOneLoadCommand {
    private String owner;
}
