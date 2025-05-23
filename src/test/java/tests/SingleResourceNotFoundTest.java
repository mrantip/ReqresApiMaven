package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingleResourceNotFoundTest {
    @Test
    public void testSingleResourceNotFound() throws Exception {
        final String BASE_URL = "https://reqres.in/api/unknown";
        final ObjectMapper objectMapper = new ObjectMapper();

        Response response = RestAssured
                .given()
                .when()
                .headers("x-api-key", "reqres-free-v1")
                .get(BASE_URL + "/233")
                .then()
                .statusCode(404)
                .extract().response();

        assertEquals("{}", response.getBody().asString(), "Тело ответа не является пустым");

    }
}
