package signin;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class SignInFunctionality {

    @Test
    public void valid_signIn() {

        JSONObject request = new JSONObject();

        request.put("email", "eve.holt@reqres.in");
        request.put("password", "cityslicka");

        given().
                header("Content-Type", "application/json").
                accept(ContentType.JSON).
                body(request.toJSONString()).
        when().
                post("https://reqres.in/api/login").

        then().
                statusCode(200).
                body("token", equalTo("QpwL5tke4Pnpja7X4"));
    }

    @Test
    public void signIn_onlyEmail() {

        JSONObject request = new JSONObject();

        request.put("email", "eve.holt@reqres.in");

        given().
                header("Content-Type", "application/json").
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                post("https://reqres.in/api/login").
                then().
                statusCode(400).
                body("error", equalTo("Missing password"));
    }

  /*  @Test
    public void signIn_wrongPassword() {

        JSONObject request = new JSONObject();

        request.put("email", "eve.holt@reqres.in");
        request.put("password", "cityslicka2");

        given().
                header("Content-Type", "application/json").
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                post("https://reqres.in/api/login").
                then().
                statusCode(200).
                body("error", equalTo("user not found"));
    }*/
}
