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
    @Path("/get/insurance")
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
    public CarInsuranceResponse getCarInsuranceInJSON(CarInsuranceRequest request){
        
        CarInsuranceResponse response = new CarInsuranceResponse();

        if (request.getId()==0) {
            response.setId(0);
            response.setName("KACKO");

        }else if  (request.getId()==1){
            response.setId(1);
            response.setName("OSAGO");

        }else if(request.getId()==2){
            response.setId(2);
            response.setName("Unknown company");

        }else{
            int id = request.getId();
            response.setId(id);
            response.setDescription("No insurance by such id:");
        }
        return response;

    }






}
