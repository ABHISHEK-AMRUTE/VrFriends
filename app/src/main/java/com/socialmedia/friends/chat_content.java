package com.socialmedia.friends;

public class chat_content {
    String message;
    String name;
    public chat_content( String message, String name)
    {
        this.message=message;
        this.name=name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
