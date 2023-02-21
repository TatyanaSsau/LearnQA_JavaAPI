import io.restassured.RestAssured;
import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class CourseTest {
    @Test
    public void testJavaWorld() {

        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/get_text")
                .andReturn();
        response.prettyPrint();
    }

    @Test
    public void nameJohn() {
        Map<String, String> params = new HashMap<>();
        params.put("name", "John");

        Response response = RestAssured
                .given()
                .queryParams(params)
                .get("https://playground.learnqa.ru/api/hello")
                .andReturn();
        response.prettyPrint();
    }

    @Test
    public void cookieTest() {
        Map<String, String> data = new HashMap<>();
        data.put("login", "secret_login2");
        data.put("password", "secret_pass2");

        Response responseForGet = RestAssured
                .given()
                .body(data)
                .when()
                .post("https://playground.learnqa.ru/api/get_auth_cooki")
                .andReturn();

        String responseCookie = responseForGet.getCookie("auth_cookie");
    }

    @Test
    public void parsingTest() {
        Map<String, String> para = new HashMap<>();
       para.put("name", "Parsing");

        JsonPath responsePars = RestAssured
                .given()
                .queryParams(para)
                .when()
                .post("https://playground.learnqa.ru/api/hello")
                .jsonPath();

        String answer = responsePars.get("answer");
        System.out.println(answer);
    }
}
