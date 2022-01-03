package com.example.currencyportal.model;

import javax.persistence.*;

@Entity
@Table(name="FX_RATES")
@SuppressWarnings("unused")
public class FxRateModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "currency")
    private String currency;

    @Column(name = "amount")
    private String amount;

    public FxRateModel() {
    }

    public FxRateModel(String currency, String amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}
