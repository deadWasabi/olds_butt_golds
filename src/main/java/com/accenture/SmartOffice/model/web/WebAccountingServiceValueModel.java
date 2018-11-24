package com.accenture.SmartOffice.model.web;

import com.accenture.SmartOffice.model.AccountingIndicationValue;

import java.util.List;
import java.util.UUID;

public class WebAccountingServiceValueModel {
    private UUID deviceUUID;
    private List<AccountingIndicationValue> accountingIndicationValues;

    public UUID getDeviceUUID() {
        return deviceUUID;
    }

    public void setDeviceUUID(UUID deviceUUID) {
        this.deviceUUID = deviceUUID;
    }

    public List<AccountingIndicationValue> getAccountingIndicationValues() {
        return accountingIndicationValues;
    }

    public void setAccountingIndicationValues(List<AccountingIndicationValue> accountingIndicationValues) {
        this.accountingIndicationValues = accountingIndicationValues;
    }
}
