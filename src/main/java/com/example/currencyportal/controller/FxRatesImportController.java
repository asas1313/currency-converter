package com.example.currencyportal.controller;

import com.example.currencyportal.config.FxRatesImportJob;
import com.example.currencyportal.dataModel.schedulerPayload.FxRatesResponse;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@RestController
@SuppressWarnings("unused")
public class FxRatesImportController {

    @Autowired
    private Scheduler scheduler;

    @PostMapping(value = "/schedule/daily-fx")
    public ResponseEntity<String> scheduleFxRates () {
        try {
            JobDetail jobDetail = buildJobDetail();
            Trigger trigger = buildTrigger(jobDetail);

            scheduler.scheduleJob(jobDetail, trigger);

            FxRatesResponse fxRatesResponse = new FxRatesResponse(true,
                    jobDetail.getKey().getName(),
                    jobDetail.getKey().getGroup(),
                    "Automatinis darbas nustatytas!");

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(fxRatesResponse.toString());

        } catch (SchedulerException se) {
            System.out.println("Nustatant automatinį darbą įvyko klaida: " + se);
            FxRatesResponse fxRatesResponse = new FxRatesResponse(false,
                    "Nustatant automatinį darbą įvyko klaida. Pabandykite dar kartą vėliau!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(fxRatesResponse.toString());
        }
    }

    private JobDetail buildJobDetail() {

        return JobBuilder.newJob(FxRatesImportJob.class)
                .withIdentity(UUID.randomUUID().toString(), "daily-Fx-rates-import")
                .withDescription("Get daily Fx Rates from LB")
                .storeDurably()
                .build();
    }

    private Trigger buildTrigger(JobDetail jobDetail) {

        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "daily-fx-rates-triggers")
                .withDescription("Get Daily Fx Rates Trigger")
                .startAt(Date.from(Instant.now()))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(168))
                .build();
    }

}
