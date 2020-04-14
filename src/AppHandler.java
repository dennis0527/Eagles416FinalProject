import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

// The Java class will be hosted at the URI path "/app"
@Path("/app")
public class AppHandler {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getState() {
        // Return some cliched textual content
        System.out.println("\n\n\n\n\nHI\n\n\n\n");
        return "New York";
    }
}