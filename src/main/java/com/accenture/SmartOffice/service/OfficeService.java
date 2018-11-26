package com.accenture.SmartOffice.service;

import com.accenture.SmartOffice.model.web.WebOfficeModelViewModel;
import com.accenture.SmartOffice.model.web.WebUserSwapModel;
import com.accenture.SmartOffice.model.web.WebWorkspaceModel;

import java.util.List;

public interface OfficeService {

    WebOfficeModelViewModel getOfficeDetail(Long id);

    void setUserWorkspace(WebWorkspaceModel webWorkspaceModel);

    void deleteUserWorkspace(Long userWorkspaceId);

    void swapUsers(WebUserSwapModel webUserSwapModel);

    List<WebOfficeModelViewModel> getOffices();
}