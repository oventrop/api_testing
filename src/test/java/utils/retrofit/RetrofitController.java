package utils.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitController {

    private static Retrofit retrofit;

    public static UsersCaller getData(String apiSource) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(apiSource)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(UsersCaller.class);
    }
}