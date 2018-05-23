package com.aouadi.kata.tennis;

/**
 * @author Wael.Aouadi
 * 
 * Tennis Player Contract
 */
public interface Player {

    String getName();

    void winPoint();

    /**
     * Warning: the order if this enum elements is important
     * <pre>
     *     - The {@code FIRST} must have {@link Id#FIRST#ordinal} must be {@code 0}
     *     - The {@code SECOND} must have {@link Id#SECOND#ordinal} must be {@code 1}
     *     - ...
     * </pre>
     */
    enum Id {
        /**
         * The first player identifier
         */
        FIRST,
        /**
         * The second player identifier
         */
        SECOND,
        /**
         * Represents the identifier of {@code null}
         */
        NONE
    }
}
