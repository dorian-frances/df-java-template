package io.df.java.template.bootstrap;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import static reactor.netty.http.client.HttpClient.newConnection;

@EnableConfigurationProperties
@Slf4j
@AllArgsConstructor
@SpringBootApplication
public class DfJavaTemplateITApplication {
    public static void main(String[] args) {
        SpringApplication.run(DfJavaTemplateITApplication.class, args);
    }

    @Bean
    public WebClient webClientIT(final WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .clientConnector(new ReactorClientHttpConnector(newConnection().compress(true)))
                .build();
    }
}