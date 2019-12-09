package axonexp.web;

import axonexp.ui.api.Api;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.grechman.exchange.load.PostLoadCommand;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WebController {

    private final CommandGateway commandGateway;

    //ToDo add mapStruct and validations on controller
    @PostMapping(Api.Load.BASE_LOADS)
    public void postLoad(@RequestBody PostLoadCommand postLoadCommand) {
        commandGateway.sendAndWait(postLoadCommand);
    }
}
