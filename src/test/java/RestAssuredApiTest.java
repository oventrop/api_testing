import com.jayway.restassured.response.Response;
import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;

public class RestAssuredApiTest extends BaseTest {

    @Test(groups = "API")
    public void restAssuredApiTest() {
        initRestAssured();
        given().when().get(API_PATH).then().statusCode(200);
        given().when().get(API_PATH).then().header("content-type", "application/json; charset=utf-8");

        Response response = given().when().get(API_PATH);

        Assert.assertNotNull(response.getHeader("content-type"), "Content-type header is absent.");
        Assert.assertEquals(response.getHeader("content-type"), "application/json; charset=utf-8", "Content-type header is absent.");

        List<User> actualUsers = Arrays.asList(response.getBody().as(User[].class));
        verifyUsers(actualUsers);
    }
}
