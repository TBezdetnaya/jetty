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

public class NegativeFlow {

    public static final String TEST_URL = "http://localhost:8080/rest";

    public static final Logger LOGGER = Logger.getLogger(PositiveFlow.class.getName());

    ClientConfig clientConfig;

    Client client;

    WebTarget target;

    @BeforeTest
    public void setUp() {

        clientConfig = new ClientConfig();

        clientConfig.register(new LoggingFilter(LOGGER, true));

        client = ClientBuilder.newClient(clientConfig);

        target = client.target(TEST_URL);
    }

    @Test
    public void testCacheMaxAge() {

        WebTarget cacheControl = target.path("blabla/blabla" + 10);

        Invocation.Builder invokeBuilder = cacheControl.request(MediaType.TEXT_PLAIN);

        invokeBuilder.header("some-header", "true");

        Response response = invokeBuilder.get();

        assertEquals(response.getStatus(), 404);

        System.out.println(response.readEntity(String.class));

    }


    @Test
    public void testHTTPMethods() {

        WebTarget cacheControl = target.path("blabla/blabla" + 10);

        Invocation.Builder invokeBuilder = cacheControl.request(MediaType.TEXT_PLAIN);

        invokeBuilder.header("some-header", "true");

        Response response = invokeBuilder.delete();

        assertEquals(response.getStatus(), 404);

        System.out.println(response.readEntity(String.class));

        System.out.println(response.getAllowedMethods());

        System.out.println(response.getCookies());

        System.out.println(response.getHeaders());

        System.out.println(response.getEntityTag());

        System.out.println(response.getMediaType());

    }
}
