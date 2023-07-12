package io.df.java.template.bootstrap.configuration;

import io.df.java.template.application.rest.api.adapter.controller.UserController;
import io.df.java.template.domain.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestApiConfiguration {

    @Bean
    public UserController userController(final UserService userService) {
        return new UserController(userService);
    }
}
