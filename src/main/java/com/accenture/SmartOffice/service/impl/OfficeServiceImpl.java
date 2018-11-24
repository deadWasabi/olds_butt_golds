package com.accenture.SmartOffice.service.impl;

import com.accenture.SmartOffice.model.web.WebOfficeModel;
import com.accenture.SmartOffice.model.web.WebUserSwapModel;
import com.accenture.SmartOffice.model.web.WebUserWorkspaceModel;
import org.springframework.stereotype.Service;
import com.accenture.SmartOffice.service.OfficeService;

@Service
public class OfficeServiceImpl implements OfficeService {

    @Override
    public WebOfficeModel getOfficeDetail(){
        //TODO
        return new WebOfficeModel();
    }

    @Override
    public void setUserWorkspace(WebUserWorkspaceModel webUserWorkspaceModel){
        //TODO
    }

    @Override
    public void deleteUserWorkspace(Long userWorkspaceId){
        //TODO
    }

    @Override
    public void swapUsers(WebUserSwapModel webUserSwapModel){
        //TODO
    }
}
