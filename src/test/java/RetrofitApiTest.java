import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class RetrofitApiTest extends BaseTest {

    @Test(groups = "API")
    public void retrofitApiTest() {
        initRetrofitResponse();
        // verify response status:
        Assert.assertEquals(response.code(), 200, String.format("Actual Response status is %d.", response.code()));

        // verify response headers:
        Assert.assertNotNull(response.headers().get("content-type"), "Content-type header is absent.");
        Assert.assertEquals(response.headers().get("content-type"), "application/json; charset=utf-8", "Content-type header is absent.");

        // get actual users:
        List<User> actualUsers = (List<User>) response.body();
        verifyUsers(actualUsers);
    }
}