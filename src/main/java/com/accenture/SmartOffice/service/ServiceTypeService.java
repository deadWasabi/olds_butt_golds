package com.accenture.SmartOffice.service;

import com.accenture.SmartOffice.dao.entity.ServiceType;
import com.accenture.SmartOffice.model.web.WebServiceTypeModel;

import java.util.List;

public interface ServiceTypeService {
    List<WebServiceTypeModel> getServicesTypes();

    ServiceType getServiceTypeById(Long id);
}
