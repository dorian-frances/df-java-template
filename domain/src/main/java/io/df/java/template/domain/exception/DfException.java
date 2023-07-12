package io.df.java.template.domain.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DfException extends Exception {
    String dfExceptionCode;
    String errorMessage;

    public static boolean isExceptionFunctional(DfException dfException) {
        return dfException.getDfExceptionCode().startsWith("F.");
    }
}
