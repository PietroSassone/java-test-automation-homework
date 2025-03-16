package com.homework.test.automation.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.homework.test.automation.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserManager {
    @Autowired
    private ObjectMapper objectMapper;

    private Map<String, User> users;

    public void initUserManager() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("credential.json")) {
            this.users = objectMapper.readValue(inputStream, new TypeReference<List<User>>() {
                    }).stream()
                    .collect(Collectors.toMap(User::username, user -> user));
        } catch (Exception e) {
            throw new RuntimeException("Error loading test user credentials", e);
        }
    }

    public User getUserByName(final String username) {
        return users.get(username);
    }
}
