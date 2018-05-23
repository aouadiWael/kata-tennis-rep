package com.aouadi.kata.tennis;

/**
 * 
 * @author Wael.Aouadi
 *
 */
@SuppressWarnings("unused")
public class TennisRuntimeException extends RuntimeException {

    public TennisRuntimeException() {
    }

    public TennisRuntimeException(String message) {
        super(message);
    }

    public TennisRuntimeException(Throwable e) {
        super(e);
    }

    public TennisRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}