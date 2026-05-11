package pharmacy.pharmacyservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient insuranceWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl("http://insurance-service")
                .build();
    }
}
