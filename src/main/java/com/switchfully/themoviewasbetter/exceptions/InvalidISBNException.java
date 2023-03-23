package com.switchfully.themoviewasbetter.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidISBNException extends RuntimeException {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public static final String ERROR_MESSAGE = "Invalid ISBN provided";
    public InvalidISBNException() {
        super(ERROR_MESSAGE);
        logger.error(ERROR_MESSAGE);
    }
}
