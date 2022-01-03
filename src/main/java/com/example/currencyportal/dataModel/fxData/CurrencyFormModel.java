package com.example.currencyportal.dataModel.fxData;

import com.example.currencyportal.dataModel.currencyData.Currency;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class CurrencyFormModel {
    private List<Currency> currencies = new ArrayList<>();
    private List<CurrencyItem> historyFormItems = new ArrayList<>();
    private String selectedCurrency;
    private String selectedCurrencyText;
    private String dtFrom;
    private String dtTo;
    private double initialCurrencyAmount = 0.0;
    private double currentFxRateForCurrency = 0.0;
    private String exchangeCurrency;
    private String exchangeCurrencyText;
    private String calculatedAmount = "";

    public CurrencyFormModel() {
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public List<CurrencyItem> getHistoryFormItems() {
        return historyFormItems;
    }

    public void setHistoryFormItems(List<CurrencyItem> historyFormItems) {
        this.historyFormItems = historyFormItems;
    }

    public String getSelectedCurrency() {
        return selectedCurrency;
    }

    public void setSelectedCurrency(String selectedCurrency) {
        this.selectedCurrency = selectedCurrency;
    }

    public String getSelectedCurrencyText() {
        return selectedCurrencyText;
    }

    public void setSelectedCurrencyText(String selectedCurrencyText) {
        this.selectedCurrencyText = selectedCurrencyText;
    }

    public String getDtFrom() {
        return dtFrom;
    }

    public void setDtFrom(String dtFrom) {
        this.dtFrom = dtFrom;
    }

    public String getDtTo() {
        return dtTo;
    }

    public void setDtTo(String dtTo) {
        this.dtTo = dtTo;
    }

    public double getInitialCurrencyAmount() {
        return initialCurrencyAmount;
    }

    public void setInitialCurrencyAmount(double initialCurrencyAmount) {
        this.initialCurrencyAmount = initialCurrencyAmount;
    }

    public double getCurrentFxRateForCurrency() {
        return currentFxRateForCurrency;
    }

    public void setCurrentFxRateForCurrency(double currentFxRateForCurrency) {
        this.currentFxRateForCurrency = currentFxRateForCurrency;
    }

    public String getExchangeCurrency() {
        return exchangeCurrency;
    }

    public void setExchangeCurrency(String exchangeCurrency) {
        this.exchangeCurrency = exchangeCurrency;
    }

    public String getExchangeCurrencyText() {
        return exchangeCurrencyText;
    }

    public void setExchangeCurrencyText(String exchangeCurrencyText) {
        this.exchangeCurrencyText = exchangeCurrencyText;
    }

    public String getCalculatedAmount() {
        return calculatedAmount;
    }

    public void setCalculatedAmount(String calculatedAmount) {
        this.calculatedAmount = calculatedAmount;
    }
}
