package com.server.tradedoc.logic.dto;

public class CommentsDTO extends AbstractDTO {

    private String content;
    private String usernameComment;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsernameComment() {
        return usernameComment;
    }

    public void setUsernameComment(String usernameComment) {
        this.usernameComment = usernameComment;
    }
}
