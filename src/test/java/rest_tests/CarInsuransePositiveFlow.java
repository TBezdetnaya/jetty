package rest_tests;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import rest.json.CarInsuranceRequest;
import rest.json.CarInsuranceResponse;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

/**
 * Created by Tanya on 15.06.2017.
 */
public class CarInsuransePositiveFlow {

    private static final String TEST_URL = "http://localhost:8080/rest";
    private static final Logger LOGGER = Logger.getLogger(PositiveFlow.class.getName());
    private ClientConfig clientConfig;
    private Client client;
    private WebTarget target;

    @BeforeTest
    public void setUp() {

        clientConfig = new ClientConfig();
        clientConfig.register(new LoggingFilter(LOGGER, true));
        client = ClientBuilder.newClient(clientConfig);
        target = client.target(TEST_URL);
    }


    @Test
    public void test (){
        CarInsuranceRequest carInsuranceRequest = new CarInsuranceRequest();
        carInsuranceRequest.setId(4);
        WebTarget queryURL = target.path("json/post/insurance");
        CarInsuranceResponse response = queryURL.request(MediaType.APPLICATION_JSON).post(Entity.entity(carInsuranceRequest, MediaType.APPLICATION_JSON), CarInsuranceResponse.class);
        Assert.assertEquals(response.getDescription() + " "+ response.getId(),"No insurance by such id: 4 0");


    }
    @Test
    public void test2 (){
        CarInsuranceRequest carInsuranceRequest = new CarInsuranceRequest();
        carInsuranceRequest.setId(2);
        WebTarget queryURL = target.path("json/post/insurance");
        CarInsuranceResponse response = queryURL.request(MediaType.APPLICATION_JSON).post(Entity.entity(carInsuranceRequest, MediaType.APPLICATION_JSON), CarInsuranceResponse.class);
        Assert.assertEquals(response.getDescription() + " "+ response.getId() + " " + response.getName(),"You bought the insurance 2 OSAGO");
    }
}
