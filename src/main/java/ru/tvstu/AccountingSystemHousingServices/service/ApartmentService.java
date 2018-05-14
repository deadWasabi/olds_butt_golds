package ru.tvstu.AccountingSystemHousingServices.service;

import ru.tvstu.AccountingSystemHousingServices.model.web.WebApartmentViewFullModel;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebApartmentViewModel;

import java.util.List;

public interface ApartmentService {
    List<WebApartmentViewModel> getWebApartmentViewModels();

    WebApartmentViewFullModel getWebApartmentViewModelById(Long id);

    WebApartmentViewFullModel createOrUpdateWebApartmentViewModel(WebApartmentViewFullModel webApartmentViewFullModel);
}
