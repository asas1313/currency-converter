package com.example.currencyportal.service;

import com.example.currencyportal.dataModel.fxData.CurrencyItem;
import com.example.currencyportal.model.FxRateModel;
import com.example.currencyportal.repository.ApiRepository;
import com.example.currencyportal.repository.CurrencyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@SuppressWarnings("unused")
public class FxRatesService {

    private final ApiRepository repository;
    private final CurrencyRepository fxRateRepository;

    public FxRatesService(ApiRepository repository1, CurrencyRepository fxRateRepository1) {
        this.repository = repository1;
        this.fxRateRepository = fxRateRepository1;
    }

    public void InitializeRates() {
        List<CurrencyItem> currentRates = repository.getCurrentCurrencyRates();
        List<FxRateModel> fxRates = new ArrayList<>();
        for (CurrencyItem item: currentRates) {
            fxRates.add(new FxRateModel(
                    item.getCurrencyAmount().get(1).getCurrency(),
                    item.getCurrencyAmount().get(1).getAmount()
            ));
        }
        fxRateRepository.deleteAll();
        fxRateRepository.saveAll(fxRates);

    }
}
