package rest.json;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.map.ObjectMapper;

@Path("json")
public class JSONService {

    ObjectMapper objectMapper = new ObjectMapper();

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Track getTrackInJSON() {
        Track track = new Track();
        track.setTitle("Diesel power");
        track.setGroup("Prodigy");
        return track;
    }
    @GET
    @Path("/get/title")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTrackTitle() throws IOException {
//        Track track = new Track();
//        track.setTitle("Diesel power");
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("track", "Numb");
        String json = objectMapper.writeValueAsString(stringMap);
        return json;
    }


    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response createTrackInJSON(TrackRequest trackRequest) {
        TrackResponse trackResponse = new TrackResponse();
        if(trackRequest.getId() < 10){
            trackResponse.setTitle("New");
        } else {
            trackResponse.setTitle("Old");
        }
        return Response.status(201).entity(trackResponse).build();
    }

}
