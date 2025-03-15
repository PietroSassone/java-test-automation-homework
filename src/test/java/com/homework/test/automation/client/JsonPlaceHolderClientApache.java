package com.homework.test.automation.client;

import com.homework.test.automation.util.JsonHelper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.testng.Assert;

import java.io.IOException;
import java.util.Objects;

@Component
public class JsonPlaceHolderClientApache {
    public static final String API_TEST_REQUEST_URL = "https://jsonplaceholder.typicode.com/users";
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonPlaceHolderClientApache.class);

    public String getUsers(final int expectedStatusCode) {
        String responseAsString = "";
        LOGGER.info("Executing HTTP request GET {} with Apache client.", API_TEST_REQUEST_URL);

        try (CloseableHttpClient apacheHttpClient = HttpClients.createDefault();
             CloseableHttpResponse response = apacheHttpClient.execute(new HttpGet(API_TEST_REQUEST_URL))) {

            Assert.assertEquals(response.getStatusLine().getStatusCode(), expectedStatusCode, "Unexpected status code.");

            HttpEntity entity = response.getEntity();

            if (Objects.nonNull(entity)) {
                responseAsString = EntityUtils.toString(entity);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return responseAsString;
    }
}
