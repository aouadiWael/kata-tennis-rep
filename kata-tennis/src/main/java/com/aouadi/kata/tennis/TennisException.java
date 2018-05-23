package com.aouadi.kata.tennis;

/**
 * 
 * @author Wael.Aouadi
 *
 */
public class TennisException extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TennisException(){
    }

    public TennisException(String message){
        super(message);
    }
}