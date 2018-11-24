package com.accenture.SmartOffice.service;

import com.accenture.SmartOffice.model.web.WebAccountingServiceModel;

import java.util.List;

public interface AccountingServiceService {
    List<WebAccountingServiceModel> getAccountingServiceByDeviceId(Long deviceId);

    WebAccountingServiceModel addAccountingService(WebAccountingServiceModel webAccountingServiceModel);

    WebAccountingServiceModel updateAccountingService(WebAccountingServiceModel webAccountingServiceModel);
}
