package tests;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Работа ресурсами")
@Feature("Инфа о ресурсах")
public class SingleResourceNotFoundTest {
    final String BASE_URL = "https://reqres.in/api/unknown";

    @Story("Проверка получения данных о несуществующем ресурсе")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void testSingleResourceNotFound() throws Exception {
        step("Get Resource List");
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
