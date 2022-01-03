package com.example.currencyportal.config;

import com.example.currencyportal.repository.ApiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unused")
public class ShedulerRunnerComponent {

    @Autowired
    ApiRepository repository;

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        repository.setDailyJob();
        System.out.println("Kasdienis valiutų kursų gavimas nustatytas.");
    }
}