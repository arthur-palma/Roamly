package api.Roamly.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Roamly API")
                        .version("1.0")
                        .description("API para gerenciamento de viagens e participantes")
                        .contact(new Contact()
                                .name("Arthur Palma")
                                .email("arthur.p.palma@gmail.com")));
    }
}