package com.accenture.SmartOffice.service.impl;

import com.accenture.SmartOffice.customException.IllegalArgumentException;
import com.accenture.SmartOffice.customException.ObjectNotFoundExeption;
import com.accenture.SmartOffice.dao.entity.Apartment;
import com.accenture.SmartOffice.dao.entity.ServiceCost;
import com.accenture.SmartOffice.dao.entity.ServiceType;
import com.accenture.SmartOffice.dao.entity.User;
import com.accenture.SmartOffice.dao.repository.ServiceCostRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.accenture.SmartOffice.model.web.WebServiceCostModel;
import com.accenture.SmartOffice.service.ApartmentService;
import com.accenture.SmartOffice.service.ServiceCostService;
import com.accenture.SmartOffice.service.ServiceTypeService;
import com.accenture.SmartOffice.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceCostServiceImpl implements ServiceCostService {

    @Autowired
    private UserService userService;

    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    private ServiceCostRepository serviceCostRepository;

    @Autowired
    private ServiceTypeService serviceTypeService;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @Override
    public List<WebServiceCostModel> getServiceCostByApartmentId(Long apartmentId) {
        User user = userService.getCurrentUser();
        Apartment apartment = apartmentService.getApartmentById(apartmentId);
        if (!user.getId().equals(apartment.getOwner().getId())) {
            throw new ObjectNotFoundExeption("Not found apartment by apartment id - " + apartmentId + " for current user");
        }

        List<WebServiceCostModel> webServiceCostModels = new ArrayList<>();
        for (ServiceCost serviceCost : apartment.getServiceCosts()) {
            WebServiceCostModel webServiceCostModel = new WebServiceCostModel();
            webServiceCostModel.setApartmentId(apartment.getId());
            webServiceCostModel.setCost(serviceCost.getCost());
            webServiceCostModel.setServiceTypeId(serviceCost.getId());
        }
        return webServiceCostModels;
    }

    @Override
    public WebServiceCostModel getServiceCostById(Long id) {
        ServiceCost serviceCost = serviceCostRepository.getOne(id);
        if (serviceCost == null) {
            throw new ObjectNotFoundExeption("Service cost not found  by id - " + id);
        }

        User user = userService.getCurrentUser();
        if (!user.getId().equals(serviceCost.getApartment().getOwner().getId())) {
            throw new ObjectNotFoundExeption("Service cost not found  by id - " + id);
        }

        WebServiceCostModel webServiceCostModel = new WebServiceCostModel();
        webServiceCostModel.setCost(serviceCost.getCost());
        webServiceCostModel.setApartmentId(serviceCost.getApartment().getId());
        webServiceCostModel.setServiceTypeId(serviceCost.getServiceType().getId());
        return webServiceCostModel;
    }

    @Override
    public WebServiceCostModel createOrUpdateServiceCost(WebServiceCostModel webServiceCostModel) {

        if(webServiceCostModel == null) {
            throw new IllegalArgumentException("Object can't be null");
        }

        ServiceCost serviceCost;

        if(webServiceCostModel.getId() != null) {
            serviceCost = serviceCostRepository.getOne(webServiceCostModel.getId());
            if(serviceCost == null) {
                throw new ObjectNotFoundExeption("Service cost not found by id - " + webServiceCostModel.getId());
            }
        } else {
            serviceCost = new ServiceCost();
        }

        if (webServiceCostModel.getCost() == null) {
            throw new IllegalArgumentException("Cost is not set");
        }

        ServiceType serviceType = serviceTypeService.getServiceTypeById(webServiceCostModel.getServiceTypeId());
        if (serviceType == null) {
            throw new ObjectNotFoundExeption("Service type not fount by id - " + webServiceCostModel.getServiceTypeId());
        }

        Apartment apartment = apartmentService.getApartmentById(webServiceCostModel.getApartmentId());
        User user = userService.getCurrentUser();
        if(apartment == null || !user.getId().equals(apartment.getId())) {
            throw new ObjectNotFoundExeption("Apartment not fount by id - " + webServiceCostModel.getApartmentId() + "for current user");
        }

        serviceCost.setApartment(apartment);
        serviceCost.setCost(webServiceCostModel.getCost());
        serviceCost.setServiceType(serviceType);
        serviceCost = serviceCostRepository.save(serviceCost);

        WebServiceCostModel webServiceCostModel1 = new WebServiceCostModel();
        webServiceCostModel.setCost(serviceCost.getCost());
        webServiceCostModel.setApartmentId(serviceCost.getApartment().getId());
        webServiceCostModel.setServiceTypeId(serviceCost.getServiceType().getId());

        return webServiceCostModel1;
    }
}
