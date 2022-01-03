package com.example.currencyportal.config;

import com.example.currencyportal.service.FxRatesService;
import org.jetbrains.annotations.NotNull;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unused")
public class FxRatesImportJob extends QuartzJobBean {

    @Autowired
    private FxRatesService fxRatesService;

    @Override
    protected void executeInternal(@NotNull JobExecutionContext context) {
        fxRatesService.InitializeRates();
    }
}
