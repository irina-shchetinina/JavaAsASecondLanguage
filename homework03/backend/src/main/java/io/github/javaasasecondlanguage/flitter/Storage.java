package io.github.javaasasecondlanguage.flitter;


import io.github.javaasasecondlanguage.flitter.entities.Flit;
import io.github.javaasasecondlanguage.flitter.entities.Subscription;
import io.github.javaasasecondlanguage.flitter.entities.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class Storage {
    public static final List<Flit> flits = new ArrayList<>();
    public static final List<User> users = new ArrayList<>();
    public static final List<Subscription> subscriptions = new ArrayList<>();

    public static void clear() {
        flits.clear();
        users.clear();
        subscriptions.clear();
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static void addFlit(Flit flit) {
        flits.add(flit);
    }

    private static Subscription getSubscription(User subscriber, User publisher) {
        return subscriptions.stream()
                .filter(x -> x.getSubscriber().equals(subscriber) && x.getPublisher().equals(publisher))
                .findFirst()
                .orElse(null);
    }

    public static void addSubscription(User subscriber, User publisher) {
        var subscription = getSubscription(subscriber, publisher);
        if (subscription == null) {
            subscriptions.add(new Subscription(subscriber, publisher));
        }
    }

    public static void removeSubscription(User subscriber, User publisher) {
        var subscription = getSubscription(subscriber, publisher);
        if (subscription != null) {
            subscriptions.remove(subscription);
        }
    }

    public static List<User> getSubscribers(User publisher) {
        return subscriptions.stream()
                .filter(x -> x.getPublisher().equals(publisher))
                .map(Subscription::getSubscriber)
                .collect(Collectors.toList());
    }

    public static List<User> getUserSubscriptions(User user) {
        return subscriptions.stream().filter(x -> x.getSubscriber().equals(user))
                .map(Subscription::getPublisher)
                .collect(Collectors.toList());
    }

    public static List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public static User getUserByName(String name) {
        var user = users.stream()
                .filter(x -> x.getName().equals(name))
                .findFirst()
                .orElse(null);
        return user;
    }

    public static User getUserByToken(String userToken) {
        var user = users.stream()
                .filter(x -> x.getToken().toString().equals(userToken))
                .findFirst()
                .orElse(null);
        return user;
    }

    public static List<Flit> getFlits() {
        return flits;
    }

    public static List<Flit> getLastFlits(int count) {
        return flits.stream()
                .sorted(Comparator.comparingLong(Flit::getDate).reversed())
                .limit(count)
                .collect(Collectors.toList());
    }

    public static List<User> getUsers() {
        return users;
    }

    public static List<Flit> getFlitsByUser(String username) {
        return flits.stream().filter(x -> x.getUser().getName().equals(username))
                .collect(Collectors.toList());
    }

    public static List<Flit> getFlitsByUserList(List<User> users) {
        return flits.stream()
                .filter((x -> users.contains(x.getUser())))
                .collect(Collectors.toList());
    }
}
