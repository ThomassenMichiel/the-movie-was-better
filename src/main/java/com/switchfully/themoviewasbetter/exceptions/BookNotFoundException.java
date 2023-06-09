package com.switchfully.themoviewasbetter.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BookNotFoundException extends RuntimeException{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public static final String ERROR_MESSAGE = "The book you requested has not been found";
    public BookNotFoundException() {
        super(ERROR_MESSAGE);
        logger.error(ERROR_MESSAGE);
    }
}
