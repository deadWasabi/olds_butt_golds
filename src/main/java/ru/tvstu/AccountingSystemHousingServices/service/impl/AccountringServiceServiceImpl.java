package ru.tvstu.AccountingSystemHousingServices.service.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tvstu.AccountingSystemHousingServices.customException.IllegalArgumentException;
import ru.tvstu.AccountingSystemHousingServices.customException.ObjectNotFoundExeption;
import ru.tvstu.AccountingSystemHousingServices.dao.entity.AccountingDevice;
import ru.tvstu.AccountingSystemHousingServices.dao.entity.AccountingService;
import ru.tvstu.AccountingSystemHousingServices.dao.entity.ServiceType;
import ru.tvstu.AccountingSystemHousingServices.dao.entity.User;
import ru.tvstu.AccountingSystemHousingServices.dao.repository.AccountingServiceRepository;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebAccountingServiceModel;
import ru.tvstu.AccountingSystemHousingServices.service.AccountingDeviceService;
import ru.tvstu.AccountingSystemHousingServices.service.AccountingServiceService;
import ru.tvstu.AccountingSystemHousingServices.service.ServiceTypeService;
import ru.tvstu.AccountingSystemHousingServices.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountringServiceServiceImpl implements AccountingServiceService {

    @Autowired
    private AccountingServiceRepository accountingServiceRepository;

    @Autowired
    private AccountingDeviceService accountingDeviceService;

    @Autowired
    private UserService userService;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @Autowired
    private ServiceTypeService serviceTypeService;

    @Override
    public List<WebAccountingServiceModel> getAccountingServiceByDeviceId(Long deviceId) {
        AccountingDevice accountingDevice = accountingDeviceService.getDeviceById(deviceId);
        if (!accountingDevice.getOwner().getId().equals(userService.getCurrentUser().getId())) {
            throw new ObjectNotFoundExeption("Account device not fount by device id - " + deviceId + "for current user");
        }

        List<AccountingService> accountingServices = accountingServiceRepository.findByDeviceId(deviceId);
        List<WebAccountingServiceModel> webAccountingServiceModels = new ArrayList<>();
        for (AccountingService accountingService : accountingServices) {
            webAccountingServiceModels.add(mapAccountingServiceToWebAccountingServiceModel(accountingService));
        }
        return webAccountingServiceModels;
    }

    @Override
    public WebAccountingServiceModel addAccountingService(WebAccountingServiceModel webAccountingServiceModel) {
        User user = userService.getCurrentUser();
        AccountingDevice accountingDevice = accountingDeviceService.getDeviceById(webAccountingServiceModel.getAccountingDeviceId());
        if (accountingDevice == null || !accountingDevice.getOwner().getId().equals(user.getId())) {
            throw new ObjectNotFoundExeption("Accounting device not found");
        }

        ServiceType serviceType = serviceTypeService.getServiceTypeById(webAccountingServiceModel.getServiceTypeId());

        if (serviceType == null) {
            throw new ObjectNotFoundExeption("Service type not found");
        }

        if (webAccountingServiceModel.getInputNumber() == null) {
            throw new IllegalArgumentException("Field inputNumber can't be null");
        }
        AccountingService accountingService = new AccountingService();
        accountingService.setName(webAccountingServiceModel.getName());
        accountingService.setDescription(webAccountingServiceModel.getDescription());
        accountingService.setInputNumber(webAccountingServiceModel.getInputNumber());
        accountingService.setAccountingDevice(accountingDevice);
        accountingService.setServiceType(serviceType);
        accountingService = accountingServiceRepository.save(accountingService);
        return mapAccountingServiceToWebAccountingServiceModel(accountingService);
    }

    private WebAccountingServiceModel mapAccountingServiceToWebAccountingServiceModel(AccountingService accountingService) {
        WebAccountingServiceModel webAccountingServiceModel = new WebAccountingServiceModel();
        dozerBeanMapper.map(accountingService, webAccountingServiceModel);
        webAccountingServiceModel.setServiceTypeId(accountingService.getServiceType().getId());
        webAccountingServiceModel.setAccountingDeviceId(accountingService.getAccountingDevice().getId());
        return webAccountingServiceModel;
    }

    @Override
    public WebAccountingServiceModel updateAccountingService(WebAccountingServiceModel webAccountingServiceModel) {
        AccountingService accountingService = accountingServiceRepository.findOne(webAccountingServiceModel.getId());
        User user = userService.getCurrentUser();

        if (accountingService == null || !accountingService.getAccountingDevice().getOwner().getId().equals(user.getId())) {
            throw new ObjectNotFoundExeption("Accounting service not found");
        }

        if (webAccountingServiceModel.getInputNumber() == null) {
            throw new IllegalArgumentException("Field inputNumber can't be null");
        }

        accountingService.setInputNumber(webAccountingServiceModel.getInputNumber());
        accountingService.setName(webAccountingServiceModel.getName());
        accountingService.setDescription(webAccountingServiceModel.getDescription());

        accountingService = accountingServiceRepository.save(accountingService);

        return mapAccountingServiceToWebAccountingServiceModel(accountingService);
    }


}
