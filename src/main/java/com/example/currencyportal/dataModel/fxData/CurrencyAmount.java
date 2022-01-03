package com.example.currencyportal.dataModel.fxData;

import com.sun.xml.txw2.annotation.XmlElement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CcyAmt")
@XmlAccessorType(XmlAccessType.FIELD)
@SuppressWarnings("unused")
public class CurrencyAmount {

    private String currency;
    private String amount;

    public CurrencyAmount() {
    }

    public CurrencyAmount(String currency, String amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    @XmlElement("Ccy")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    @XmlElement("Amt")
    public void setAmount(String amount) {
        this.amount = amount;
    }
}
