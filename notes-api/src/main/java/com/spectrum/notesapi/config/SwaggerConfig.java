package com.spectrum.notesapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Created by "Mohammad Shah Alam" on 13 Oct, 2020
 */

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false)
                .groupName("notes-api").select()
                .apis(RequestHandlerSelectors.basePackage("com.spectrum.notesapi.controller"))
                .build().apiInfo(apiInfo());
    }


    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("Swagger notes-api")
                .description("Api consumer guide can be found at https://chalk.charter.com/display/OSSNMS/Api+Consumer+Guide")
                .license("Charter internal use only").termsOfServiceUrl("https://chalk.charter.com/display/OSSNMS/NOTES+PROGRAM")
                .version("0.0.19")
                .contact(new Contact("NOTES Team",
                        "https://chalk.charter.com/display/OSSNMS/NOTES+PROGRAM",
                        "DL-OSS-CApps-NOTES-Support@charter.com"
                ))
                .build();
    }
}
