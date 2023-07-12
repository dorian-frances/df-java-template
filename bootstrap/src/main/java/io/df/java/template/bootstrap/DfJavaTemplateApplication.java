package io.df.java.template.bootstrap;

import io.df.java.template.bootstrap.configuration.DfJavaTemplateConfiguration;
import io.df.java.template.bootstrap.configuration.DomainConfiguration;
import io.df.java.template.bootstrap.configuration.RestApiConfiguration;
import io.df.java.template.infrastructure.postgres.configuration.PostgresConfiguration;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableConfigurationProperties
@Import(value = {DfJavaTemplateConfiguration.class, DomainConfiguration.class, PostgresConfiguration.class, RestApiConfiguration.class})
@Slf4j
@EnableAsync
@AllArgsConstructor
public class DfJavaTemplateApplication {
    public static void main(String[] args) {
        SpringApplication.run(DfJavaTemplateApplication.class, args);
    }
}

