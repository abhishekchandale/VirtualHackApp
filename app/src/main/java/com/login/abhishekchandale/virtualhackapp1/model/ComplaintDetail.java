package com.login.abhishekchandale.virtualhackapp1.model;

/**
 * Created by Abhishek.Chandale on 1/4/2016.
 */
public class ComplaintDetail {

    private static ComplaintDetail mInstance=null;
    private String complaintText;
    private String image;
    private double lat;
    private double lon;
    private String dateTime;
    private String complaintAddress;
    private String name;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String Email;
    private ComplaintDetail(){

        String complaintText="";
        String image="";
        double lat=0.0;
        double lon=0.0;
        String dateTime="";
        String complaintAddress="";
        String name="";
        String email="";

    }

    public static ComplaintDetail getInstance(){
        if(mInstance == null)
        {
            mInstance = new ComplaintDetail();
        }
        return mInstance;
    }

    public String getComplaintText() {
        return complaintText;
    }

    public void setComplaintText(String complaintText) {
        this.complaintText = complaintText;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getComplaintAddress() {
        return complaintAddress;
    }

    public void setComplaintAddress(String complaintAddress) {
        this.complaintAddress = complaintAddress;
    }


}
