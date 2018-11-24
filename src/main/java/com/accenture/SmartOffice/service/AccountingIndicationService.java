package com.accenture.SmartOffice.service;

import com.accenture.SmartOffice.dao.entity.AccountingIndication;
import com.accenture.SmartOffice.model.web.WebAccountingIndicationRequestModel;
import com.accenture.SmartOffice.model.web.WebAccountingIndicationResponseModel;

import java.util.List;

public interface AccountingIndicationService {
    AccountingIndication addNewAccountingIndication(AccountingIndication accountingIndication);

    List<WebAccountingIndicationResponseModel> getAccountingIndicationByServiceIdAndDateRange(WebAccountingIndicationRequestModel webAccountingIndicationRequestModel);
}
