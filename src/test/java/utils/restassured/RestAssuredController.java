package utils.restassured;

import com.jayway.restassured.RestAssured;

public class RestAssuredController {

    public static void initController(String apiSource) {
        RestAssured.baseURI = apiSource;
    }
}