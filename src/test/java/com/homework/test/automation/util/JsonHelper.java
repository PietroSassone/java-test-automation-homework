package com.homework.test.automation.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;

@Slf4j
@Component
public class JsonHelper {

    @Autowired
    private ObjectMapper objectMapper;

    public JsonNode parseJsonString(final String jsonToParse) {
        try {
            return objectMapper.readTree(jsonToParse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void logNamesAndEmailsFromJson(final JsonNode jsonNode) {
        log.info("Printing names and emails.");
        jsonNode.forEach(user -> log.info("{}|{}", user.get("name").asText(), user.get("email").asText()));
    }

    public void assertFirstEmail(final JsonNode jsonNode) {
        Assert.assertTrue(jsonNode.get(0).get("email").asText().contains("@"), "First email does not contain '@'.");
    }
}
