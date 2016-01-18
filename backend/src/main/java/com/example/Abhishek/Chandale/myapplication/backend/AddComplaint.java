package com.example.Abhishek.Chandale.myapplication.backend;


import com.google.appengine.api.datastore.Blob;

/**
 * Created by abhi on 17-01-2016.
 */
public class AddComplaint {

    private Long id;
    private String complaintMessage;
    private String name;
    private String date;
    private Double lat;

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComplaintMessage() {
        return complaintMessage;
    }

    public void setComplaintMessage(String complaintMessage) {
        this.complaintMessage = complaintMessage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getComplaintAddress() {
        return complaintAddress;
    }

    public void setComplaintAddress(String complaintAddress) {
        this.complaintAddress = complaintAddress;
    }

    public Blob getImageBlob() {
        return imageBlob;
    }

    public void setImageBlob(byte[] bytes) {
        this.imageBlob = new Blob(bytes);
    }

    private Double lon;
    private String complaintAddress;
    private Blob imageBlob;
}
