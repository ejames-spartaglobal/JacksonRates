package com.spartaglobal.jacksonrates;

import com.spartaglobal.jacksonrates.HTTPServices.FixerHTTPClient;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestObject {

private static FixerHTTPClient fixerHttpClient = new FixerHTTPClient();

@BeforeClass
    public static void setup(){
    fixerHttpClient.executeLatestRatesGetRequest();
}

@Test
    public void test(){
    System.out.println(fixerHttpClient.getFixerLatestRatesJSONString());
}

}
