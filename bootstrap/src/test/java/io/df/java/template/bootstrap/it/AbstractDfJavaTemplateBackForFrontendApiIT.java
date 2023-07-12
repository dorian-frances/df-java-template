package io.df.java.template.bootstrap.it;

import com.github.javafaker.Faker;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import io.df.java.template.bootstrap.DfJavaTemplateITApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.util.UriComponentsBuilder;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.util.Map;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ActiveProfiles({"it", "front-api"})
@AutoConfigureWebTestClient(timeout = "36000")
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = DfJavaTemplateITApplication.class)
@Testcontainers
@Slf4j
@DirtiesContext
@ContextConfiguration(initializers = AbstractDfJavaTemplateBackForFrontendApiIT.WireMockInitializer.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public abstract class AbstractDfJavaTemplateBackForFrontendApiIT {

    @Container
    static PostgreSQLContainer postgresSQLContainer =
            new PostgreSQLContainer<>("postgres:13")
                    .withDatabaseName("df.-java-template");


    @DynamicPropertySource
    static void updateProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.password", postgresSQLContainer::getPassword);
        registry.add("spring.datasource.username", postgresSQLContainer::getUsername);
    }

    @LocalServerPort
    int port;

    @Autowired
    WebTestClient client;
    @Autowired
    protected WireMockServer wireMockServer;
    private final ObjectMapper objectMapper = new ObjectMapper();


    protected URI getApiURI(final String path) {
        return UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(port)
                .path(path)
                .build()
                .toUri();
    }

    protected URI getApiURIWithPathParams(final String path, Map<String, String> pathParams) {
        return UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(port)
                .path(path)
                .build(pathParams);
    }

    protected static final String REST_API_USERS = "/users/{id}";

    static protected final Faker faker = new Faker();

    public static class WireMockInitializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            WireMockServer bffWireMockServer = new WireMockServer(new WireMockConfiguration().dynamicPort());
            bffWireMockServer.start();

            configurableApplicationContext
                    .getBeanFactory()
                    .registerSingleton("bffWireMockServer", bffWireMockServer);

            configurableApplicationContext.addApplicationListener(
                    applicationEvent -> {
                        if (applicationEvent instanceof ContextClosedEvent) {
                            bffWireMockServer.stop();
                        }
                    });

            TestPropertyValues.of(
                            "application.job-api.url:http://localhost:" + bffWireMockServer.port() + "/")
                    .applyTo(configurableApplicationContext);
        }

    }

}
