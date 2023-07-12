package io.df.java.template.application.rest.api.adapter.mapper;



import io.df.java.template.contract.api.model.GetUserDTO;
import io.df.java.template.domain.exception.DfException;
import io.df.java.template.domain.model.User;

import static io.df.java.template.application.rest.api.adapter.mapper.DfExceptionMapper.errorToContract;

public interface UserMapper {
    static GetUserDTO getUserFromDomainToResponse(User user) {
        final GetUserDTO getUserDTO = new GetUserDTO();
        getUserDTO.setId(user.getId());
        getUserDTO.setUsername(user.getUsername());
        return getUserDTO;
    }

    static GetUserDTO errorToGetUserDTO(DfException dfException) {
        final GetUserDTO getUserDTO = new GetUserDTO();
        getUserDTO.setError(errorToContract(dfException));
        return getUserDTO;
    }
}
