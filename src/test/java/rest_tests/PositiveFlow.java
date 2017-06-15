package rest_tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import rest.jaxb.Customer;
import rest.json.Track;
import rest.json.TrackRequest;
import rest.json.TrackResponse;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class PositiveFlow {

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
    public void testCacheMaxAge() {

        WebTarget cacheControl = target.path("cache-control/" + 10);

        Invocation.Builder invokeBuilder = cacheControl.request(MediaType.TEXT_PLAIN);

        invokeBuilder.header("some-header", "true");

        Response response = invokeBuilder.get();

        assertEquals(response.getStatus(), 200);

        System.out.println(response.readEntity(String.class));
    }


    @Test
    public void testQueryParam() {

        WebTarget queryURL = target.path("users/queryparam/query");

        WebTarget queryURLWithParam = queryURL.queryParam("from", "20").queryParam("to", "100");

        Invocation.Builder invokeBuilder = queryURLWithParam.request(MediaType.TEXT_PLAIN);

        invokeBuilder.header("some-header", "true");

        Response response = invokeBuilder.get();

        String entity = response.readEntity(String.class);

        assertEquals(response.getEntity() != null, 200);

        System.out.println(response.readEntity(String.class));
    }

    @Test
    public void testJSONGet() throws IOException {

        WebTarget queryURL = target.path("json/get");

        Invocation.Builder invokeBuilder = queryURL.request(MediaType.APPLICATION_JSON);

        Response response = invokeBuilder.get();

        assertEquals(response.getStatus(), 200);

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, String> jsonResult = objectMapper.readValue(response.readEntity(String.class),
                new TypeReference<HashMap<String, String>>() {
                });

        assertTrue(jsonResult.get("title").equals("Diesel power"));

        assertTrue(jsonResult.get("group").equals("Prodigy"));
    }


    @Test
    public void testJSONPost() throws IOException {

//        String input = "{\"group\":\"Linkin Park\",\"title\":\"Numb\"}";

        Track trackNEw = new Track();
        trackNEw.setTitle("Numb");
        trackNEw.setGroup("Linkin Park");

        WebTarget queryURL = target.path("json/post");

        Track track = queryURL.request(MediaType.APPLICATION_JSON).post(Entity.entity(trackNEw, MediaType.APPLICATION_JSON), Track.class);

        assertTrue(track.getTitle().equals("Numb"));

        assertTrue(track.getGroup().equals("Linkin Park"));
    }


    @Test
    public void testJSONPostNew() throws IOException {

        TrackRequest trackRequest = new TrackRequest();
        trackRequest.setId(23213);

        WebTarget queryURL = target.path("json/post");

        TrackResponse track = queryURL.request(MediaType.APPLICATION_JSON).post(Entity.entity(trackRequest, MediaType.APPLICATION_JSON), TrackResponse.class);

        assertTrue(track.getTitle().equals("Old"));
    }

    @Test
    public void testXMlGet() throws IOException {

        int id = 1;

        WebTarget queryURL = target.path("xml/customer/" + id);

        Customer customer = queryURL.request(MediaType.APPLICATION_XML).get(Customer.class);

        assertTrue(customer.getName().equals("Sergii Oliinyk"));
    }
}

