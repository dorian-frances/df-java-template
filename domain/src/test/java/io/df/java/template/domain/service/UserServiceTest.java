package io.df.java.template.domain.service;

import com.github.javafaker.Faker;
import io.df.java.template.domain.exception.DfException;
import io.df.java.template.domain.exception.DfExceptionCode;
import io.df.java.template.domain.model.User;
import io.df.java.template.domain.port.out.UserStoragePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private final Faker faker = new Faker();

    private UserStoragePort userStoragePortMock;
    private UserService userService;

    @BeforeEach
    void initEach() {
        userStoragePortMock = mock(UserStoragePort.class);
        userService = new UserService(userStoragePortMock);
    }

    @Test
    void should_throw_exception_for_not_found_user() throws DfException {
        // Given
        final UUID userIdFake = UUID.randomUUID();

        // When
        when(userStoragePortMock.getUser(userIdFake))
                .thenReturn(Optional.empty());
        DfException exception = null;
        try {
            userService.getUser(userIdFake);
        } catch (DfException dfException) {
            exception = dfException;
        }

        // Then
        assertThat(exception).isNotNull();
        assertThat(exception.getDfExceptionCode()).isEqualTo(DfExceptionCode.USER_NOT_FOUND_EXCEPTION);
        assertThat(exception.getErrorMessage()).isEqualTo(String.format("Failed to find user with id %s", userIdFake));

    }

    @Test
    void should_return_user_given_a_user_id() throws DfException {
        // Given
        final UUID userIdFake = UUID.randomUUID();
        final String usernameFake = faker.ancient().god();

        // When
        when(userStoragePortMock.getUser(userIdFake))
                .thenReturn(Optional.of(User.builder().id(userIdFake).username(usernameFake).build()));
        final User resultUser = userService.getUser(userIdFake);

        // Then
        assertThat(resultUser.getId()).isEqualTo(userIdFake);
        assertThat(resultUser.getUsername()).isEqualTo(usernameFake);
    }
}
