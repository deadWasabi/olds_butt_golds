package ru.tvstu.AccountingSystemHousingServices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebAccountingServiceModel;
import ru.tvstu.AccountingSystemHousingServices.service.AccountingServiceService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/accountingService")
public class AccountingServiceController {

    private static final String VIEW = "/view";
    private static final String FOR_DEVICE = VIEW + "/device";
    private static final String BY_ID = "/{id:.+}";
    private static final String ADD = "/add";
    private static final String UPDATE = "/update";

    @Autowired
    private AccountingServiceService accountingService;

    @ResponseBody
    @RequestMapping(value = FOR_DEVICE + BY_ID, method = RequestMethod.GET)
    public List<WebAccountingServiceModel> getAccountingServiceForDevice(@PathVariable Long deviceId) {
        return accountingService.getAccountingServiceByDeviceId(deviceId);
    }

    @ResponseBody
    @RequestMapping(value = ADD, method = RequestMethod.POST)
    public WebAccountingServiceModel addAccountingService(@RequestBody WebAccountingServiceModel webAccountingServiceModel) {
        return accountingService.addAccountingService(webAccountingServiceModel);
    }

    @ResponseBody
    @RequestMapping(value = UPDATE, method = RequestMethod.POST)
    public WebAccountingServiceModel updateAccountingService(@RequestBody WebAccountingServiceModel webAccountingServiceModel) {
        return accountingService.updateAccountingService(webAccountingServiceModel);
    }
}
