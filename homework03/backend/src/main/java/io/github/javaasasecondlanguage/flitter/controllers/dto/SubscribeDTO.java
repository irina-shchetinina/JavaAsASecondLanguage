package io.github.javaasasecondlanguage.flitter.controllers.dto;

public class SubscribeDTO {
    private String subscriberToken;
    private String publisherName;

    public String getSubscriberToken() {
        return subscriberToken;
    }

    public void setSubscriberToken(String subscriberToken) {
        this.subscriberToken = subscriberToken;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }
}
