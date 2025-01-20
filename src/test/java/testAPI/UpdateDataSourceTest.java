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


public class UpdateDataSourceTest {

    @Test
    public void testUpdateDataSourceByNotExistedUID() {
        String uid = "kLtEtcRGk"; // Replace with the UID of the existing data source

        // Define the request payload
        String requestBody = """
        {
          "id": 1,
          "uid": "updated-uid",
          "orgId": 1,
          "name": "updated_test_datasource",
          "type": "graphite",
          "access": "proxy",
          "url": "http://updated-datasource.com",
          "password": "",
          "user": "",
          "database": "",
          "basicAuth": true,
          "basicAuthUser": "updateduser",
          "secureJsonData": {
            "basicAuthPassword": "updatedpassword"
          },
          "isDefault": false,
          "jsonData": null
        }
        """;

        // Send PUT request to update the data source
        given().
                header("Accept", "application/json").
                header("Content-Type", "application/json").
                header("Authorization", "Bearer glsa_1CsLxCX7L3ltJvA53hvFzuFCNkBkNu7c_621b7eff"). // Replace with your Bearer token
                body(requestBody). // Attach JSON payload
                when().
                put("http://localhost:3000/api/datasources/uid/" + uid).
                then().
                statusCode(404);// Expect HTTP 404 Not found
    }

    @Test
    public void testCreateAndUseDataSource() {
        // Step 1: Define the request body to create a new data source
        String requestBody = """
    {
      "name": "dynamic_datasource",
      "type": "graphite",
      "url": "http://dynamic-datasource.com",
      "access": "proxy",
      "basicAuth": false
    }
    """;

        // Step 2: Send POST request to create the data source and retrieve its UID
        String uid =
                given().
                        header("Accept", "application/json").
                        header("Content-Type", "application/json").
                        header("Authorization", "Bearer glsa_1CsLxCX7L3ltJvA53hvFzuFCNkBkNu7c_621b7eff").
                        body(requestBody).
                        when().
                        post("http://localhost:3000/api/datasources").
                        then().
                        statusCode(200). // Validate that the data source was created
                        body("message", equalTo("Datasource added")). // Confirm the success message
                        extract().
                        path("datasource.uid"); // Extract the UID from the response

        System.out.println("Created Data Source UID: " + uid);

        // Step 3: Use the UID to perform an update
        updateDataSource(uid);

        // Step 4: Optionally delete the created data source
        deleteDataSource(uid);
    }
    public void updateDataSource(String uid) {
        // Define the request payload for updating the data source
        String updateRequestBody = """
    {
      "name": "updated_dynamic_datasource",
      "type": "graphite",
      "url": "http://updated-datasource.com",
      "access": "proxy",
      "basicAuth": true,
      "basicAuthUser": "updateduser",
      "secureJsonData": {
        "basicAuthPassword": "updatedpassword"
      }
    }
    """;

        // Send PUT request to update the data source
        given().
                header("Accept", "application/json").
                header("Content-Type", "application/json").
                header("Authorization", "Bearer glsa_1CsLxCX7L3ltJvA53hvFzuFCNkBkNu7c_621b7eff").
                body(updateRequestBody).
                when().
                put("http://localhost:3000/api/datasources/uid/" + uid).
                then().
                statusCode(200). // Validate that the update was successful
                body("message", equalTo("Datasource updated")). // Confirm the success message
                body("datasource.name", equalTo("updated_dynamic_datasource")); // Validate the updated name

        System.out.println("Data Source updated successfully for UID: " + uid);
    }
    public void deleteDataSource(String uid) {
        // Send DELETE request to remove the data source
        given().
                header("Accept", "application/json").
                header("Content-Type", "application/json").
                header("Authorization", "Bearer glsa_1CsLxCX7L3ltJvA53hvFzuFCNkBkNu7c_621b7eff").
                when().
                delete("http://localhost:3000/api/datasources/uid/" + uid).
                then().
                statusCode(200). // Validate that the deletion was successful
                body("message", equalTo("Data source deleted")); // Confirm the success message

        System.out.println("Data Source deleted successfully for UID: " + uid);
    }


}
