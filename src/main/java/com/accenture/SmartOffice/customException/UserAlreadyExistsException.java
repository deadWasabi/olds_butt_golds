package com.accenture.SmartOffice.customException;

public class UserAlreadyExistsException extends BasicProjectException {

    public UserAlreadyExistsException(String message) {
        super(message);
    }

    public UserAlreadyExistsException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public UserAlreadyExistsException(String message, String code) {super(message, code);
    }

    public UserAlreadyExistsException(String message, String code, Throwable throwable) {
        super(message, code, throwable);
    }

}
