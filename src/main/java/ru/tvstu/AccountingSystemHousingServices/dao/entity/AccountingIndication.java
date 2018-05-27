package ru.tvstu.AccountingSystemHousingServices.dao.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "accounting_indication")
public class AccountingIndication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="accounting_service_id", nullable=false)
    private AccountingService accountingService;

    @NotNull
    private Long currentAccounting;

    @NotNull
    private Date accountingDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountingService getAccountingService() {
        return accountingService;
    }

    public void setAccountingService(AccountingService accountingService) {
        this.accountingService = accountingService;
    }

    public Long getCurrentAccounting() {
        return currentAccounting;
    }

    public void setCurrentAccounting(Long currentAccounting) {
        this.currentAccounting = currentAccounting;
    }

    public Date getAccountingDate() {
        return accountingDate;
    }

    public void setAccountingDate(Date accountingDate) {
        this.accountingDate = accountingDate;
    }
}
