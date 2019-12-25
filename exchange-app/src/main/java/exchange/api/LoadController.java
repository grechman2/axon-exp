package exchange.api;

import exchange.dto.PostLoadDto;
import exchange.service.LoadService;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import exchange.load.PostLoadCommand;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoadController {

    private final LoadService loadService;

    //ToDo add mapStruct and validations on controller
    @PostMapping(Api.Load.BASE_LOADS)
    public void postLoad(@RequestBody PostLoadDto dto) {
        loadService.postLoad(dto);
    }
}
