package registration;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class registration_functionality {

    @Test
    public void valid_signup () {
        JSONObject request = new JSONObject();

        request.put("email", "eve.holt@reqres.in");
        request.put("password", "pistol");

        given().
                header("Content-Type", "application/json").
                accept(ContentType.JSON).
                body(request.toJSONString()).

       when().
                post("https://reqres.in/api/register").

       then().
                statusCode(200).
                body("token", equalTo("QpwL5tke4Pnpja7X4")).
                body("id", equalTo(4));
    }

    @Test
    public void wrong_credential () {
        JSONObject request = new JSONObject();

        request.put("email", "ash.holt@reqres.in");
        request.put("password", "pistol");

        given().
                header("Content-Type", "application/json").
                accept(ContentType.JSON).
                body(request.toJSONString()).

        when().
                post("https://reqres.in/api/register").

        then().
                statusCode(400).
                body("error", equalTo("Note: Only defined users succeed registration"));
    }

    @Test
    public void invalid_signup () {
        JSONObject request = new JSONObject();

        request.put("email", "eve.holt@reqres.in");


        given().
                header("Content-Type", "application/json").
                accept(ContentType.JSON).
                body(request.toJSONString()).

                when().
                post("https://reqres.in/api/register").

                then().
                statusCode(400).
                body("error", equalTo("Missing password"));
    }

    @Test
    public void signup_onlyPassword () {
        JSONObject request = new JSONObject();

        request.put("password", "pistol");


        given().
                header("Content-Type", "application/json").
                accept(ContentType.JSON).
                body(request.toJSONString()).

                when().
                post("https://reqres.in/api/register").

                then().
                statusCode(400).
                body("error", equalTo("Missing email or username"));
    }

}
