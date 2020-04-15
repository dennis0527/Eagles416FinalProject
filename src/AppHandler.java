import org.json.simple.parser.ParseException;

import javax.ws.rs.*;
import java.io.IOException;

// The Java class will be hosted at the URI path "/app"
@Path("/app")
public class AppHandler {

    public AppHandler() {
    }

    // The Java method will process HTTP GET requests
//    @Consumes(MediaType.TEXT_PLAIN)
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/{stateName}")
//    public String getState(@PathParam("stateName") String stateName) throws IOException, ParseException {
//        String test =DatabaseSimulator.test();
//        return test;
//    }
    @GET
    @Produces("text/plain")
    public String getState() {
        DatabaseSimulator databaseSimulator = new DatabaseSimulator();
        String test =databaseSimulator.stateName;
        return test;
    }
}