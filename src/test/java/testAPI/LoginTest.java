
import org.example.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

//import static com.google.common.base.Predicates.equalTo;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
//import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.equalTo; // Correct import for Hamcrest's equalTo


public class LoginTest {

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
    public void
    lotto_resource_returns_200_with_expected_id_and_winners() {

        when().
                get("http://localhost:3000/api/search").
                then().
                statusCode(401).
                body("message", equalTo("Unauthorized"));

    }

    @Test
    public void testGetDatasources() {
        given().
                header("Accept", "application/json"). // Set Accept header
                header("Content-Type", "application/json"). // Set Content-Type header
                header("Authorization", "Bearer glsa_1CsLxCX7L3ltJvA53hvFzuFCNkBkNu7c_621b7eff"). // Set Bearer token
                when().
                get("http://localhost:3000/api/datasources"). // Replace with your actual API URL
                then().
                statusCode(200). // Expect HTTP 200 OK
                body("size()", greaterThan(0)); // Validate response body (example)
    }

    @Test
    public void testCreateDataSource() {
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
    }

    @Test
    public void testGetDataSourceIdByName() {

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

        String dataSourceName = "test_datasource"; // The name of the data source to retrieve

        given().
                header("Accept", "application/json"). // Expected response format
                header("Content-Type", "application/json"). // Request format
                header("Authorization", "Bearer glsa_1CsLxCX7L3ltJvA53hvFzuFCNkBkNu7c_621b7eff"). // Bearer token
                when().
                get("http://localhost:3000/api/datasources/id/" + dataSourceName). // Endpoint with data source name
                then().
                statusCode(200). // Expect HTTP 200 OK
                body("id", notNullValue()). // Validate that the ID exists
                body("id", greaterThan(0)); // Validate that the ID is greater than 0
    }

    @Test
    public void testGetAllDataSources() {
        given().
                header("Accept", "application/json"). // Expected response format
                header("Content-Type", "application/json"). // Request format
                header("Authorization", "Bearer glsa_1CsLxCX7L3ltJvA53hvFzuFCNkBkNu7c_621b7eff"). // Bearer token
                when().
                get("http://localhost:3000/api/datasources"). // Replace with your API URL
                then().
                statusCode(200). // Expect HTTP 200 OK
                body("size()", greaterThan(0)). // Ensure the response contains at least one data source
                body("[0].id", notNullValue()). // Validate the first data source has an ID
                body("[0].name", notNullValue()); // Validate the first data source has a name
    }
}
