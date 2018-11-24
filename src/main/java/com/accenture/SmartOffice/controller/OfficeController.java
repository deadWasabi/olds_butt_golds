package com.accenture.SmartOffice.controller;

import com.accenture.SmartOffice.model.web.WebOfficeModel;
import com.accenture.SmartOffice.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.accenture.SmartOffice.model.web.WebUserSwapModel;
import com.accenture.SmartOffice.model.web.WebUserWorkspaceModel;

@CrossOrigin
@RestController
@RequestMapping(value = "/office")
public class OfficeController {

    @Autowired
    private OfficeService officeService;

    private static final String DETAILS = "/details";
    private static final String SET_USER = "/setUser";
    private static final String DELETE_USER = "/deleteUser";
    private static final String SWAP_USERS = "/swapUsers";

    @ResponseBody
    @GetMapping(value = DETAILS)
    public WebOfficeModel getOfficeDetail() {
        return officeService.getOfficeDetail();
    }

    @ResponseBody
    @PostMapping(value = SET_USER)
    public void setUserWorkspace(@RequestBody WebUserWorkspaceModel webUserWorkspaceModel) {
        officeService.setUserWorkspace(webUserWorkspaceModel);
    }

    @ResponseBody
    @PostMapping(value = DELETE_USER)
    public void deleteUserWorkspace(@PathVariable Long userWorkspaceId) {
        officeService.deleteUserWorkspace(userWorkspaceId);
    }

    @ResponseBody
    @PostMapping(value = SWAP_USERS)
    public void swapUsers(@RequestBody WebUserSwapModel webUserSwapModel) {
        officeService.swapUsers(webUserSwapModel);
    }

}
