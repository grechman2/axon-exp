package exchange.load.service;

import exchange.load.ValidateOwnerIsAbleToPostOnlyOneLoadCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Service;

@Service
public class LoadService {

    private final LoadRepositoryInt loadRepository;

    public LoadService(LoadRepositoryInt loadRepository) {
        this.loadRepository = loadRepository;
    }

    @CommandHandler
    public void validateExecutorHasOnlyOneLoad(ValidateOwnerIsAbleToPostOnlyOneLoadCommand command){
        if(loadRepository.isOwnerHasPostedLoadAlready(command.getOwner())){
            throw  new IllegalArgumentException("owner has already posted load");
        };

    }
}
