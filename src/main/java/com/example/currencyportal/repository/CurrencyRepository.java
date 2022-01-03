package com.example.currencyportal.repository;
import com.example.currencyportal.model.FxRateModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<FxRateModel, String> {

        FxRateModel findByCurrency(String currency);
    }
