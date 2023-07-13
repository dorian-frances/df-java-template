package io.df.java.template.application.rest.api.adapter.mapper;

import io.df.java.template.contract.api.model.DfErrorDTO;
import io.df.java.template.domain.exception.DfException;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.function.Supplier;

public interface DfExceptionMapper {


    static DfErrorDTO errorToContract(DfException dfException) {
        final DfErrorDTO dfErrorDTO = new DfErrorDTO();
        dfErrorDTO.setStatusCode(dfException.getDfExceptionCode());
        dfErrorDTO.setErrorMessage(dfException.getErrorMessage());
        dfErrorDTO.setTimestamp(new Date().toString());
        return dfErrorDTO;
    }

    static <T> ResponseEntity<T> mapDfExceptionToContract(final Supplier<T> tSupplier, DfException dfException) {
        if (DfException.isExceptionNotFound(dfException)) {
            return ResponseEntity.status(404).body(tSupplier.get());
        } else if (DfException.isExceptionBadRequest(dfException)) {
            return ResponseEntity.status(400).body(tSupplier.get());
        } else {
            return ResponseEntity.status(500).body(tSupplier.get());
        }
    }
}
