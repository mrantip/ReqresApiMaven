package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.ResourceResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetListResourceTest {

    @Test
    public void testGetListResource() throws Exception {
        final String BASE_URL = "https://reqres.in/api/unknown";
        final ObjectMapper objectMapper = new ObjectMapper();

        Response response = RestAssured
                .given()
                .when()
                .headers("x-api-key", "reqres-free-v1")
                .get(BASE_URL)
                .then()
                .statusCode(200)
                .extract().response();

        ResourceResponse resourceResponse = objectMapper.readValue(response.asString(), ResourceResponse.class);

        assertEquals(12, resourceResponse.getTotal(), "Неверное количество страниц");
        assertEquals(6, resourceResponse.getData().size(), "Количество ресурсов не совпадает");
    }
}
