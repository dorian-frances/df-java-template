package io.df.java.template.infrastructure.postgres.configuration;

import io.df.java.template.infrastructure.postgres.adapter.PostgresUserAdapter;
import io.df.java.template.infrastructure.postgres.repository.UserRepository;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableJpaAuditing
@EntityScan(basePackages = {"io.df.java.template.infrastructure.postgres.entity"})
@EnableJpaRepositories(basePackages = {"io.df.java.template.infrastructure.postgres.repository"})
public class PostgresConfiguration {

    @Bean
    public PostgresUserAdapter postgresUserAdapter(final UserRepository userRepository) {
        return new PostgresUserAdapter(userRepository);
    }
}

