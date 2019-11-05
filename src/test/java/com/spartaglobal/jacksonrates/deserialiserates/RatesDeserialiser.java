package com.spartaglobal.jacksonrates.deserialiserates;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class RatesDeserialiser {

    public RatesDTO ratesMapped;

    public RatesDeserialiser(String fileLocation) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            ratesMapped=objectMapper.readValue(new File(fileLocation),RatesDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
