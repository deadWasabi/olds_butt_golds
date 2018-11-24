package com.accenture.SmartOffice.dao.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "service_type")
public class ServiceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String utilsOfMeasurements;

    private ServiceTypeEnum serviceTypeEnum;

    private String description;

    @OneToMany(mappedBy = "serviceType", fetch = FetchType.LAZY)
    private List<AccountingService> accountingService;

    @OneToMany(mappedBy = "serviceType", fetch = FetchType.LAZY)
    private List<ServiceCost> serviceCosts;

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

    public List<AccountingService> getAccountingService() {
        return accountingService;
    }

    public void setAccountingService(List<AccountingService> accountingService) {
        this.accountingService = accountingService;
    }

    public List<ServiceCost> getServiceCosts() {
        return serviceCosts;
    }

    public void setServiceCosts(List<ServiceCost> serviceCosts) {
        this.serviceCosts = serviceCosts;
    }
}
