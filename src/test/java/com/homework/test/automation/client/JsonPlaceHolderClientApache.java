package com.homework.test.automation.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.springframework.stereotype.Component;
import org.testng.Assert;

@Slf4j
@Component
public class JsonPlaceHolderClientApache {
    public static final String API_TEST_REQUEST_URL = "https://jsonplaceholder.typicode.com/users";

    public String getUsers(final int expectedStatusCode) {
        String responseAsString;
        log.info("Executing HTTP request GET {} with Apache client.", API_TEST_REQUEST_URL);

        try (CloseableHttpClient apacheHttpClient = HttpClients.createDefault()) {
            responseAsString = apacheHttpClient.execute(new HttpGet(API_TEST_REQUEST_URL),
                    response -> {
                        Assert.assertEquals(response.getCode(), expectedStatusCode, "Unexpected status code.");
                        return EntityUtils.toString(response.getEntity());
                    }
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return responseAsString;
    }
}
