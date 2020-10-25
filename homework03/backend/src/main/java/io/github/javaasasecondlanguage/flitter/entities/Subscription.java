package io.github.javaasasecondlanguage.flitter.entities;

public class Subscription {
    private User subscriber;
    private User publisher;

    public Subscription(User subscriber, User publisher) {
        this.subscriber = subscriber;
        this.publisher = publisher;
    }

    public User getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(User subscriber) {
        this.subscriber = subscriber;
    }

    public User getPublisher() {
        return publisher;
    }

    public void setPublisher(User publisher) {
        this.publisher = publisher;
    }
}
