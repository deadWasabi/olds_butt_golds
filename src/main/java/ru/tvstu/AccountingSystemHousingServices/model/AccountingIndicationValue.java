package ru.tvstu.AccountingSystemHousingServices.model;

import java.util.Date;

public class AccountingIndicationValue {
    private Integer inputNumber;
    private Long currentIndication;
    private Date dateIndication;

    public Integer getInputNumber() {
        return inputNumber;
    }

    public void setInputNumber(Integer inputNumber) {
        this.inputNumber = inputNumber;
    }

    public Long getCurrentIndication() {
        return currentIndication;
    }

    public void setCurrentIndication(Long currentIndication) {
        this.currentIndication = currentIndication;
    }

    public Date getDateIndication() {
        return dateIndication;
    }

    public void setDateIndication(Date dateIndication) {
        this.dateIndication = dateIndication;
    }
}
