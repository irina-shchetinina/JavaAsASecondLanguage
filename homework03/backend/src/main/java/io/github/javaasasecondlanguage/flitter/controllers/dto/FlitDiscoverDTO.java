package io.github.javaasasecondlanguage.flitter.controllers.dto;

public class FlitDiscoverDTO {
    private String userName;
    private String content;

    public FlitDiscoverDTO() {
    }

    public FlitDiscoverDTO(String userName, String content) {
        this.userName = userName;
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
