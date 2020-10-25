package io.github.javaasasecondlanguage.flitter.entities;

import java.util.Date;

public class Flit {
    private long date;
    private User user;
    private String content;

    public Flit(User user, String content) {
        // without thinking about time zones, client and server dates
        this.date = System.currentTimeMillis();
        this.user = user;
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
