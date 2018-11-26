package com.accenture.SmartOffice.controller;

import com.accenture.SmartOffice.model.web.WebOfficeModelViewModel;
import com.accenture.SmartOffice.model.web.WebWorkspaceModel;
import com.accenture.SmartOffice.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.accenture.SmartOffice.model.web.WebUserSwapModel;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/office")
public class OfficeController {

    @Autowired
    private OfficeService officeService;

    private static final String VIEW = "/view";
    private static final String DETAILS = "/details/{id}";
    private static final String SET_USER = "/setUser";
    private static final String DELETE_USER = "/deleteUser/{id}";
    private static final String SWAP_USERS = "/swapUsers";

    @ResponseBody
    @GetMapping(value = DETAILS)
    public WebOfficeModelViewModel getOfficeDetail(@PathVariable Long id) {
        return officeService.getOfficeDetail(id);
    }

    @ResponseBody
    @PostMapping(value = SET_USER)
    public void setUserWorkspace(@RequestBody WebWorkspaceModel webUserWorkspaceModel) {
        officeService.setUserWorkspace(webUserWorkspaceModel);
    }

    @ResponseBody
    @GetMapping(value = VIEW)
    public List<WebOfficeModelViewModel> getOffices() {
        return officeService.getOffices();
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
