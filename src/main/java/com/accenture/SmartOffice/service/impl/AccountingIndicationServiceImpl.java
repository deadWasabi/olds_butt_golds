package com.accenture.SmartOffice.service.impl;

import com.accenture.SmartOffice.dao.entity.AccountingIndication;
import com.accenture.SmartOffice.dao.repository.AccountingIndicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.accenture.SmartOffice.model.web.WebAccountingIndicationRequestModel;
import com.accenture.SmartOffice.model.web.WebAccountingIndicationResponseModel;
import com.accenture.SmartOffice.service.AccountingIndicationService;
import com.accenture.SmartOffice.utilits.MappingHelper;

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
