package com.homework.test.automation.client;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;

import java.io.IOException;

import static com.homework.test.automation.client.JsonPlaceHolderClientApache.API_TEST_REQUEST_URL;

@Component
public class JsonPlaceHolderClientOkHttp {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonPlaceHolderClientOkHttp.class);

    @Autowired
    private OkHttpClient okHttpClient;

    public String getUsers(final int expectedStatusCode) {
        LOGGER.info("Executing HTTP request GET {} with Apache client.", API_TEST_REQUEST_URL);

        final Request request = new Request.Builder()
                .url(API_TEST_REQUEST_URL)
                .build();

        String responseAsString;
        try (Response response = okHttpClient.newCall(request).execute()) {

            Assert.assertEquals(response.code(), expectedStatusCode, "Unexpected status code.");
            responseAsString = response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return responseAsString;
    }
}
