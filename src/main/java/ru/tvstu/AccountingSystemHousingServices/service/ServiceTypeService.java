package ru.tvstu.AccountingSystemHousingServices.service;

import ru.tvstu.AccountingSystemHousingServices.dao.entity.ServiceType;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebServiceTypeModel;

import java.util.List;

public interface ServiceTypeService {
    List<WebServiceTypeModel> getServicesTypes();

    ServiceType getServiceTypeById(Long id);
}
