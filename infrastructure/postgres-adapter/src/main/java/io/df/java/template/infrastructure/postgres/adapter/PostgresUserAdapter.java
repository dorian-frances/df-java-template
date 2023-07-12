package io.df.java.template.infrastructure.postgres.adapter;

import io.df.java.template.infrastructure.postgres.entity.UserEntity;
import io.df.java.template.infrastructure.postgres.repository.UserRepository;
import io.df.java.template.domain.exception.DfException;
import io.df.java.template.domain.exception.DfExceptionCode;
import io.df.java.template.domain.model.User;
import io.df.java.template.infrastructure.postgres.mapper.UserEntityMapper;
import io.df.java.template.domain.port.out.UserStoragePort;
import lombok.AllArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class PostgresUserAdapter implements UserStoragePort {

    private final UserRepository userRepository;

    @Override
    public Optional<User> getUser(UUID userId) {
        return userRepository.findById(userId).map(UserEntityMapper::fromEntityToDomain);
    }
}
