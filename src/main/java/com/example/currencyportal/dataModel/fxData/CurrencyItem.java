package com.example.currencyportal.dataModel.fxData;

import com.sun.xml.txw2.annotation.XmlElement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "FxRate")
@XmlAccessorType(XmlAccessType.FIELD)
@SuppressWarnings("unused")
public class CurrencyItem {

    private String type;
    private String date;
    List<CurrencyAmount> currencyAmount;

    public CurrencyItem() {
    }

    public CurrencyItem(String type, String date, List<CurrencyAmount> currencyAmount) {
        this.type = type;
        this.date = date;
        this.currencyAmount = currencyAmount;
    }

    public String getType() {
        return type;
    }

    @XmlElement("Tp")
    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    @XmlElement("Dt")
    public void setDate(String date) {
        this.date = date;
    }

    public List<CurrencyAmount> getCurrencyAmount() {
        return currencyAmount;
    }

    @XmlElement("CcyAmt")
    public void setCurrencyAmount(List<CurrencyAmount> currencyAmount) {
        this.currencyAmount = currencyAmount;
    }
}
