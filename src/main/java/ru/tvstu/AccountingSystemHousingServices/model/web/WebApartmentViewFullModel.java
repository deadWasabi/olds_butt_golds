package ru.tvstu.AccountingSystemHousingServices.model.web;

public class WebApartmentViewFullModel extends WebApartmentViewModel {
    private Integer square;

    private String description;

    private Integer numberResidents;

    private Integer numberBeneficiaries;

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
}
