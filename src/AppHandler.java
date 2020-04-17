import org.json.simple.parser.ParseException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;
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
    @Produces(MediaType.APPLICATION_JSON)
    public String getState() {
        return DatabaseSimulator.getStateJson();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/Precinct/{stateName}")
    public String getPrecinctData(@PathParam("stateName") String name) {
        return DatabaseSimulator.getPrecinctJson(name);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{stateName}/Districts")
    public String getDistricts(@PathParam("stateName") String name){
        return DatabaseSimulator.getCongressionalDistricts(name);
    }
}