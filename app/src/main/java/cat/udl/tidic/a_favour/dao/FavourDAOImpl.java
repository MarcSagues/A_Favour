package cat.udl.tidic.a_favour.dao;

import java.util.List;

import cat.udl.tidic.a_favour.RetrofitClientInstance;
import cat.udl.tidic.a_favour.models.CategoryEnum;
import cat.udl.tidic.a_favour.models.Favour;
import cat.udl.tidic.a_favour.models.FavourTypeEnum;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

public class FavourDAOImpl implements FavourDAO {

    private Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();


        @Override
        public Call<List<Favour>> getFavours(String user_id, String tokenAuth,
                                             FavourTypeEnum type, CategoryEnum category) {
            return  retrofit.create(FavourDAO.class).getFavours(user_id, tokenAuth, type, category);
        }

        @Override
        public Call<Void> postFavour(String tokenAuth, Favour favour) {
            return  retrofit.create(FavourDAO.class).postFavour(tokenAuth, favour);
        }

        @Override
        public Call<Void> setFavours(String tokenAuth, int favour_id, Favour favour) {
            return  retrofit.create(FavourDAO.class).setFavours(tokenAuth, favour_id, favour);
        }

        @Override
        public Call<Void> deleteFavour(String tokenAuth, int id) {
            return  retrofit.create(FavourDAO.class).deleteFavour(tokenAuth, id);
        }

}