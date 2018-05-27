package ru.tvstu.AccountingSystemHousingServices.model.web;

import java.util.Date;

public class WebAccountingIndicationRequestModel {
    private Long serviceId;
    private Date startDate;
    private Date endDate;

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
