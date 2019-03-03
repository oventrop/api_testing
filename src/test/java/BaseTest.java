import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;
import model.User;
import org.testng.annotations.BeforeTest;
import retrofit2.Response;
import utils.restassured.RestAssuredController;
import utils.retrofit.RetrofitController;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class BaseTest {
    protected static final String API_SOURCE = "https://jsonplaceholder.typicode.com/";
    protected static final String API_PATH = "users";
    private static final String JSON_PATH = "src/test/resources/data";

    Response response;


    protected void initRetrofitResponse() {
        try {
            response = RetrofitController.getData(API_SOURCE).getUsers().execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected List<User> getExpectedUsers() {
        Gson converter = new Gson();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(JSON_PATH));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        User[] users = converter.fromJson(reader, User[].class);
        return Arrays.asList(users);
    }

    void initRestAssured() {
        RestAssuredController.initController(API_SOURCE);
        RestAssured.defaultParser = Parser.JSON;;
    }
}
