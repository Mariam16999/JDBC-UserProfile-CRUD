package com.intercom.userprofile.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition()
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenApi()
    {
        return new OpenAPI().info(new Info().title("userProfile").description("user/Citizen Info").version("v1.0.0"));


    }
}
