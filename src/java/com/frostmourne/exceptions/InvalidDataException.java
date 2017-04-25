package com.frostmourne.exceptions;

import javax.xml.ws.WebFault;

@WebFault(name = "InvalidDataException")
public class InvalidDataException extends Exception {

    public InvalidDataException(String message) {
        super(message);
    }
}
