package io.github.javaasasecondlanguage.flitter.services;

import java.util.List;

public interface SubscriptionService {
    void subscribe(String subscriberToken, String publisherName);

    void unsubscribe(String subscriberToken, String publisherName);

    List<String> getSubscribersNames(String userToken);

    List<String> getPublishersNames(String userToken);
}
