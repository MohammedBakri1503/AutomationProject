
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;


public class GetDataSourceTest {


        private final String BASE_URL = "http://localhost:3000/api";
        private final String BEARER_TOKEN = "Bearer glsa_1CsLxCX7L3ltJvA53hvFzuFCNkBkNu7c_621b7eff";

        @Test
        public void testCreateAndRetrieveDataSource() {


            // Step 1: Create a new data source
            String dataSourceName = "unique_test_datasource"; // Ensure this name is unique
            given().
                    header("Accept", "application/json"). // Expected response format
                    header("Content-Type", "application/json"). // Request format
                    header("Authorization", "Bearer glsa_1CsLxCX7L3ltJvA53hvFzuFCNkBkNu7c_621b7eff"). // Bearer token
                    when().
                    delete("http://localhost:3000/api/datasources/name/" + dataSourceName);// Endpoint with the data source name

            String createRequestBody = """
        {
          "name": "unique_test_datasource",
          "type": "graphite",
          "url": "http://unique-datasource.com",
          "access": "proxy",
          "basicAuth": false
        }
        """;

            given().
                    header("Accept", "application/json").
                    header("Content-Type", "application/json").
                    header("Authorization", BEARER_TOKEN).
                    body(createRequestBody).
                    when().
                    post(BASE_URL + "/datasources").
                    then().
                    statusCode(200).
                    body("message", equalTo("Datasource added"));

            System.out.println("Step 1: Data source created with name: " + dataSourceName);

            // Step 2: Retrieve the ID of the newly created data source by name
            int dataSourceId =
                    given().
                            header("Accept", "application/json").
                            header("Authorization", BEARER_TOKEN).
                            when().
                            get(BASE_URL + "/datasources/id/" + dataSourceName).
                            then().
                            statusCode(200).
                            extract().
                            path("id");

            System.out.println("Step 2: Retrieved ID of the data source: " + dataSourceId);

            // Step 3: Retrieve the UID of the data source using its ID
            String dataSourceUID =
                    given().
                            header("Accept", "application/json").
                            header("Authorization", BEARER_TOKEN).
                            when().
                            get(BASE_URL + "/datasources/" + dataSourceId).
                            then().
                            statusCode(200).
                            extract().
                            path("uid");

            System.out.println("Step 3: Retrieved UID of the data source: " + dataSourceUID);

            // Step 4: Retrieve the data source details using the UID
            given().
                    header("Accept", "application/json").
                    header("Authorization", BEARER_TOKEN).
                    when().
                    get(BASE_URL + "/datasources/uid/" + dataSourceUID).
                    then().
                    statusCode(200).
                    body("uid", equalTo(dataSourceUID)).
                    body("name", equalTo(dataSourceName)).
                    body("type", equalTo("graphite"));

            System.out.println("Step 4: Successfully retrieved data source details using UID: " + dataSourceUID);
        }
    }



