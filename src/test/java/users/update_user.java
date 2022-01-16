package users;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class update_user {

    @Test
    public void update_user() {
        JSONObject request = new JSONObject();

        request.put("name", "ashiq");
        request.put("job", "qa");

        given().
                header("Content-Type", "application/json").
                accept(ContentType.JSON).
                body(request.toJSONString()).

                when().
                put("https://reqres.in/api/users/2").

                then().
                statusCode(200).
                body("name", equalTo("ashiq")).
                body("job", equalTo("qa")).
                log().all();
    }
}
