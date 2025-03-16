package com.homework.test.automation.tests.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.homework.test.automation.client.JsonPlaceHolderClientApache;
import com.homework.test.automation.client.JsonPlaceHolderClientOkHttp;
import com.homework.test.automation.config.SpringConfig;
import com.homework.test.automation.util.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(classes = SpringConfig.class)
public class JsonPlaceholderApiTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private JsonPlaceHolderClientApache apacheClient;

    @Autowired
    private JsonPlaceHolderClientOkHttp okHttpClient;

    @Autowired
    private JsonHelper jsonHelper;

    @Test
    public void testGetUsersWithApacheClient() {
        // Given - When
        final JsonNode jsonResponse = jsonHelper.parseJsonString(apacheClient.getUsers(200));

        // Then
        jsonHelper.logNamesAndEmailsFromJson(jsonResponse);
        jsonHelper.assertFirstEmail(jsonResponse);
    }

    @Test
    public void testGetUsersWithOkHttpClient() {
        // Given - When
        final JsonNode jsonResponse = jsonHelper.parseJsonString(okHttpClient.getUsers(200));

        // Then
        jsonHelper.logNamesAndEmailsFromJson(jsonResponse);
        jsonHelper.assertFirstEmail(jsonResponse);
    }
}
