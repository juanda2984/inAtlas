package com.inatlas.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DoubleTwoDecimalSerializer extends JsonSerializer<Double> {

	@Override
	public void serialize(Double value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		if (value != null) {
            BigDecimal roundedValue = BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP);
            gen.writeNumber(roundedValue);
        }
	}

}
