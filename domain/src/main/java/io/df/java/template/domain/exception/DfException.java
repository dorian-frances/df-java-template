package io.df.java.template.domain.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DfException extends Exception {
    String dfExceptionCode;
    String errorMessage;

    public static boolean isExceptionNotFound(DfException dfException) {
        return dfException.getDfExceptionCode().startsWith("NF");
    }

    public static boolean isExceptionBadRequest(DfException dfException) {
        return dfException.getDfExceptionCode().startsWith("BD.");
    }
}
