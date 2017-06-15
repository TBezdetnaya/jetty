package rest.json;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Path("json")
public class CarInsuranceService {

    ObjectMapper objectMapper = new ObjectMapper();
    @GET
    @Path("/get/list")
    @Produces(MediaType.APPLICATION_JSON)
    public String getList() throws IOException {
        Map<Integer, String> stringMap = new HashMap<>();
        stringMap.put(1, "KACKO");
        stringMap.put(2,"OSAGO");
        stringMap.put(3, "Unknown company");
        String json = objectMapper.writeValueAsString(stringMap);
        return json;

    }

    @POST
    @Path ("/post/insurance")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CarInsuranceResponse getCarInsurance(CarInsuranceRequest request){
        
        CarInsuranceResponse response = new CarInsuranceResponse();

        if (request.getId()< 3){
            response.setName("KACKO ");
            response.setId(1);
            response.setName("OSAGO ");
            response.setId(2);
            response.getDescription();


        }else{
            response.setDescription("No insurance by such id: ");
        }
        return response;

    }



}
