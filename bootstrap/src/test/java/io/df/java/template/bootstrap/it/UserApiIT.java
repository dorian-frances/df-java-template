package io.df.java.template.bootstrap.it;

import io.df.java.template.infrastructure.postgres.entity.UserEntity;
import io.df.java.template.infrastructure.postgres.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.UUID;

public class UserApiIT extends AbstractDfJavaTemplateBackForFrontendApiIT {

    @Autowired
    public UserRepository userRepository;

    @Test
    void should_find_user_for_user_id() {
        final UUID userId = UUID.randomUUID();
        final String username = faker.ancient().god();
        final UserEntity userEntity = userRepository.save(
                UserEntity.builder()
                        .id(userId)
                        .username(username)
                        .build()
        );

        // When
        client.get()
                .uri(getApiURIWithPathParams(REST_API_USERS, Map.of("id", userId.toString())))
                .exchange()
                // Then
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$.id").isEqualTo(userId.toString())
                .jsonPath("$.username").isEqualTo(username)
                .jsonPath("$.error").isEmpty();

    }
}