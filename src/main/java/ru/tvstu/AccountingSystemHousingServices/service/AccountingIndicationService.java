package ru.tvstu.AccountingSystemHousingServices.service;

import ru.tvstu.AccountingSystemHousingServices.dao.entity.AccountingIndication;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebAccountingIndicationRequestModel;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebAccountingIndicationResponseModel;

import java.util.List;

public interface AccountingIndicationService {
    AccountingIndication addNewAccountingIndication(AccountingIndication accountingIndication);

    List<WebAccountingIndicationResponseModel> getAccountingIndicationByServiceIdAndDateRange(WebAccountingIndicationRequestModel webAccountingIndicationRequestModel);
}
