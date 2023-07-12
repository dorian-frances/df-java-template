package io.df.java.template.domain.port.in;

import io.df.java.template.domain.model.User;
import io.df.java.template.domain.exception.DfException;

import java.util.UUID;

public interface UserFacade {

    User getUser(UUID userId) throws DfException;
}
