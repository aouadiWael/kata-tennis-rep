package com.aouadi.kata.tennis.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Wael.Aouadi
 * Proxy Invocation Handler
 * Handles the methods invoke for a delegate object.
 * Used as workaround of the cyclic dependencies between {@link SinglePlayer} and {@link ScoreMediator} classes.
 * @param <T>
 */
public class DelegateInvocationHandler<T> implements InvocationHandler {

    private T delegate;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            return method.invoke(delegate, args);
        }catch (InvocationTargetException ite){
            throw ite.getCause();
        }catch (Throwable t){
            if(delegate == null){
                throw new IllegalStateException("The delegate is not set. ");
            }
            throw t;
        }
    }

    public void setDelegate(T delegate) {
        this.delegate = delegate;
    }
}