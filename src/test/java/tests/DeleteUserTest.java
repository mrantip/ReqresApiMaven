package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

public class DeleteUserTest {
    final String BASE_URL = "https://reqres.in/api/users";
    final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testDeleteUser() throws Exception {
        step("Отправляем DELETE-запрос");
        Response response = RestAssured
                .given()
                .when()
                .headers("x-api-key", "reqres-free-v1")
                .delete(BASE_URL + "/2")
                .then()
                .statusCode(204)
                .extract().response();
    }
}
