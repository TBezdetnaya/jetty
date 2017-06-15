package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import java.io.File;


@Path("file")
public class FileService {

    ClassLoader classLoader = getClass().getClassLoader();

    @GET
    @Path("/get-file")
    @Produces("text/plain")
    public Response getFile() {

        File file = new File(classLoader.getResource("web.txt").getFile());

        ResponseBuilder builder = Response.ok(file);

        builder.header("Content-Disposition", "attachmnet; filename=\"file_from_server.log\"");

        return builder.build();
    }
}
