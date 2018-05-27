package ru.tvstu.AccountingSystemHousingServices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebServiceTypeModel;
import ru.tvstu.AccountingSystemHousingServices.service.ServiceTypeService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/serviceType")
public class ServiceTypeController {
    private static final String VIEW = "/view";

    @Autowired
    private ServiceTypeService serviceTypeService;

    @ResponseBody
    @RequestMapping(value = VIEW, method = RequestMethod.GET)
    public List<WebServiceTypeModel> getAllServiceTypes() {
        return serviceTypeService.getServicesTypes();
    }
}
