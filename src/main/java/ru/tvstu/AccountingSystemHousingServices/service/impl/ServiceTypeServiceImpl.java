package ru.tvstu.AccountingSystemHousingServices.service.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tvstu.AccountingSystemHousingServices.dao.entity.ServiceType;
import ru.tvstu.AccountingSystemHousingServices.dao.repository.ServiceTypeRepository;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebServiceTypeModel;
import ru.tvstu.AccountingSystemHousingServices.service.ServiceTypeService;
import ru.tvstu.AccountingSystemHousingServices.utilits.MappingHelper;

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
