package com.example.Abhishek.Chandale.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.ConflictException;
import static com.example.Abhishek.Chandale.myapplication.backend.OfyService.ofy;

import java.util.logging.Logger;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "complaintApi",
        version = "v1",
        resource = "complaint",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.Chandale.Abhishek.example.com",
                ownerName = "backend.myapplication.Chandale.Abhishek.example.com",
                packagePath = ""
        )
)
public class ComplaintEndpoint {

    private static final Logger logger = Logger.getLogger(ComplaintEndpoint.class.getName());

    /**
     * This method gets the <code>Complaint</code> object associated with the specified <code>id</code>.
     *
     * @param id The id of the object to be returned.
     * @return The <code>Complaint</code> associated with <code>id</code>.
     */
    @ApiMethod(name = "getComplaint")
    public Complaint getComplaint(@Named("id") Long id) {
        // TODO: Implement this function
        logger.info("Calling getComplaint method");
        return null;
    }

    /**
     * This inserts a new <code>Complaint</code> object.
     *
     * @param complaint The object to be added.
     * @return The object to be added.
     */
    @ApiMethod(name = "insertComplaint")
    public Complaint insertComplaint(Complaint complaint) throws ConflictException{

        if(complaint.getId() !=null){

            if(findRecord(complaint.getId())!=null){

                throw new ConflictException("object already Exits!");
            }
        }
        ofy().save().entity(complaint).now();
        logger.info("Calling insertComplaint method");
        return complaint;
    }
    private Complaint findRecord(Long id) {
        return ofy().load().type(Complaint.class).id(id).now();
    }

}//class end