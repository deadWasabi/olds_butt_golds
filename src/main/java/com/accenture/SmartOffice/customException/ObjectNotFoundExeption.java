package com.accenture.SmartOffice.customException;

public class ObjectNotFoundExeption extends BasicProjectException {

    public ObjectNotFoundExeption(String message) {
        super(message);
    }

    public ObjectNotFoundExeption(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ObjectNotFoundExeption(String message, String code) {
        super(message, code);
    }

    public ObjectNotFoundExeption(String message, String code, Throwable throwable) {
        super(message, code, throwable);
    }
}
