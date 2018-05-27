package ru.tvstu.AccountingSystemHousingServices.service;

import ru.tvstu.AccountingSystemHousingServices.dao.entity.Apartment;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebApartmentAddDeviceModel;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebApartmentViewFullModel;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebApartmentViewModel;

import java.util.List;

public interface ApartmentService {
    List<WebApartmentViewModel> getWebApartmentViewModels();

    WebApartmentViewFullModel getWebApartmentViewModelById(Long id);

    WebApartmentViewFullModel createOrUpdateWebApartmentViewModel(WebApartmentViewFullModel webApartmentViewFullModel);

    WebApartmentViewFullModel addDeviceToApartment(WebApartmentAddDeviceModel webApartmentAddDeviceModel);

    WebApartmentViewFullModel removeDeviceToApartment(WebApartmentAddDeviceModel webApartmentAddDeviceModel);

    Apartment getApartmentById(Long apartmentId);
}
