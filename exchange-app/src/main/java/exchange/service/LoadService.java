package exchange.service;

import exchange.dto.PostLoadDto;
import exchange.load.PostLoadCommand;
import exchange.validator.LoadValidator;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoadService {

    private final LoadValidator loadValidator;
    private final CommandGateway commandGateway;

    public void postLoad(PostLoadDto dto){
        loadValidator.verifyOwnerHasNoPostedLoadsYet(dto.getOwner());
        commandGateway.sendAndWait(new PostLoadCommand(
                dto.getOwner(),
                dto.getFrom(),
                dto.getTo(),
                dto.getShouldBeDeliveredOn(),
                dto.getPrice()));
    }



}
