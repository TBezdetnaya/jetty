package rest.jaxb;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("xml/customer")
public class XMLService {

    @GET
    @Path("/{pin}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Customer getCustomerInXML(@PathParam("pin") int pin) {
        Customer customer = new Customer();
        customer.setName("Sergii Oliinyk");
        customer.setPin(pin);
        return customer;
    }
}
