package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.ResourceResponse;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Работа ресурсами")
@Feature("Инфа о ресурсах")
public class GetListResourceTest {
    final String BASE_URL = "https://reqres.in/api/unknown";
    final ObjectMapper objectMapper = new ObjectMapper();

    @Story("Получение информации списке ресурсов")
    @Severity(SeverityLevel.MINOR)
    @Test
    public void testGetListResource() throws Exception {
        step("Get Resource List");
        Response response = RestAssured
                .given()
                .when()
                .headers("x-api-key", "reqres-free-v1")
                .get(BASE_URL)
                .then()
                .statusCode(200)
                .extract().response();

        step("Десериализация json-ответа");
        ResourceResponse resourceResponse = objectMapper.readValue(response.asString(), ResourceResponse.class);

        assertEquals(12, resourceResponse.getTotal(), "Неверное количество страниц");
        assertEquals(6, resourceResponse.getData().size(), "Количество ресурсов не совпадает");
    }
}
