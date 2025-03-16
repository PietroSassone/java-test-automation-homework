package com.homework.test.automation.client;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;

import java.io.IOException;

import static com.homework.test.automation.client.JsonPlaceHolderClientApache.API_TEST_REQUEST_URL;

@Slf4j
@Component
public class JsonPlaceHolderClientOkHttp {

    @Autowired
    private OkHttpClient okHttpClient;

    public String getUsers(final int expectedStatusCode) {
        log.info("Executing HTTP request GET {} with Apache client.", API_TEST_REQUEST_URL);

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
