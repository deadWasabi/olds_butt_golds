package com.accenture.SmartOffice.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class IdentityDocument {
    /**
     * Тип документа
     */
    private String documentType;

    /**
     * Номер документа
     */
    private String number;

    /**
     * Серия документа
     */
    private String serial;

    /**
     * Дата выдачи
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date issueDate;

    /**
     * Кем выдан
     */
    private String issuer;

    /**
     * Код подразделения
     */
    private String issuerCode;

    public IdentityDocument() {
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getIssuerCode() {
        return issuerCode;
    }

    public void setIssuerCode(String issuerCode) {
        this.issuerCode = issuerCode;
    }
}
