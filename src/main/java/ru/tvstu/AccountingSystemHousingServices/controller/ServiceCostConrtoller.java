package ru.tvstu.AccountingSystemHousingServices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebServiceCostModel;
import ru.tvstu.AccountingSystemHousingServices.service.ServiceCostService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/serviceCost")
public class ServiceCostConrtoller {
    private static final String VIEW = "/view";
    private static final String APARTMENT_ID = "/apartmentId";
    private static final String BY_ID = "/{id:.+}";
    private static final String ADD = "/add";
    private static final String UPDATE = "/update";

    @Autowired
    private ServiceCostService serviceCostService;

    @ResponseBody
    @RequestMapping(value = VIEW + APARTMENT_ID + BY_ID, method = RequestMethod.GET)
    public List<WebServiceCostModel> getServiceCostsByApartment(@PathVariable Long apartmentId) {
        return serviceCostService.getServiceCostByApartmentId(apartmentId);
    }

    @ResponseBody
    @RequestMapping(value = BY_ID, method = RequestMethod.GET)
    public WebServiceCostModel getServiceCostsById(@PathVariable Long id) {
        return serviceCostService.getServiceCostById(id);
    }

    @ResponseBody
    @RequestMapping(value = {ADD, UPDATE}, method = RequestMethod.POST)
    public WebServiceCostModel createServiceCost(@RequestBody WebServiceCostModel webServiceCostModel) {
        return serviceCostService.createOrUpdateServiceCost(webServiceCostModel);
    }
}
