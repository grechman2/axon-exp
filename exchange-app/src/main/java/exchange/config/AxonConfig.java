package exchange.config;

import exchange.upcaster.LoadDestinationChangeEvent1To2;
import org.axonframework.serialization.upcasting.event.SingleEventUpcaster;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Bean
    public SingleEventUpcaster LoadDestinationChangeEvent1To2(){
        return new LoadDestinationChangeEvent1To2();
    }
}
