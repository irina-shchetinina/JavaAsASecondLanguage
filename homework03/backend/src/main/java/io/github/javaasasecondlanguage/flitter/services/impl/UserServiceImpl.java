package io.github.javaasasecondlanguage.flitter.services.impl;

import io.github.javaasasecondlanguage.flitter.Storage;
import io.github.javaasasecondlanguage.flitter.entities.User;
import io.github.javaasasecondlanguage.flitter.services.UserService;
import io.github.javaasasecondlanguage.flitter.services.exceptions.IncorrectUserNameException;
import io.github.javaasasecondlanguage.flitter.services.exceptions.UserAlreadyRegisteredException;
import io.github.javaasasecondlanguage.flitter.services.exceptions.UserNotFoundByNameException;
import io.github.javaasasecondlanguage.flitter.services.exceptions.UserNotFoundByTokenException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserServiceImpl implements UserService {

    @Override
    public User registerUser(String name) {
        System.out.println("register user");
        if (Storage.getUserByName(name) != null) {
            throw new UserAlreadyRegisteredException();
        }
        if (name.length() < 3) {
            throw new IncorrectUserNameException("User name is too short");
        }
        if (name.length() > 255) {
            throw new IncorrectUserNameException("User name is too long");
        }
        var user = new User(name);
        Storage.addUser(user);
        return user;
    }

    @Override
    public List<String> getUserNamesList() {
        var userList = Storage.getUsers();
        return userList.stream().map(User::getName).collect(Collectors.toList());
    }

    @Override
    public User getUserByName(String name) {
        var user = Storage.getUserByName(name);
        if (user == null) {
            throw new UserNotFoundByNameException();
        }
        return user;
    }

    @Override
    public User getUserByToken(String token) {
        var user = Storage.getUserByToken(token);
        if (user == null) {
            throw new UserNotFoundByTokenException();
        }
        return user;
    }
}
