import com.jayway.restassured.response.Response;
import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;

public class ApiTest extends BaseTest {

    private List<User> expectedUsers;

    @Test
    public void retrofitApiTest() {
        initRetrofitResponse();
        // verify response status:
        Assert.assertEquals(response.code(), 200, String.format("Actual Response status is %d.", response.code()));

        // verify response headers:
        Assert.assertNotNull(response.headers().get("content-type"), "Content-type header is absent.");
        Assert.assertEquals(response.headers().get("content-type"), "application/json; charset=utf-8", "Content-type header is absent.");

        // get expected users:
        expectedUsers = getExpectedUsers();
        // get actual users:
        List<User> actualUsers = (List<User>) response.body();
        verifyUsers(actualUsers);
    }

    @Test
    public void restAssuredApiTest() {
        initRestAssured();
        given().when().get(API_PATH).then().statusCode(200);
        given().when().get(API_PATH).then().header("content-type", "application/json; charset=utf-8");

        Response response = given().when().get(API_PATH);

        Assert.assertNotNull(response.getHeader("content-type"), "Content-type header is absent.");
        Assert.assertEquals(response.getHeader("content-type"), "application/json; charset=utf-8", "Content-type header is absent.");

        List<User> actualUsers = Arrays.asList(response.getBody().as(User[].class));
        // get expected users:
        expectedUsers = getExpectedUsers();
        verifyUsers(actualUsers);
    }

    private void verifyUsers(List<User> actualUsers) {
        Assert.assertEquals(expectedUsers.size(), actualUsers.size(), "Object sizes are not equal.");
        SoftAssert softAssert = new SoftAssert();
        for (int i = 0; i < expectedUsers.size(); i++) {
            softAssert.assertEquals(expectedUsers.get(i), actualUsers.get(i), String.format("User %d is not equal to expected.", i));
        }
        softAssert.assertAll();
    }
}