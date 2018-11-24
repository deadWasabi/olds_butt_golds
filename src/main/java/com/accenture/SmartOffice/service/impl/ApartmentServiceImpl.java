package com.accenture.SmartOffice.service.impl;

import com.accenture.SmartOffice.customException.IllegalArgumentException;
import com.accenture.SmartOffice.customException.ObjectNotFoundExeption;
import com.accenture.SmartOffice.dao.entity.AccountingDevice;
import com.accenture.SmartOffice.dao.entity.Apartment;
import com.accenture.SmartOffice.dao.repository.ApartmentRepository;
import com.accenture.SmartOffice.model.web.WebAccountingDeviceModel;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.accenture.SmartOffice.model.web.WebApartmentAddDeviceModel;
import com.accenture.SmartOffice.model.web.WebApartmentViewFullModel;
import com.accenture.SmartOffice.model.web.WebApartmentViewModel;
import com.accenture.SmartOffice.service.AccountingDeviceService;
import com.accenture.SmartOffice.service.ApartmentService;
import com.accenture.SmartOffice.utilits.MappingHelper;

import java.util.List;

@Service
public class ApartmentServiceImpl implements ApartmentService {

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private AccountingDeviceService accountingDeviceService;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @Override
    public List<WebApartmentViewModel> getWebApartmentViewModels() {
        List<Apartment> apartments = apartmentRepository.getApartmentsByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        return MappingHelper.map(apartments, WebApartmentViewModel.class);
    }

    @Override
    public WebApartmentViewFullModel getWebApartmentViewModelById(Long id) {
        Apartment apartment = apartmentRepository.getOne(id);
        if (SecurityContextHolder.getContext().getAuthentication().getName().equals(apartment.getOwner().getLogin())) {
            WebApartmentViewFullModel webApartmentViewModel = dozerBeanMapper.map(apartment, WebApartmentViewFullModel.class);
            webApartmentViewModel.setWebAccountingDeviceModelList(MappingHelper.map(apartment.getAccountingDevice(), WebAccountingDeviceModel.class));
            return webApartmentViewModel;
        } else {
            throw new ObjectNotFoundExeption("Apartment by id - " + id + "not fount for user - " + SecurityContextHolder.getContext().getAuthentication().getName());
        }
    }

    @Override
    public WebApartmentViewFullModel createOrUpdateWebApartmentViewModel(WebApartmentViewFullModel webApartmentViewFullModel) {
        Apartment apartment = dozerBeanMapper.map(webApartmentViewFullModel, Apartment.class);
        return dozerBeanMapper.map(apartmentRepository.save(apartment), WebApartmentViewFullModel.class);
    }

    @Override
    public WebApartmentViewFullModel addDeviceToApartment(WebApartmentAddDeviceModel webApartmentAddDeviceModel) {

        Apartment apartment = getApartmentById(webApartmentAddDeviceModel.getApartmentId());
        if (apartment == null) {
            throw new ObjectNotFoundExeption("Apartment not found");
        }

        AccountingDevice accountingDevice = accountingDeviceService.getDeviceById(webApartmentAddDeviceModel.getAccountingDeviceId());
        if (accountingDevice == null) {
            throw new ObjectNotFoundExeption("Accounting device not found");
        }

        if (accountingDevice.getApartment() != null) {
            throw new IllegalArgumentException("AccountingDevice already add to apartment");
        }

        accountingDevice.setApartment(apartment);
        accountingDevice = accountingDeviceService.createOrUpdateDevice(accountingDevice);
        apartment.getAccountingDevice().add(accountingDevice);

        return dozerBeanMapper.map(apartment, WebApartmentViewFullModel.class);
    }

    @Override
    public WebApartmentViewFullModel removeDeviceToApartment(WebApartmentAddDeviceModel webApartmentAddDeviceModel) {
        Apartment apartment = getApartmentById(webApartmentAddDeviceModel.getApartmentId());
        if (apartment == null) {
            throw new ObjectNotFoundExeption("Apartment not found");
        }

        AccountingDevice accountingDevice = accountingDeviceService.getDeviceById(webApartmentAddDeviceModel.getAccountingDeviceId());
        if (accountingDevice == null) {
            throw new ObjectNotFoundExeption("Accounting device not found");
        }

        if (!accountingDevice.getApartment().getId().equals( apartment.getId())) {
            throw new IllegalArgumentException("AccountingDevice not connected to apartment");
        }

        apartment.getAccountingDevice().remove(accountingDevice);
        accountingDevice.setApartment(null);
        accountingDeviceService.createOrUpdateDevice(accountingDevice);

        return dozerBeanMapper.map(apartment, WebApartmentViewFullModel.class);
    }

    @Override
    public Apartment getApartmentById(Long apartmentId) {
        if (apartmentId == null) {
            throw new IllegalArgumentException("Apartment Id can't be null");
        }
        return apartmentRepository.getOne(apartmentId);
    }
}
