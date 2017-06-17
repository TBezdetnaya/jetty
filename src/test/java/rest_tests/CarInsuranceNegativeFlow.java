package rest_tests;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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
    public void test() {

        WebTarget cacheControl = target.path("json/get/blablabla");
        Invocation.Builder invokeBuilder = cacheControl.request(MediaType.APPLICATION_JSON);
        Response response = invokeBuilder.get();
        assertEquals(response.getStatus(), 404);
    }
    @Test
    public void test2() {

        WebTarget cacheControl = target.path("json/post/blablabla");
        Invocation.Builder invokeBuilder = cacheControl.request(MediaType.APPLICATION_JSON);
        Response response = invokeBuilder.get();
        assertEquals(response.getStatus(), 404);
    }


}
