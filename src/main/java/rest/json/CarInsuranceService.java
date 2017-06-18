package rest.json;
import org.codehaus.jackson.map.ObjectMapper;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Path("carInsuranceService")
public class CarInsuranceService {

   private ObjectMapper objectMapper = new ObjectMapper();

    @GET
    @Path("/get/insurance")
    @Produces(MediaType.APPLICATION_JSON)
    public String getList() throws IOException {
        Map<Integer, String> stringMap = new HashMap<>();
        stringMap.put(1, "KACKO");
        stringMap.put(2, "OSAGO");
        stringMap.put(3, "Unknown company");
        String json = objectMapper.writeValueAsString(stringMap);

        return json;

    }


    @POST
    @Path("/post/insurance")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CarInsuranceResponse getCarInsuranceInJSON(CarInsuranceRequest request) {

        CarInsuranceResponse response = new CarInsuranceResponse();
        int id = request.getId();
        switch (id) {
            case 0:
                response.setId(0);
                response.setName("KACKO");
                break;
            case 1:
                response.setId(1);
                response.setName("OSAGO");
                break;
            case 2:
                response.setId(2);
                response.setName("Unknown company");
                break;
            default:
                response.setId(id);
                response.setDescription("No insurance by such id:");
        }

        return response;

    }


}
