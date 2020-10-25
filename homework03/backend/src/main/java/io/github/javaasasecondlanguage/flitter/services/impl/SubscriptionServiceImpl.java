package io.github.javaasasecondlanguage.flitter.services.impl;

import io.github.javaasasecondlanguage.flitter.Storage;
import io.github.javaasasecondlanguage.flitter.entities.User;
import io.github.javaasasecondlanguage.flitter.services.SubscriptionService;
import io.github.javaasasecondlanguage.flitter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SubscriptionServiceImpl implements SubscriptionService {
    private UserService userService;

    @Autowired
    public SubscriptionServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void subscribe(String subscriberToken, String publisherName) {
        var subscriber = userService.getUserByToken(subscriberToken);

        var publisher = userService.getUserByName(publisherName);

        Storage.addSubscription(subscriber, publisher);
    }

    @Override
    public void unsubscribe(String subscriberToken, String publisherName) {
        var subscriber = userService.getUserByToken(subscriberToken);

        var publisher = userService.getUserByName(publisherName);

        Storage.removeSubscription(subscriber, publisher);
    }

    @Override
    public List<String> getSubscribersNames(String userToken) {
        var publisher = userService.getUserByToken(userToken);

        var subscribers = Storage.getSubscribers(publisher);
        return subscribers.stream().map(User::getName).collect(Collectors.toList());
    }

    @Override
    public List<String> getPublishersNames(String userToken) {
        var user = userService.getUserByToken(userToken);
        var publishers = Storage.getUserSubscriptions(user);
        return publishers.stream().map(User::getName).collect(Collectors.toList());
    }
}
