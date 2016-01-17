package com.example.Abhishek.Chandale.myapplication.backend;

import com.google.appengine.api.datastore.Blob;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by Abhishek.Chandale on 1/4/2016.
 */

@Entity
public class Complaint {

    @Id
    private Long id;
    private String complaintMessage;
    private String name;
    private String date;
    private Double lat;
    private Double lon;
    private String complaintAddress;
    private Blob imageBlob;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    private String city;
    private String email;
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    private String mobile;


    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getComplaintAddress() {
        return complaintAddress;
    }

    public void setComplaintAddress(String complaintAddress) {
        this.complaintAddress = complaintAddress;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComplaintMessage() {
        return complaintMessage;
    }

    public void setComplaintMessage(String complaintMessage) {
        this.complaintMessage = complaintMessage;
    }


    public byte[] getImage() {
        if (imageBlob == null) {
            return null;
        }
        return imageBlob.getBytes();
    }

    public void setImage(byte[] bytes) {
        this.imageBlob = new Blob(bytes);
    }

}
