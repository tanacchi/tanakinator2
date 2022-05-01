package com.example.tanakinator2.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;

@Disabled
public class UnitTestUtil {
    public static String entity2JsonTest(Object entity) throws Exception {
        return new ObjectMapper().writeValueAsString(entity);
    }
}
