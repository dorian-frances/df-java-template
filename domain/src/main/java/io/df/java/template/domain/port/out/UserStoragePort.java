package io.df.java.template.domain.port.out;

import io.df.java.template.domain.exception.DfException;
import io.df.java.template.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserStoragePort {
    Optional<User> getUser(UUID userId) throws DfException;
}
