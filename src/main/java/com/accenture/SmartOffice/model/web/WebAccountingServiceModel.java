package com.accenture.SmartOffice.model.web;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WebAccountingServiceModel {
    private Long id;

    private Long accountingDeviceId;

    private Integer inputNumber;

    private String name;

    private String description;

    private Long serviceTypeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountingDeviceId() {
        return accountingDeviceId;
    }

    public void setAccountingDeviceId(Long accountingDeviceId) {
        this.accountingDeviceId = accountingDeviceId;
    }

    public Integer getInputNumber() {
        return inputNumber;
    }

    public void setInputNumber(Integer inputNumber) {
        this.inputNumber = inputNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(Long serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }
}
