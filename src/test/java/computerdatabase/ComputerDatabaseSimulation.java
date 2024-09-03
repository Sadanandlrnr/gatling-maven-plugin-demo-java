package computerdatabase;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class ComputerDatabaseSimulation extends Simulation {

    // Define the HTTP protocol configuration
    HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://13.235.113.56:80") // Base URL for all requests
            .inferHtmlResources(); // Automatically download and parse HTML resources

    // Define the scenario
    ScenarioBuilder scn = scenario("Basic Load Test")
            .exec(
                    http("Request to /") // Name of the request
                            .get("/") // HTTP GET method
                            .check(status().is(200)) // Check that the status is 200 OK
            );

    {
        // Set up the simulation
        setUp(
                scn.injectOpen(atOnceUsers(100)) // Inject 100 users at once
        ).protocols(httpProtocol);
    }
}
