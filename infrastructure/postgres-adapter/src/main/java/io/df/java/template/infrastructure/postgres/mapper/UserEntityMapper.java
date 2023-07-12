package io.df.java.template.infrastructure.postgres.mapper;

import io.df.java.template.infrastructure.postgres.entity.UserEntity;
import io.df.java.template.domain.model.User;

public interface UserEntityMapper {
    static User fromEntityToDomain(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .build();
    }
}
