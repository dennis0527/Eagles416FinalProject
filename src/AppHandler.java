import com.eagles.ElectionDataQuality.PersistenceLayer.PersistenceLayer;
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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getState() {
        String result = DatabaseSimulator.getStateJson();
        System.out.println("Retrieving states");
        return result;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/Precinct/{stateName}")
    public String getPrecinctData(@PathParam("stateName") String name) {
        return DatabaseSimulator.getPrecinctJson(name.replaceAll("%20", " "));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{stateName}/Districts")
    public String getDistricts(@PathParam("stateName") String name){
        return DatabaseSimulator.getCongressionalDistricts(name);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{stateName}/{precinctName}/neighbors")
    public String getNeighbors(@PathParam("stateName") String state, @PathParam("precinctName") String precinct){
        return DatabaseSimulator.getNeighbors(state, precinct.replaceAll("%20", " "));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("AnomalousError/{stateName}")
    public String getAnomalousErrors(@PathParam("stateName") String state) {
        return DatabaseSimulator.getAnomalousErrors(state);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("NationalParks")
    public String getNationalParks(){
        return DatabaseSimulator.getNationalParks();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("state")
    public String getStateJson(){
        PersistenceLayer l = new PersistenceLayer();
        return l.getStateJsonString();
    }
}