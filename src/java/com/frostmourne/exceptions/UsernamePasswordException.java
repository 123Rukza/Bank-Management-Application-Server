package com.frostmourne.exceptions;

import javax.xml.ws.WebFault;

@WebFault(name = "UsernamePasswordException")
public class UsernamePasswordException extends Exception {

    public UsernamePasswordException(String message, Throwable cause) {
        super(message);
    }
}
