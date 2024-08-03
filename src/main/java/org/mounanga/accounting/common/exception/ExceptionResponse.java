package org.mounanga.accounting.common.exception;

import java.util.Set;

public record ExceptionResponse(Integer code,
                                String message,
                                String description,
                                Set<String> validation) {
}
