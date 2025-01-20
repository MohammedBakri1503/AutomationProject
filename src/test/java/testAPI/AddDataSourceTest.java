package testAPI;
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



public class AddDataSourceTest {

    @BeforeEach
    public void setUp() {
        String dataSourceName = "test_datasource"; // Name of the data source to delete
        String datasource2 = "test_datasource_with_auth";
        String datasource3 = "test_cloudwatch_datasource";


        given().
                header("Accept", "application/json"). // Expected response format
                header("Content-Type", "application/json"). // Request format
                header("Authorization", "Bearer glsa_1CsLxCX7L3ltJvA53hvFzuFCNkBkNu7c_621b7eff"). // Bearer token
                when().
                delete("http://localhost:3000/api/datasources/name/" + dataSourceName);// Endpoint with the data source name
        given().
                header("Accept", "application/json"). // Expected response format
                header("Content-Type", "application/json"). // Request format
                header("Authorization", "Bearer glsa_1CsLxCX7L3ltJvA53hvFzuFCNkBkNu7c_621b7eff"). // Bearer token
                when().
                delete("http://localhost:3000/api/datasources/name/" + datasource3);// Endpoint with the data source name
        given().
                header("Accept", "application/json"). // Expected response format
                header("Content-Type", "application/json"). // Request format
                header("Authorization", "Bearer glsa_1CsLxCX7L3ltJvA53hvFzuFCNkBkNu7c_621b7eff"). // Bearer token
                when().
                delete("http://localhost:3000/api/datasources/name/" + datasource2);// Endpoint with the data source name

    }


    @Test
    public void testCreateGraphiteDataSource() {
        // Define the request body
        String requestBody = """
        {
          "name": "test_datasource",
          "type": "graphite",
          "url": "http://mydatasource.com",
          "access": "proxy",
          "basicAuth": false
        }
        """;

        given().
                header("Accept", "application/json").
                header("Content-Type", "application/json").
                header("Authorization", "Bearer glsa_1CsLxCX7L3ltJvA53hvFzuFCNkBkNu7c_621b7eff"). // Bearer token
                body(requestBody). // Attach JSON payload
                when().
                post("http://localhost:3000/api/datasources"). // Replace with your API URL
                then().
                statusCode(200). // Expect HTTP 200 OK
                body("message", equalTo("Datasource added")). // Validate response message
                body("datasource.name", equalTo("test_datasource")). // Validate datasource name
                body("datasource.type", equalTo("graphite")); // Validate datasource type

        given().
                header("Accept", "application/json").
                header("Content-Type", "application/json").
                header("Authorization", "Bearer glsa_1CsLxCX7L3ltJvA53hvFzuFCNkBkNu7c_621b7eff"). // Bearer token
                body(requestBody). // Attach JSON payload
                when().
                post("http://localhost:3000/api/datasources"). // Replace with your API URL
                then().
                statusCode(409);// Expect HTTP 409 Conflict

    }

    @Test
    public void testCreateGraphiteDataSourceWithBasicAuth() {
        String requestBody = """
    {
      "name": "test_datasource_with_auth",
      "type": "graphite",
      "url": "http://mydatasource.com",
      "access": "proxy",
      "basicAuth": true,
      "basicAuthUser": "basicuser",
      "secureJsonData": {
        "basicAuthPassword": "basicpassword"
      }
    }
    """;

        given().
                header("Accept", "application/json").
                header("Content-Type", "application/json").
                header("Authorization", "Bearer glsa_1CsLxCX7L3ltJvA53hvFzuFCNkBkNu7c_621b7eff").
                body(requestBody).
                when().
                post("http://localhost:3000/api/datasources").
                then().
                statusCode(200).
                body("message", equalTo("Datasource added")).
                body("datasource.basicAuth", equalTo(true)).
                body("datasource.basicAuthUser", equalTo("basicuser")).
                body("datasource.secureJsonFields.basicAuthPassword", equalTo(true));
    }

    @Test
    public void testCreateCloudWatchDataSource() {
        String requestBody = """
    {
      "name": "test_cloudwatch_datasource",
      "type": "cloudwatch",
      "url": "http://monitoring.us-west-1.amazonaws.com",
      "access": "proxy",
      "jsonData": {
        "authType": "keys",
        "defaultRegion": "us-west-1"
      },
      "secureJsonData": {
        "accessKey": "Ol4pIDpeKSA6XikgOl4p",
        "secretKey": "dGVzdCBrZXkgYmxlYXNlIGRvbid0IHN0ZWFs"
      }
    }
    """;

        given().
                header("Accept", "application/json").
                header("Content-Type", "application/json").
                header("Authorization", "Bearer glsa_1CsLxCX7L3ltJvA53hvFzuFCNkBkNu7c_621b7eff").
                body(requestBody).
                when().
                post("http://localhost:3000/api/datasources").
                then().
                statusCode(200).
                body("message", equalTo("Datasource added")).
                body("datasource.name", equalTo("test_cloudwatch_datasource")).
                body("datasource.type", equalTo("cloudwatch")).
                body("datasource.jsonData.defaultRegion", equalTo("us-west-1")).
                body("datasource.secureJsonFields.accessKey", equalTo(true)).
                body("datasource.secureJsonFields.secretKey", equalTo(true));
    }


}
