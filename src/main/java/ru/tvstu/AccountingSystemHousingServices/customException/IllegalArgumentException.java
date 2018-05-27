package ru.tvstu.AccountingSystemHousingServices.customException;

public class IllegalArgumentException extends BasicProjectException {
    public IllegalArgumentException(String message) {
        super(message);
    }

    public IllegalArgumentException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public IllegalArgumentException(String message, String code) {
        super(message, code);
    }

    public IllegalArgumentException(String message, String code, Throwable throwable) {
        super(message, code, throwable);
    }
}
