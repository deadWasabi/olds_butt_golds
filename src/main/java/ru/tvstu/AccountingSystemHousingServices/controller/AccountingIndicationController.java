package ru.tvstu.AccountingSystemHousingServices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebAccountingIndicationRequestModel;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebAccountingIndicationResponseModel;
import ru.tvstu.AccountingSystemHousingServices.service.AccountingIndicationService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/accountingIndication")
public class AccountingIndicationController {
    private static final String VIEW = "/view";

    @Autowired
    private AccountingIndicationService accountingIndicationService;

    @ResponseBody
    @RequestMapping(value = VIEW, method = RequestMethod.GET)
    public List<WebAccountingIndicationResponseModel> getServiceIndications(@RequestBody WebAccountingIndicationRequestModel webAccountingIndicationRequestModel) {
        return null;
    }
}
