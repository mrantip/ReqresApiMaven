package tests;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Работа с данными пользователей")
@Feature("Получает инфорацию о пользователях")
public class UserNotFoundTest {
    final String BASE_URL = "https://reqres.in/api/users";

    @Story("Проверка получения данных о несуществующем пользователе")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void testUserNotFound() throws Exception {
        step("Get Resource List");
        Response response = RestAssured
                .given()
                .when()
                .headers("x-api-key", "reqres-free-v1")
                .get(BASE_URL + "/266")
                .then()
                .statusCode(404)
                .extract().response();

        String responseBody = response.getBody().asString();
        assertEquals("{}", responseBody, "Тело ответа не является пустым");
    }
}
