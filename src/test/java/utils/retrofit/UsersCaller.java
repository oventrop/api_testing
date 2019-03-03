package utils.retrofit;

import model.User;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface UsersCaller {

    @GET("users/")
    public Call<List<User>> getUsers();
}
