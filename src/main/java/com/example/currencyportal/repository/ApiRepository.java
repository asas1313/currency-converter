package com.example.currencyportal.repository;

import com.example.currencyportal.dataModel.fxData.FxRatesResponse;
import com.example.currencyportal.dataModel.currencyData.CurrenciesListResponse;
import com.example.currencyportal.dataModel.fxData.CurrencyItem;
import com.example.currencyportal.dataModel.currencyData.Currency;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

@Repository
public class ApiRepository {

    public ApiRepository() {
    }

    public List<Currency> getCurrencyItems() {
        String uri = "https://www.lb.lt/webservices/ExchangeRates/ExchangeRates.asmx/getListOfCurrencies";
        CurrenciesListResponse result;

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CurrenciesListResponse.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            result = (CurrenciesListResponse) jaxbUnmarshaller.unmarshal(new URL(uri));

            return result.getItem();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CurrencyItem> getHistoryForCurrency(String currency, String dtFrom, String dtTo) {

        String uri = "http://www.lb.lt/webservices/FxRates/FxRates.asmx/getFxRatesForCurrency";
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("tp", "EU");
        map.add("ccy", currency);
        map.add("dtFrom", dtFrom);
        map.add("dtTo", dtTo);

        return _getFxRatesFromRest(uri, map);
    }

    public List<CurrencyItem> getCurrentCurrencyRates() {

        String uri = "http://www.lb.lt/webservices/FxRates/FxRates.asmx/getCurrentFxRates";
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("tp", "EU");

        return _getFxRatesFromRest(uri, map);
    }

    private List<CurrencyItem> _getFxRatesFromRest(String uri, MultiValueMap<String, Object> map) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> restResult = restTemplate.postForEntity(uri, map, String.class);
            File file = null;
            if (restResult.getStatusCode() == HttpStatus.OK) {
                try {
                    Path temp = Files.createTempFile("temp", ".xml");
                    file = temp.toFile();
                    FileWriter writer = new FileWriter(file);
                    writer.write(Objects.requireNonNull(restResult.getBody()));
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (file != null) {
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    Document document = builder.parse(new File(file.getPath()));
                    document.getDocumentElement().normalize();
                    FxRatesResponse fxRatesResponse = new FxRatesResponse();
                    fxRatesResponse.buildFromXmlDocument(document);
                    return fxRatesResponse.getItem();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void setDailyJob() {

        String uri = "http://localhost:8083/schedule/daily-fx";
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.postForEntity(uri, null, String.class);

    }
}
