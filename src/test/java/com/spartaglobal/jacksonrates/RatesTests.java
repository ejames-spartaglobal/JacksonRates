package com.spartaglobal.jacksonrates;

import com.spartaglobal.jacksonrates.deserialiserates.RatesDTO;
import com.spartaglobal.jacksonrates.deserialiserates.RatesDeserialiser;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

public class RatesTests {

    private static RatesDeserialiser rates;




    @BeforeClass
    public static void setup(){
        rates=new RatesDeserialiser("resources/rates.json");

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
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        String formatted = format.format(timestampDate);
        Assert.assertEquals(rates.ratesMapped.getDate(),formatted);
    }

    @Test
    public void checkRatesQuantity(){

        Assert.assertEquals(rates.ratesMapped.getRateCount(),rates.ratesMapped.getRates().size());
    }

    @Test
    public void checkDate(){
        Assert.assertEquals("2018-10-10", rates.ratesMapped.getDate());
    }

    @Test
    public void checkBase(){
        Assert.assertEquals("EUR",rates.ratesMapped.getBase());
    }
}
