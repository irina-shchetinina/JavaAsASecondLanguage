package io.github.javaasasecondlanguage.flitter.services.impl;

import io.github.javaasasecondlanguage.flitter.Storage;
import io.github.javaasasecondlanguage.flitter.entities.Flit;
import io.github.javaasasecondlanguage.flitter.services.FlitService;
import io.github.javaasasecondlanguage.flitter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FlitServiceImpl implements FlitService {
    private UserService userService;

    @Autowired
    public FlitServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Flit addFlit(Flit flit) {
        Storage.addFlit(flit);
        return flit;
    }

    @Override
    public List<Flit> getLastFlits(int count) {
        return Storage.getLastFlits(count);
    }

    @Override
    public List<Flit> getFlitsByUser(String userName) {
        var user = userService.getUserByName(userName);
        return Storage.getFlitsByUser(userName);
    }

    @Override
    public List<Flit> getFeedForUser(String userToken) {
        var user = userService.getUserByToken(userToken);
        var publishers = Storage.getUserSubscriptions(user);
        return Storage.getFlitsByUserList(publishers);
    }
}
