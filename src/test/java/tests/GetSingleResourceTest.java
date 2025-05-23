package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.SingleResourceResponse;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Epic("Работа ресурсами")
@Feature("Инфа о ресурсах")
public class GetSingleResourceTest {
    final String BASE_URL = "https://reqres.in/api/unknown";
    final ObjectMapper objectMapper = new ObjectMapper();

    @Story("Получение информации о конкретном ресурсе")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testGetSingleResource() throws Exception {
        step("Get Resource List");
        Response response = RestAssured
                .given()
                .when()
                .headers("x-api-key", "reqres-free-v1")
                .get(BASE_URL + "/2")
                .then()
                .statusCode(200)
                .extract().response();

        step("Десериализация json-ответа");
        SingleResourceResponse singleResourceResponse = objectMapper.readValue(response.asString(), SingleResourceResponse.class);

        assertFalse(singleResourceResponse.getData().getName().isEmpty(), "Ресурс не содержит имени");
        assertNotNull(singleResourceResponse.getData(), "Указанного ресурса не существует");

    }

}
