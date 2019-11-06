package com.spartaglobal.jacksonrates;

import com.spartaglobal.jacksonrates.HTTPServices.FixerHTTPClient;
import com.spartaglobal.jacksonrates.deserialiserates.RatesDeserialiser;
import com.spartaglobal.jacksonrates.config.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class RatesTests {

    private static RatesDeserialiser rates;
    private static FixerHTTPClient fixerHTTPClient = new FixerHTTPClient();



    @BeforeClass
    public static void setup(){
        fixerHTTPClient.executeLatestRatesGetRequest();
        rates=new RatesDeserialiser(fixerHTTPClient.getFixerLatestRatesJSONString());

    }

    @Test
    public void testSuccessIsTrue(){
        Assert.assertTrue(rates.ratesMapped.success);
    }

    @Test
    public void testEpochDate(){
        long epochTime=rates.ratesMapped.getTimestamp();
        long fullDate=epochTime * 1000L;
        Date timestampDate = new Date(fullDate);
        DateFormat format  =new SimpleDateFormat("yyyy-MM-dd");
//        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        String formatted = format.format(timestampDate);
        Date currentDate = new Date(System.currentTimeMillis());
        String currentFormatted=format.format(currentDate);
        Assert.assertEquals(currentFormatted,formatted);
    }

    @Test
    public void checkRatesQuantity(){

        Assert.assertEquals(rates.ratesMapped.getRateCount(),rates.ratesMapped.getRates().size());
    }

//    @Test
//    public void checkDate(){
//        Assert.assertEquals(rates, rates.ratesMapped.getDate());
//    }

    @Test
    public void checkBase(){
        Assert.assertEquals(rates.ratesMapped.getRates().get("EUR"),rates.ratesMapped.getEuro(),0);
    }
}
