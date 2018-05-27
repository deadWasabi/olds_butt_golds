package ru.tvstu.AccountingSystemHousingServices.service.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.tvstu.AccountingSystemHousingServices.customException.IllegalArgumentException;
import ru.tvstu.AccountingSystemHousingServices.dao.entity.*;
import ru.tvstu.AccountingSystemHousingServices.dao.repository.AccountingDeviceRepository;
import ru.tvstu.AccountingSystemHousingServices.model.AccountingIndicationValue;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebAccountingDeviceModel;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebAccountingDeviceVerifyModel;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebAccountingServiceValueModel;
import ru.tvstu.AccountingSystemHousingServices.service.AccountingDeviceService;
import ru.tvstu.AccountingSystemHousingServices.service.AccountingIndicationService;
import ru.tvstu.AccountingSystemHousingServices.service.UserService;
import ru.tvstu.AccountingSystemHousingServices.utilits.MappingHelper;

import javax.persistence.Access;
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
