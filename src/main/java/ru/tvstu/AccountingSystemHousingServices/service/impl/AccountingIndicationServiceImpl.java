package ru.tvstu.AccountingSystemHousingServices.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tvstu.AccountingSystemHousingServices.dao.entity.AccountingIndication;
import ru.tvstu.AccountingSystemHousingServices.dao.repository.AccountingIndicationRepository;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebAccountingIndicationRequestModel;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebAccountingIndicationResponseModel;
import ru.tvstu.AccountingSystemHousingServices.service.AccountingIndicationService;
import ru.tvstu.AccountingSystemHousingServices.utilits.MappingHelper;

import java.util.List;

@Service
public class AccountingIndicationServiceImpl implements AccountingIndicationService {

    @Autowired
    private AccountingIndicationRepository accountingIndicationRepository;

    @Override
    public AccountingIndication addNewAccountingIndication(AccountingIndication accountingIndication) {
        return accountingIndicationRepository.save(accountingIndication);
    }

    @Override
    public List<WebAccountingIndicationResponseModel> getAccountingIndicationByServiceIdAndDateRange(WebAccountingIndicationRequestModel webAccountingIndicationRequestModel) {
        List<AccountingIndication> accountingIndications = accountingIndicationRepository.fingAccountingIndicationByServiceIdAndRangeDate(
                webAccountingIndicationRequestModel.getServiceId(),
                webAccountingIndicationRequestModel.getStartDate(),
                webAccountingIndicationRequestModel.getEndDate());
        return MappingHelper.map(accountingIndications, WebAccountingIndicationResponseModel.class);
    }
}
