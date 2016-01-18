package com.example.Abhishek.Chandale.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import java.util.logging.Logger;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "addComplaintApi",
        version = "v1",
        resource = "addComplaint",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.Chandale.Abhishek.example.com",
                ownerName = "backend.myapplication.Chandale.Abhishek.example.com",
                packagePath = ""
        )
)
public class AddComplaintEndpoint {

    private static final Logger logger = Logger.getLogger(AddComplaintEndpoint.class.getName());

    /**
     * This method gets the <code>AddComplaint</code> object associated with the specified <code>id</code>.
     *
     * @param id The id of the object to be returned.
     * @return The <code>AddComplaint</code> associated with <code>id</code>.
     */
    @ApiMethod(name = "getAddComplaint")
    public AddComplaint getAddComplaint(@Named("id") Long id) {
        // TODO: Implement this function
        logger.info("Calling getAddComplaint method");
        return null;
    }

    /**
     * This inserts a new <code>AddComplaint</code> object.
     *
     * @param addComplaint The object to be added.
     * @return The object to be added.
     */
    @ApiMethod(name = "insertAddComplaint")
    public AddComplaint insertAddComplaint(AddComplaint addComplaint) {
        // TODO: Implement this function
        logger.info("Calling insertAddComplaint method");
        return addComplaint;
    }
}