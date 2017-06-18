package rest_tests;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import rest.json.CarInsuranceRequest;
import rest.json.CarInsuranceResponse;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by Tanya on 15.06.2017.
 */
public class CarInsurancePositiveFlow {

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
    public void testCarInsuranceInJSONPost (){
        CarInsuranceRequest carInsuranceRequest = new CarInsuranceRequest();
        carInsuranceRequest.setId(4);
        WebTarget queryURL = target.path("carInsuranceService/post/insurance");
        CarInsuranceResponse response = queryURL.request(MediaType.APPLICATION_JSON).post(Entity.entity(carInsuranceRequest, MediaType.APPLICATION_JSON), CarInsuranceResponse.class);
        Assert.assertEquals(response.getDescription() + " "+ response.getId(),"No insurance by such id: 4");


    }
    @Test
    public void testCarInsuranceInJSONPost1(){
        CarInsuranceRequest carInsuranceRequest = new CarInsuranceRequest();
        carInsuranceRequest.setId(1);
        WebTarget queryURL = target.path("carInsuranceService/post/insurance");
        CarInsuranceResponse response = queryURL.request(MediaType.APPLICATION_JSON).post(Entity.entity(carInsuranceRequest, MediaType.APPLICATION_JSON), CarInsuranceResponse.class);
        System.out.println(response.getDescription() + " "+ response.getId() + " " + response.getName());
        Assert.assertEquals(response.getDescription() + " "+ response.getId() + " " + response.getName(),"You bought the insurance 1 OSAGO");


    }
    @Test
    public void testCarInsuranceInJSONPost3(){
        CarInsuranceRequest carInsuranceRequest = new CarInsuranceRequest();
        carInsuranceRequest.setId(2);
        WebTarget queryURL = target.path("carInsuranceService/post/insurance");
        CarInsuranceResponse response = queryURL.request(MediaType.APPLICATION_JSON).post(Entity.entity(carInsuranceRequest, MediaType.APPLICATION_JSON), CarInsuranceResponse.class);
        System.out.println(response.getDescription() + " "+ response.getId() + " " + response.getName());
        Assert.assertEquals(response.getDescription() + " "+ response.getId() + " " + response.getName(),"You bought the insurance 2 Unknown company");


    }
    @Test
    public void testCarInsuranceInJSONGet() throws IOException {

        WebTarget queryURL = target.path("carInsuranceService/get/insurance");
        Invocation.Builder invokeBuilder = queryURL.request(MediaType.APPLICATION_JSON);
        Response response = invokeBuilder.get();
        assertEquals(response.getStatus(), 200);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<Integer, String> jsonResult = objectMapper.readValue(response.readEntity(String.class),
                new TypeReference<HashMap<Integer, String>>() {
                });

        assertTrue(jsonResult.get(1).equals("KACKO"));
        assertTrue(jsonResult.get(2).equals("OSAGO"));
        assertTrue(jsonResult.get(3).equals("Unknown company"));


    }


}
