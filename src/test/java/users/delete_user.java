package users;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class delete_user {

    @Test
    public void delete_user() {
        JSONObject request = new JSONObject();

        request.put("name", "ashiq");
        request.put("job", "qa");

        given().
                header("Content-Type", "application/json").
                accept(ContentType.JSON).
                body(request.toJSONString()).

        when().
                delete("https://reqres.in/api/users/2").

         then().
                statusCode(204)
               .
                log().all();
    }
}
