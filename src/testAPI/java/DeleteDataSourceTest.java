
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class DeleteDataSourceTest {


    private final String BASE_URL = "http://localhost:3000/api";
    private final String BEARER_TOKEN = "Bearer glsa_1CsLxCX7L3ltJvA53hvFzuFCNkBkNu7c_621b7eff";

    @BeforeEach
    public void setUp() {
        String dataSourceName = "test_datasource"; // Name of the data source to delete

        given().
                header("Accept", "application/json"). // Expected response format
                header("Content-Type", "application/json"). // Request format
                header("Authorization", "Bearer glsa_1CsLxCX7L3ltJvA53hvFzuFCNkBkNu7c_621b7eff"). // Bearer token
                when().
                delete("http://localhost:3000/api/datasources/name/" + dataSourceName);// Endpoint with the data source name

    }
    @Test
    public void testDeleteDataSourceByUID() {
        String uid = "kLtEtcRGk"; // Replace with the UID of an existing data source

        given().
                header("Accept", "application/json").
                header("Authorization", "Bearer glsa_1CsLxCX7L3ltJvA53hvFzuFCNkBkNu7c_621b7eff").
                when().
                delete("http://localhost:3000/api/datasources/uid/" + uid).
                then().
                statusCode(404);// Expect HTTP 401 Not Found
    }

    @Test
    public void testDeleteDataSourceByName() {
        // Define the request payload
        String requestBody = """
        {
          "name": "test_datasource",
          "type": "graphite",
          "url": "http://mydatasource.com",
          "access": "proxy",
          "basicAuth": false
        }
        """;

        // Send POST request
        given().
                header("Accept", "application/json").
                header("Content-Type", "application/json").
                header("Authorization", "Bearer glsa_1CsLxCX7L3ltJvA53hvFzuFCNkBkNu7c_621b7eff"). // Bearer token
                body(requestBody). // Attach JSON payload
                when().
                post("http://localhost:3000/api/datasources"). // Replace with your Grafana API URL
                then().
                statusCode(200). // Validate HTTP 200 response
                body("message", equalTo("Datasource added")). // Validate response message
                body("datasource.name", equalTo("test_datasource")). // Validate datasource name
                body("datasource.type", equalTo("graphite")); // Validate datasource type

        String dataSourceName = "test_datasource"; // Replace with the name of an existing data source

        given().
                header("Accept", "application/json").
                header("Authorization", "Bearer glsa_1CsLxCX7L3ltJvA53hvFzuFCNkBkNu7c_621b7eff").
                when().
                delete("http://localhost:3000/api/datasources/name/" + dataSourceName).
                then().
                statusCode(200). // Expect HTTP 200 OK
                body("message", equalTo("Data source deleted")). // Validate success message
                body("id", notNullValue()); // Ensure the ID of the deleted data source is returned

        System.out.println("Successfully deleted data source with name: " + dataSourceName);
    }

    @Test
    public void testCreateAndDeleteDataSource() {
        // Step 1: Create a new data source
        String dataSourceName = "temp_datasource"; // Ensure this name is unique
        String createRequestBody = """
        {
          "name": "temp_datasource",
          "type": "graphite",
          "url": "http://temp-datasource.com",
          "access": "proxy",
          "basicAuth": false
        }
        """;

        String uid =
                given().
                        header("Accept", "application/json").
                        header("Content-Type", "application/json").
                        header("Authorization", BEARER_TOKEN).
                        body(createRequestBody).
                        when().
                        post(BASE_URL + "/datasources").
                        then().
                        statusCode(200).
                        body("message", equalTo("Datasource added")). // Validate success message
                        extract().
                        path("datasource.uid"); // Extract the UID of the created data source

        System.out.println("Step 1: Created data source with UID: " + uid);

        // Step 2: Delete the data source by UID
        given().
                header("Accept", "application/json").
                header("Authorization", BEARER_TOKEN).
                when().
                delete(BASE_URL + "/datasources/uid/" + uid).
                then().
                statusCode(200). // Expect HTTP 200 OK
                body("message", equalTo("Data source deleted")). // Validate success message
                body("id", notNullValue()); // Ensure the ID of the deleted data source is returned

        System.out.println("Step 2: Successfully deleted data source with UID: " + uid);
    }

}
