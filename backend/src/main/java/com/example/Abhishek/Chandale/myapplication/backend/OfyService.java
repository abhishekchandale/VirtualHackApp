package com.example.Abhishek.Chandale.myapplication.backend;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;


/**
 * Created by Abhishek.Chandale on 1/4/2016.
 */
public class OfyService {

    static {
        ObjectifyService.register(Complaint.class);
    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}
