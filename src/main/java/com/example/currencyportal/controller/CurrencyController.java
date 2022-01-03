package com.example.currencyportal.controller;

import com.example.currencyportal.dataModel.currencyData.Currency;
import com.example.currencyportal.dataModel.fxData.CurrencyFormModel;
import com.example.currencyportal.repository.ApiRepository;
import com.example.currencyportal.repository.CurrencyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
@SuppressWarnings("unused")
public class CurrencyController {

    private final ApiRepository repository;
    private final CurrencyRepository fxRateRepository;
    private final CurrencyFormModel formModel;

    @ModelAttribute("currencies")
    public List<Currency> getCurrencies() {
        return repository.getCurrencyItems();
    }

    @ModelAttribute("formModel")
    public CurrencyFormModel getHistoryFormItems() {
        return formModel;
    }

    public CurrencyController(ApiRepository repository, CurrencyRepository fxRateRepository) {
        this.repository = repository;
        this.fxRateRepository = fxRateRepository;
        formModel = new CurrencyFormModel();

    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("formModel", formModel);
        return "index";
    }

    @PostMapping
    public String showRateHistory(
            @RequestParam(value="selectedCurrency", required = false) String selectedCurrency,
            @RequestParam(value="selectedCurrencyText", required = false) String selectedCurrencyText,
            @RequestParam(value="dtFrom", required = false) String dtFrom,
            @RequestParam(value="dtTo", required = false) String dtTo,
            Model model) {

        formModel.setSelectedCurrency(selectedCurrency);
        formModel.setSelectedCurrencyText(selectedCurrencyText);
        formModel.setDtFrom(dtFrom);
        formModel.setDtTo(dtTo);
        this.formModel.setHistoryFormItems(
                repository.getHistoryForCurrency( selectedCurrency, dtFrom, dtTo)
        );

        return "redirect:/";
    }

    @PostMapping(value = "/exchange")
    public String showCurrentFxRate(
            @RequestParam(value="exchangeCurrency", required = false) String exchangeCurrency,
            @RequestParam(value="exchangeCurrencyText", required = false) String exchangeCurrencyText,
            @RequestParam(value="initialCurrencyAmount", required = false) String initialCurrencyAmount,
            Model model) {
        if (!exchangeCurrency.isEmpty() && !initialCurrencyAmount.isEmpty()) {
            formModel.setExchangeCurrency(exchangeCurrency);
            formModel.setExchangeCurrencyText(exchangeCurrencyText);
            formModel.setInitialCurrencyAmount(Double.parseDouble(initialCurrencyAmount));
            formModel.setCurrentFxRateForCurrency(Double.parseDouble(
                    fxRateRepository.findByCurrency(
                                    formModel.getExchangeCurrency())
                            .getAmount()));
            this.formModel.setCalculatedAmount(String.format("%.2f",
                    formModel.getInitialCurrencyAmount() * formModel.getCurrentFxRateForCurrency()));
        }
        return "redirect:/";
    }
}
