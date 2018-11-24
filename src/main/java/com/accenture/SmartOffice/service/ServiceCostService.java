package com.accenture.SmartOffice.service;

import com.accenture.SmartOffice.model.web.WebServiceCostModel;

import java.util.List;

public interface ServiceCostService {

    List<WebServiceCostModel> getServiceCostByApartmentId(Long apartmentId);

    WebServiceCostModel getServiceCostById(Long id);

    WebServiceCostModel createOrUpdateServiceCost(WebServiceCostModel webServiceCostModel);
}
