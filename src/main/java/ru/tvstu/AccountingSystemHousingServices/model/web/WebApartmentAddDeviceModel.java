package ru.tvstu.AccountingSystemHousingServices.model.web;

public class WebApartmentAddDeviceModel {
    private Long apartmentId;
    private Long accountingDeviceId;

    public Long getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Long apartmentId) {
        this.apartmentId = apartmentId;
    }

    public Long getAccountingDeviceId() {
        return accountingDeviceId;
    }

    public void setAccountingDeviceId(Long accountingDeviceId) {
        this.accountingDeviceId = accountingDeviceId;
    }
}
