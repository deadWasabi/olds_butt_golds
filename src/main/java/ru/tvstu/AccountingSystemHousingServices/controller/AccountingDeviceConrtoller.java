package ru.tvstu.AccountingSystemHousingServices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebAccountingDeviceModel;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebAccountingDeviceVerifyModel;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebAccountingServiceValueModel;
import ru.tvstu.AccountingSystemHousingServices.service.AccountingDeviceService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/accountingDevice")
public class AccountingDeviceConrtoller {

    private static final String VERIFY = "/verify";
    private static final String ACCOUNTING = "/accounting";
    private static final String ADD = "/add";
    private static final String VIEW = "/view";
    private static final String BY_ID = "/{id:.+}";

    @Autowired
    private AccountingDeviceService accountingDeviceService;

    @ResponseBody
    @RequestMapping(value = VERIFY, method = RequestMethod.POST)
    public WebAccountingDeviceVerifyModel verifyAccountingDevice(@RequestBody WebAccountingDeviceVerifyModel webAccountingDeviceVerifyModel) {
        return accountingDeviceService.verifyAccountingDevice(webAccountingDeviceVerifyModel);
    }

    @ResponseBody
    @RequestMapping(value = ACCOUNTING, method = RequestMethod.POST)
    public ResponseEntity<?> accoungingDeviceValue(@RequestBody WebAccountingServiceValueModel webAccountingServiceValueModel) {
        if(accountingDeviceService.saveNewIndicationsReceivedFromDevice(webAccountingServiceValueModel)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @ResponseBody
    @RequestMapping(value = ADD, method = RequestMethod.POST)
    public WebAccountingDeviceModel addAccountinDevice(@RequestBody String serialNumber) {
       return accountingDeviceService.addAccountingDeviceToUser(serialNumber);
    }

    @ResponseBody
    @RequestMapping(value = VIEW, method = RequestMethod.GET)
    public List<WebAccountingDeviceModel> getAccountingDevices() {
        return accountingDeviceService.getAccountingDevices();
    }

    @ResponseBody
    @RequestMapping(value = BY_ID, method = RequestMethod.GET)
    public WebAccountingDeviceModel getAccountingDevice(@PathVariable Long accoungintDeviceId) {
        return accountingDeviceService.getWebModelDeviceById(accoungintDeviceId);
    }
}
