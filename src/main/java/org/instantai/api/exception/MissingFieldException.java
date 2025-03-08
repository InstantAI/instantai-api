package org.instantai.api.exception;

import java.util.List;

public class MissingFieldException extends RuntimeException {
    private final List<String> missingFields;

    public MissingFieldException(String message, List<String> missingFields) {
        super(message);
        this.missingFields = missingFields;
    }

    public List<String> getMissingFields() {
        return missingFields;
    }
}
