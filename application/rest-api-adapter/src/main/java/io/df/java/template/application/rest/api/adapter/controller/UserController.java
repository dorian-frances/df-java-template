package io.df.java.template.application.rest.api.adapter.controller;

import io.df.java.template.contract.api.UserApi;
import io.df.java.template.contract.api.model.GetUserDTO;
import io.df.java.template.domain.exception.DfException;
import io.df.java.template.domain.model.User;
import io.df.java.template.domain.port.in.UserFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static io.df.java.template.application.rest.api.adapter.mapper.DfExceptionMapper.mapDfExceptionToContract;
import static io.df.java.template.application.rest.api.adapter.mapper.UserMapper.errorToGetUserDTO;
import static io.df.java.template.application.rest.api.adapter.mapper.UserMapper.getUserFromDomainToResponse;

@RestController
@Tags(@Tag(name = "User"))
@AllArgsConstructor
public class UserController implements UserApi {

    final UserFacade userFacade;

    @Override
    public ResponseEntity<GetUserDTO> getUser(UUID id) {
        try {
            final User user = userFacade.getUser(id);
            return ResponseEntity.ok(getUserFromDomainToResponse(user));
        } catch (DfException exception) {
            return mapDfExceptionToContract(() -> errorToGetUserDTO(exception), exception);
        }

    }
}
