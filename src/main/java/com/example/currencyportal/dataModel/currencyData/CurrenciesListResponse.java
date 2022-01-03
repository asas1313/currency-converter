package com.example.currencyportal.dataModel.currencyData;

import com.sun.xml.txw2.annotation.XmlElement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Currencies")
@XmlAccessorType(XmlAccessType.FIELD)
@SuppressWarnings("unused")
public class CurrenciesListResponse {

        private List<Currency> item = new ArrayList<>();

        @XmlElement("item")
        public List<Currency> getItem() {
                return item;
        }

        public void setItem(List<Currency> item) {
                this.item = item;
        }

        public CurrenciesListResponse() {
        }

        public CurrenciesListResponse(List<Currency> item) {
                this.item = item;
        }
}
