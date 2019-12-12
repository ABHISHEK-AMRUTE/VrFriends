package com.socialmedia.friends;

public class user_info_class {


    String name,image_url,uid;
    public user_info_class(String name,String image_url,String uid)
    {   this.uid=uid;
        this.name=name;
        this.image_url=image_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
