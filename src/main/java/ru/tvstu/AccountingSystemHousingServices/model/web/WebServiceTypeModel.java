package ru.tvstu.AccountingSystemHousingServices.model.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.tvstu.AccountingSystemHousingServices.dao.entity.ServiceTypeEnum;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WebServiceTypeModel {

    private Long id;

    private String name;

    private String utilsOfMeasurements;

    private ServiceTypeEnum serviceTypeEnum;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUtilsOfMeasurements() {
        return utilsOfMeasurements;
    }

    public void setUtilsOfMeasurements(String utilsOfMeasurements) {
        this.utilsOfMeasurements = utilsOfMeasurements;
    }

    public ServiceTypeEnum getServiceTypeEnum() {
        return serviceTypeEnum;
    }

    public void setServiceTypeEnum(ServiceTypeEnum serviceTypeEnum) {
        this.serviceTypeEnum = serviceTypeEnum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
