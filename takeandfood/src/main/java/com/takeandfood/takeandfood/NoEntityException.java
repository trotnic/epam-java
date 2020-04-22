package com.takeandfood.takeandfood;/*
 * @project takeandfood
 * @author vladislav on 4/22/20
 */

import javax.lang.model.UnknownEntityException;

public class NoEntityException extends Exception {
    public NoEntityException(String message) {
        super(message);
    }
}
