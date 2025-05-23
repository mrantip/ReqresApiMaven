package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.UserData;
import models.UserResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class GetListUsersTest {

    @Test
    public void testGetListUsers() throws Exception {
        final String BASE_URL = "https://reqres.in/api/users";
        final ObjectMapper objectMapper = new ObjectMapper();

        Response response = RestAssured
                .given()
                .when()
                .headers("x-api-key", "reqres-free-v1")
                .get(BASE_URL)
                .then()
                .statusCode(200)
                .extract().response();

        UserResponse userResponse = objectMapper.readValue(response.asString(), UserResponse.class);


        assertEquals(6, userResponse.getData().size(), "Количество пользователей не совпадает");
        assertEquals(1, userResponse.getPage(), "Неверная страница");
        assertEquals(2, userResponse.getTotal_pages(), "Неверное общее количество страниц");
        assertEquals(12, userResponse.getTotal(), "Неверное количество страниц");

        for (UserData user : userResponse.getData()) {
            assertTrue(user.getEmail().endsWith("@reqres.in"), "Email пользователя "
                    + user.getId() + " не оканчивается на @reqres.in");
        }
    }
}
