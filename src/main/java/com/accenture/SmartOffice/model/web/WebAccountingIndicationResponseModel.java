package com.accenture.SmartOffice.model.web;

import java.util.Date;

public class WebAccountingIndicationResponseModel {
    private Long currentAccounting;
    private Date accountingDate;

    public Long getCurrentAccounting() {
        return currentAccounting;
    }

    public void setCurrentAccounting(Long currentAccounting) {
        this.currentAccounting = currentAccounting;
    }

    public Date getAccountingDate() {
        return accountingDate;
    }

    public void setAccountingDate(Date accountingDate) {
        this.accountingDate = accountingDate;
    }
}
