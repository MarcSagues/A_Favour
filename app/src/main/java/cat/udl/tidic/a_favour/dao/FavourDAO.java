package cat.udl.tidic.a_favour.dao;

import com.google.gson.JsonObject;

import java.util.List;

import cat.udl.tidic.a_favour.MainPageClasses.DataModel;
import cat.udl.tidic.a_favour.models.CategoryEnum;
import cat.udl.tidic.a_favour.models.Favour;
import cat.udl.tidic.a_favour.models.FavourTypeEnum;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FavourDAO {

    @GET("/favours")
    Call<List<Favour>> getFavours(@Query("user_id") String user_id,
                                  @Header("Authorization") String tokenAuth,
                                  @Query("type") FavourTypeEnum type,
                                  @Query("category") CategoryEnum categor);

    @POST("/favours/post/")
    Call<Void> postFavour(@Header ("Authorization") String tokenAuth,
                          @Body Favour favour);

    @POST("/favours/update/{favour_id}")
    Call<Void> setFavours(@Header ("Authorization") String tokenAuth,
                          @Path(value = "favour_id") int favour_id,
                          @Body Favour favour);

    @GET("/favours/delete/{id}")
    Call<Void> deleteFavour(@Header ("Authorization") String tokenAuth,
                            @Path (value = "id") int id);


}
