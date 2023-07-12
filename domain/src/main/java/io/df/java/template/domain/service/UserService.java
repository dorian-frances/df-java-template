package io.df.java.template.domain.service;

import io.df.java.template.domain.exception.DfExceptionCode;
import io.df.java.template.domain.model.User;
import io.df.java.template.domain.exception.DfException;
import io.df.java.template.domain.port.in.UserFacade;
import io.df.java.template.domain.port.out.UserStoragePort;
import lombok.AllArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class UserService implements UserFacade {

    UserStoragePort userStoragePort;

    @Override
    public User getUser(UUID userId) throws DfException {
        final Optional<User> optionalUser = userStoragePort.getUser(userId);
        if (optionalUser.isEmpty()) {
            throw DfException.builder()
                    .dfExceptionCode(DfExceptionCode.USER_NOT_FOUND_EXCEPTION)
                    .errorMessage(String.format("Failed to find user with id %s", userId))
                    .build();
        }
        return optionalUser.get();
    }
}
