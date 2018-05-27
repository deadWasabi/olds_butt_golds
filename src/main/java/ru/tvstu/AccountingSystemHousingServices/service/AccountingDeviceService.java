package ru.tvstu.AccountingSystemHousingServices.service;

import ru.tvstu.AccountingSystemHousingServices.dao.entity.AccountingDevice;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebAccountingDeviceModel;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebAccountingDeviceVerifyModel;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebAccountingServiceValueModel;

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
