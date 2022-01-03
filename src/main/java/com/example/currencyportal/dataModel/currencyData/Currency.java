package com.example.currencyportal.dataModel.currencyData;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
@XmlAccessorType (XmlAccessType.FIELD)
@SuppressWarnings("unused")
public class Currency {

        private String currency;
        private String description;

    public Currency() {
    }

    public Currency(String currency, String description) {
        this.currency = currency;
        this.description = description;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public String setDescription(String description) {
        return this.description = description;
    }

}
