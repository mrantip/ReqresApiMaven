package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.SingleUserResponse;
import models.UserData;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Работа с данными пользователей")
@Feature("Получает инфорацию о пользователях")
public class GetSingleUserTest {
    final String BASE_URL = "https://reqres.in/api/users";
    final ObjectMapper objectMapper = new ObjectMapper();

    @Story("Получение информации о конкретном пользователе")
    @Description("Получение информации о конкрентном пользователе а точнее всех его данных включая аватарку. Всеее!")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testGetSingleUser() throws Exception {
        step("Get Resource List");
        Response response = RestAssured
                .given()
                .when()
                .get(BASE_URL + "/2")
                .then()
                .statusCode(200)
                .extract().response();

        step("Десериализация json-ответа");
        SingleUserResponse singleUserResponse = objectMapper.readValue(response.asString(), SingleUserResponse.class);

        UserData user = singleUserResponse.getData();

        assertTrue(user.getEmail().endsWith("@reqres.in"), "Email пользователя "
                + user.getId() + " не оканчивается на @reqres.in");
    }
}
