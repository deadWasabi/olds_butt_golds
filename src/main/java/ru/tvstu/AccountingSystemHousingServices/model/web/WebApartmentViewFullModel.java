package ru.tvstu.AccountingSystemHousingServices.model.web;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WebApartmentViewFullModel extends WebApartmentViewModel {
    private Integer square;

    private String description;

    private Integer numberResidents;

    private Integer numberBeneficiaries;

    private List<WebAccountingDeviceModel> webAccountingDeviceModelList = new ArrayList<>();

    public Integer getSquare() {
        return square;
    }

    public void setSquare(Integer square) {
        this.square = square;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumberResidents() {
        return numberResidents;
    }

    public void setNumberResidents(Integer numberResidents) {
        this.numberResidents = numberResidents;
    }

    public Integer getNumberBeneficiaries() {
        return numberBeneficiaries;
    }

    public void setNumberBeneficiaries(Integer numberBeneficiaries) {
        this.numberBeneficiaries = numberBeneficiaries;
    }

    public List<WebAccountingDeviceModel> getWebAccountingDeviceModelList() {
        return webAccountingDeviceModelList;
    }

    public void setWebAccountingDeviceModelList(List<WebAccountingDeviceModel> webAccountingDeviceModelList) {
        this.webAccountingDeviceModelList = webAccountingDeviceModelList;
    }
}
