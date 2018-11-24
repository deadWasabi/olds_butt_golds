package com.accenture.SmartOffice.service;

import com.accenture.SmartOffice.dao.entity.Apartment;
import com.accenture.SmartOffice.model.web.WebApartmentAddDeviceModel;
import com.accenture.SmartOffice.model.web.WebApartmentViewFullModel;
import com.accenture.SmartOffice.model.web.WebApartmentViewModel;

import java.util.List;

public interface ApartmentService {
    List<WebApartmentViewModel> getWebApartmentViewModels();

    WebApartmentViewFullModel getWebApartmentViewModelById(Long id);

    WebApartmentViewFullModel createOrUpdateWebApartmentViewModel(WebApartmentViewFullModel webApartmentViewFullModel);

    WebApartmentViewFullModel addDeviceToApartment(WebApartmentAddDeviceModel webApartmentAddDeviceModel);

    WebApartmentViewFullModel removeDeviceToApartment(WebApartmentAddDeviceModel webApartmentAddDeviceModel);

    Apartment getApartmentById(Long apartmentId);
}
