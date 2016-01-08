package com.login.abhishekchandale.virtualhackapp1.model;

/**
 * Created by Abhishek.Chandale on 12/16/2015.
 */
public class Registration

{
    private String email;
    private String address;
    private String image;
    private String birthday;
    private String gender;
    private String userName;
    private String aboutMe;
    private String id;
    private String password;

    private Registration(){
        name="abhi";
        email="abhishek.chandale@happiestminds.com";
        address="null";
        image="image";
        birthday="null";
        gender="null";
        userName="null";
        aboutMe="null";
        password="*";
    }

    public static Registration getInstance(){
        if(mInstance == null)
        {
            mInstance = new Registration();
        }
        return mInstance;
    }

    private static Registration mInstance = null;

    private String name;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
