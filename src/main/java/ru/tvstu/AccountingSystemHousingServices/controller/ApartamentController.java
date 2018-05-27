package ru.tvstu.AccountingSystemHousingServices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebApartmentAddDeviceModel;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebApartmentViewFullModel;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebApartmentViewModel;
import ru.tvstu.AccountingSystemHousingServices.service.ApartmentService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/apartment")
public class ApartamentController {

    private static final String VIEW = "/view";
    private static final String BY_ID = "/{id:.+}";
    private static final String ADD_DEVICE_TO_APARTMENT = "/add/device";
    private static final String REMOVE_DEVICE_TO_APARTMENT = "/remove/device";

    @Autowired
    private ApartmentService apartmentService;

    @ResponseBody
    @RequestMapping(value = VIEW, method = RequestMethod.GET)
    public List<WebApartmentViewModel> getUserInfo() {
        return apartmentService.getWebApartmentViewModels();
    }

    @ResponseBody
    @RequestMapping(value = BY_ID, method = RequestMethod.GET)
    public WebApartmentViewFullModel getUserInfo(@PathVariable Long id) {
        return apartmentService.getWebApartmentViewModelById(id);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public WebApartmentViewFullModel getUserInfo(@RequestBody WebApartmentViewFullModel webApartmentViewFullModel) {
        return apartmentService.createOrUpdateWebApartmentViewModel(webApartmentViewFullModel);
    }

    @ResponseBody
    @RequestMapping(value = ADD_DEVICE_TO_APARTMENT,method = RequestMethod.POST)
    public WebApartmentViewFullModel addDeviceToApartment(@RequestBody  WebApartmentAddDeviceModel webApartmentAddDeviceModel) {
        return apartmentService.addDeviceToApartment(webApartmentAddDeviceModel);
    }

    @ResponseBody
    @RequestMapping(value = REMOVE_DEVICE_TO_APARTMENT,method = RequestMethod.POST)
    public WebApartmentViewFullModel removeDeviceToApartment(@RequestBody WebApartmentAddDeviceModel webApartmentAddDeviceModel) {
        return apartmentService.removeDeviceToApartment(webApartmentAddDeviceModel);
    }
}
