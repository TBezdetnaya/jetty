package rest_tests;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import rest.json.CarInsuranceRequest;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;
import static org.testng.Assert.assertEquals;


/**
 * Created by Tanya on 17.06.2017.
 */
public class CarInsuranceNegativeFlow {
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
    public void testStatusJSONGet() {

        WebTarget cacheControl = target.path("carInsuranceService/get/blablabla");
        Invocation.Builder invokeBuilder = cacheControl.request(MediaType.APPLICATION_JSON);
        Response response = invokeBuilder.get();
        assertEquals(response.getStatus(), 404);
    }
    @Test
    public void testStatusJSONPost() {
        CarInsuranceRequest carInsuranceRequest = new CarInsuranceRequest();
        WebTarget cacheControl = target.path("carInsuranceService/post/blablabla");
        Invocation.Builder invokeBuilder = cacheControl.request();
        Response response = invokeBuilder.post(Entity.entity(carInsuranceRequest, MediaType.APPLICATION_JSON));
        assertEquals(response.getStatus(), 404);
    }


}
