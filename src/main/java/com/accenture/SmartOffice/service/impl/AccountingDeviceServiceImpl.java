package com.accenture.SmartOffice.service.impl;

import com.accenture.SmartOffice.customException.IllegalArgumentException;
import com.accenture.SmartOffice.dao.entity.AccountingDevice;
import com.accenture.SmartOffice.dao.entity.AccountingIndication;
import com.accenture.SmartOffice.dao.entity.AccountingService;
import com.accenture.SmartOffice.dao.entity.User;
import com.accenture.SmartOffice.dao.repository.AccountingDeviceRepository;
import com.accenture.SmartOffice.model.AccountingIndicationValue;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.accenture.SmartOffice.model.web.WebAccountingDeviceModel;
import com.accenture.SmartOffice.model.web.WebAccountingDeviceVerifyModel;
import com.accenture.SmartOffice.model.web.WebAccountingServiceValueModel;
import com.accenture.SmartOffice.service.AccountingDeviceService;
import com.accenture.SmartOffice.service.AccountingIndicationService;
import com.accenture.SmartOffice.service.UserService;
import com.accenture.SmartOffice.utilits.MappingHelper;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class AccountingDeviceServiceImpl implements AccountingDeviceService {

    @Autowired
    private AccountingDeviceRepository accountingDeviceRepository;

    @Autowired
    private AccountingIndicationService accountingIndicationService;

    @Autowired
    private UserService userService;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @Override
    public WebAccountingDeviceVerifyModel verifyAccountingDevice(WebAccountingDeviceVerifyModel webAccountingDeviceVerifyModel) {
        AccountingDevice accountingDevice = accountingDeviceRepository.findBySerialNumber(webAccountingDeviceVerifyModel.getSerialNumber());
        if (accountingDevice == null) {
            accountingDevice = new AccountingDevice();
            accountingDevice.setSerialNumber(webAccountingDeviceVerifyModel.getSerialNumber());
            accountingDevice.setDeviceUUID(UUID.randomUUID());
            accountingDevice = accountingDeviceRepository.save(accountingDevice);
            accountingDevice.setVerificationDate(new Date());
        }
        return dozerBeanMapper.map(accountingDevice, WebAccountingDeviceVerifyModel.class);
    }

    @Override
    public WebAccountingDeviceModel addAccountingDeviceToUser(String serialNumber) {
        User currentUser = userService.getCurrentUser();
        AccountingDevice accountingDevice = accountingDeviceRepository.findBySerialNumber(serialNumber);
        accountingDevice.setOwner(currentUser);
        accountingDevice.setInstallationDate(new Date());
        accountingDevice = accountingDeviceRepository.save(accountingDevice);
        return dozerBeanMapper.map(accountingDevice, WebAccountingDeviceModel.class);
    }

    @Override
    public List<WebAccountingDeviceModel> getAccountingDevices() {
        User currentUser = userService.getCurrentUser();
        List<AccountingDevice> accountingDeviceList = currentUser.getAccountingDevices();
        return MappingHelper.map(accountingDeviceList, WebAccountingDeviceModel.class);
    }

    @Override
    public AccountingDevice getDeviceById(Long deviceId) {
        if (deviceId == null) {
            throw new IllegalArgumentException("Accounting device Id can't be null");
        }
        return accountingDeviceRepository.getOne(deviceId);
    }

    @Override
    public AccountingDevice createOrUpdateDevice(AccountingDevice accountingDevice) {
        if (accountingDevice == null) {
            throw new IllegalArgumentException("Accounting device can't be null");
        }

        return accountingDeviceRepository.save(accountingDevice);
    }

    @Override
    public WebAccountingDeviceModel getWebModelDeviceById(Long deviceId) {
        AccountingDevice accountingDevice = getDeviceById(deviceId);
        return dozerBeanMapper.map(accountingDevice, WebAccountingDeviceModel.class);
    }

    @Override
    public AccountingDevice getAccountingDeviceByUUID(UUID deviceUUID) {
        return accountingDeviceRepository.findByDeviceUUID(deviceUUID);
    }

    @Override
    public Boolean saveNewIndicationsReceivedFromDevice(WebAccountingServiceValueModel webAccountingServiceValueModel) {
        AccountingDevice accountingDevice = getAccountingDeviceByUUID(webAccountingServiceValueModel.getDeviceUUID());
        if(accountingDevice == null) {
            return Boolean.FALSE;
        }
        List<AccountingService> accountingServices = accountingDevice.getAccountingServices();
        for(AccountingIndicationValue accountingIndicationValue : webAccountingServiceValueModel.getAccountingIndicationValues()) {
            for(AccountingService accountingService : accountingServices) {
                if(accountingIndicationValue.getInputNumber().equals(accountingService.getInputNumber())) {
                    AccountingIndication accountingIndication = new AccountingIndication();
                    accountingIndication.setAccountingDate(accountingIndicationValue.getDateIndication());
                    accountingIndication.setCurrentAccounting(accountingIndicationValue.getCurrentIndication());
                    accountingIndication.setAccountingService(accountingService);
                    accountingIndicationService.addNewAccountingIndication(accountingIndication);
                }
            }
        }

        return Boolean.TRUE;
    }
}
