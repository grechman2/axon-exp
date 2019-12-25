package exchange.service;

import exchange.dto.ChangeLoadDestinationDto;
import exchange.dto.PostLoadDto;
import exchange.load.commands.ChangeLoadDestinationCommand;
import exchange.load.commands.PostLoadCommand;
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

    public void changeLoadDestination(String loadId, ChangeLoadDestinationDto dto){
        commandGateway.sendAndWait(new ChangeLoadDestinationCommand(
                loadId,
                dto.getTo()));
    }


}
