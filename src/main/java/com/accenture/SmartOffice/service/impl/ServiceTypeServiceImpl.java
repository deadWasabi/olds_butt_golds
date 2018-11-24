package com.accenture.SmartOffice.service.impl;

import com.accenture.SmartOffice.dao.entity.ServiceType;
import com.accenture.SmartOffice.dao.repository.ServiceTypeRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.accenture.SmartOffice.model.web.WebServiceTypeModel;
import com.accenture.SmartOffice.service.ServiceTypeService;
import com.accenture.SmartOffice.utilits.MappingHelper;

import java.util.List;

@Service
public class ServiceTypeServiceImpl implements ServiceTypeService {

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public List<WebServiceTypeModel> getServicesTypes() {
        return MappingHelper.map(serviceTypeRepository.findAll(), WebServiceTypeModel.class);
    }

    @Override
    public ServiceType getServiceTypeById(Long id) {
        return serviceTypeRepository.getOne(id);
    }
}
