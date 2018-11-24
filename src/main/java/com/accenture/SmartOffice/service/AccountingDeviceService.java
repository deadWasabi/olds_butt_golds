package com.accenture.SmartOffice.service;

import com.accenture.SmartOffice.dao.entity.AccountingDevice;
import com.accenture.SmartOffice.model.web.WebAccountingDeviceModel;
import com.accenture.SmartOffice.model.web.WebAccountingDeviceVerifyModel;
import com.accenture.SmartOffice.model.web.WebAccountingServiceValueModel;

import java.util.List;
import java.util.UUID;

public interface AccountingDeviceService {

    WebAccountingDeviceVerifyModel verifyAccountingDevice(WebAccountingDeviceVerifyModel webAccountingDeviceVerifyModel);

    WebAccountingDeviceModel addAccountingDeviceToUser(String serialNumber);

    List<WebAccountingDeviceModel> getAccountingDevices();

    AccountingDevice getDeviceById(Long deviceId);

    AccountingDevice createOrUpdateDevice(AccountingDevice accountingDevice);

    WebAccountingDeviceModel getWebModelDeviceById(Long deviceId);

    AccountingDevice getAccountingDeviceByUUID(UUID deviceUUID);

    Boolean saveNewIndicationsReceivedFromDevice(WebAccountingServiceValueModel webAccountingServiceValueModel);
}
