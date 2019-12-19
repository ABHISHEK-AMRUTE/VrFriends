package com.socialmedia.friends;

public class user_info_class {

    int image_code;
    String name,image_url,uid,bio;
    public user_info_class(String name,String image_url,String uid,int image_code ,String bio)
    {   this.uid=uid;
        this.name=name;
        this.image_url=image_url;
        this.image_code= image_code;
        this.bio =bio;
    }

    public int getImage_code() {
        return image_code;
    }

    public void setImage_code(int image_code) {
        this.image_code = image_code;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
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
