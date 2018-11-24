package ru.tvstu.AccountingSystemHousingServices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebOfficeModel;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebUserSwapModel;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebUserWorkspaceModel;
import ru.tvstu.AccountingSystemHousingServices.service.OfficeService;

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
//      TODO
//          return officeService.getOfficeDetail();
        throw new UnsupportedOperationException();
    }

    @ResponseBody
    @PostMapping(value = SET_USER)
    public void setUserWorkspace(@RequestBody WebUserWorkspaceModel webUserWorkspaceModel) {
//      TODO
//          officeService.setUserWorkspace(webUserWorkspaceModel);
        throw new UnsupportedOperationException();
    }

    @ResponseBody
    @PostMapping(value = DELETE_USER)
    public void deleteUserWorkspace(@RequestBody WebUserWorkspaceModel webUserWorkspaceModel) {
//      TODO
//          officeService.deleteUserWorkspace(webUserWorkspaceModel);
        throw new UnsupportedOperationException();
    }

    @ResponseBody
    @PostMapping(value = SWAP_USERS)
    public void swapUsers(@RequestBody WebUserSwapModel webUserSwapModel) {
//      TODO
//          officeService.swapUsers(webUserSwapModel);
        throw new UnsupportedOperationException();
    }

}
