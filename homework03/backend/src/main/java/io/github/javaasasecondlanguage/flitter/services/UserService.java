package io.github.javaasasecondlanguage.flitter.services;

import io.github.javaasasecondlanguage.flitter.Storage;
import io.github.javaasasecondlanguage.flitter.entities.User;

import java.util.List;
import java.util.stream.Collectors;

public interface UserService {
    public List<String> getUserNamesList();

    public User registerUser(String name);

    public User getUserByName(String name);

    public User getUserByToken(String token);
}
