package ru.tvstu.AccountingSystemHousingServices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebApartmentViewFullModel;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebApartmentViewModel;
import ru.tvstu.AccountingSystemHousingServices.service.ApartmentService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/apartment")
public class ApartamentController {

    private static final String VIEW_PATH = "/view";
    private static final String BY_ID_PATH = "/{id:.+}";

    @Autowired
    private ApartmentService apartmentService;

    @ResponseBody
    @RequestMapping(value = VIEW_PATH, method = RequestMethod.GET)
    public List<WebApartmentViewModel> getUserInfo() {
        return apartmentService.getWebApartmentViewModels();
    }

    @ResponseBody
    @RequestMapping(value = BY_ID_PATH, method = RequestMethod.GET)
    public WebApartmentViewFullModel getUserInfo(@PathVariable Long id) {
        return apartmentService.getWebApartmentViewModelById(id);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public WebApartmentViewFullModel getUserInfo(WebApartmentViewFullModel webApartmentViewFullModel) {
        return apartmentService.createOrUpdateWebApartmentViewModel(webApartmentViewFullModel);
    }
}
