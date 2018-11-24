package com.accenture.SmartOffice.service;

import com.accenture.SmartOffice.model.web.WebOfficeModel;
import com.accenture.SmartOffice.model.web.WebUserSwapModel;
import com.accenture.SmartOffice.model.web.WebUserWorkspaceModel;

public interface OfficeService {

    WebOfficeModel getOfficeDetail();

    void setUserWorkspace(WebUserWorkspaceModel webUserWorkspaceModel);

    void deleteUserWorkspace(Long userWorkspaceId);

    void swapUsers(WebUserSwapModel webUserSwapModel);
}