package cat.udl.tidic.a_favour;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = BuildConfig.API_URL;
    private static final String SALT = BuildConfig.SALT;
    private static final int ITERATIONS = BuildConfig.ITERATIONS;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static String getSalt() { return SALT;}
    public static  int getIterations(){return ITERATIONS;}
}
