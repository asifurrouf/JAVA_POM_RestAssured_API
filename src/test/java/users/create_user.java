package users;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class create_user {

    @Test
    public void create_new_user () {
        JSONObject request = new JSONObject();

        request.put("name", "ashiq");
        request.put("job", "doctor");

        given().
                header("Content-Type", "application/json").
                accept(ContentType.JSON).
                body(request.toJSONString()).

        when().
                post("https://reqres.in/api/users").

        then().
                statusCode(201).
                body("name", equalTo("ashiq")).
                body("job", equalTo("doctor")).
                log().all();
    }
}
