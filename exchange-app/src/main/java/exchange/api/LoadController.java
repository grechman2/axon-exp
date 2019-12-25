package exchange.api;

import exchange.dto.ChangeLoadDestinationDto;
import exchange.dto.PostLoadDto;
import exchange.service.LoadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping(Api.Load.CHANGE_LOAD_DESTINATION)
    public void postLoad(
            @PathVariable("id") String loadId,
            @RequestBody ChangeLoadDestinationDto dto) {
        loadService.changeLoadDestination(loadId, dto);
    }

}
