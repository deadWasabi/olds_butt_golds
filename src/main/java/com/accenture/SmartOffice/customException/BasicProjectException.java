package com.accenture.SmartOffice.customException;

import java.util.UUID;

public class BasicProjectException extends RuntimeException {
    private static final int MAX_DEPTH_CAUSES = 5;
    /**
     * Уникальный идентификатор ошибки
     */
    private String uuid;
    /**
     * Код ошибки
     */
    private String code;

    public BasicProjectException() {
    }

    public BasicProjectException(String message) {
        super(message);
        this.uuid = createUuid();
        this.code = createCode();
    }

    public BasicProjectException(String message, String uuid, String code) {
        super(message);
        this.uuid = uuid;
        this.code = code;
    }

    public BasicProjectException(String message, String code, Throwable throwable) {
        super(message, throwable);
        this.uuid = throwable instanceof BasicProjectException ? ((BasicProjectException) throwable).getUuid() : createUuid();
        this.code = code != null ? code : createCode();
    }

    public BasicProjectException(String message, String code) {
        super(message);
        this.uuid = createUuid();
        this.code = code != null ? code : createCode();
    }

    public BasicProjectException(String message, Throwable throwable) {
        this(message, null, throwable);
    }

    public String getUuid() {
        return uuid;
    }

    protected String createUuid() {
        return UUID.randomUUID().toString();
    }

    public String getCode() {
        return code;
    }

    protected String createCode() {
        return getClass().getSimpleName().replaceAll("([^A-Z])([A-Z])", "$1_$2").replaceFirst("^teamwiseServer_",
                "").toUpperCase();
    }

    @Override
    public String toString() {
        String s = getClass().getName() + "[uuid = " + getUuid() + ", code = " + getCode() + "]";
        String message = getMessage();
        return (message != null) ? (s + ": " + message) : s;
    }
}
