package io.github.javaasasecondlanguage.flitter.entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class User {

    private String name;
    private UUID token;

    public User(String name) {
        this.name = name;
        this.token = UUID.randomUUID();
    }

    @JsonProperty("userName")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("userToken")
    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }
}
