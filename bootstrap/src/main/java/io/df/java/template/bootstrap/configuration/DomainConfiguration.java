package io.df.java.template.bootstrap.configuration;

import io.df.java.template.domain.service.UserService;
import io.df.java.template.infrastructure.postgres.adapter.PostgresUserAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

    @Bean
    public UserService userService(final PostgresUserAdapter postgresUserAdapter) {
        return new UserService(postgresUserAdapter);
    }
}
