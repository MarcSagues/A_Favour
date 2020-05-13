package cat.udl.tidic.a_favour.models;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.common.util.ArrayUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import cat.udl.tidic.a_favour.App;
import cat.udl.tidic.a_favour.MainPageClasses.DataModel;
import cat.udl.tidic.a_favour.RetrofitClientInstance;
import cat.udl.tidic.a_favour.UserServices;
import cat.udl.tidic.a_favour.Views.LoadingPanel;
import cat.udl.tidic.a_favour.Views.LoginView;
import cat.udl.tidic.a_favour.Views.MainPage;
import cat.udl.tidic.a_favour.preferences.PreferencesProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainClassViewModel extends MainPage implements LifecycleOwner{
    //private UserModel user = new UserModel();
    private UserServices userService;
        private MutableLiveData<List<DataModel.Favour>> allFavours = new MutableLiveData<>();
    public LiveData<List<DataModel.Favour>> getAllFavours(){ return allFavours; }
    private Context c;
    public List<DataModel.Favour> listOfFavours;
    public DataModel.Favour[] favours;
    MainPage mainPage;
    DataModel.Favour[] eventList;
    public DataModel.Favour favour;
    public MutableLiveData<UserModel> userModelMutableLiveData = new MutableLiveData<>();
    public ProfileViewModel profileViewModel;



    public MainClassViewModel(Context c, MainPage m)
    {

        this.c = c;
        profileViewModel = new ProfileViewModel(c);
        userService = RetrofitClientInstance.
                getRetrofitInstance().create(UserServices.class);
        mainPage = m;
        SharedPreferences mPreferences = PreferencesProvider.providePreferences();
        String token = mPreferences.getString("all_favours", "");
        Log.d("Token:", token);
        getFavours(0);
    }

    public static void  logOut()
    {
        Context c = App.getAppContext();
        //UserServices userService = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
        SharedPreferences sp = PreferencesProvider.providePreferences();
        sp.edit().remove("token").apply();
        sp.edit().remove("id").apply();
        Intent intent = new Intent(c, LoginView.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        c.startActivity(intent);


    }

    public void logOutAPI()
    {
        userService = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
        String token = PreferencesProvider.providePreferences().getString("token","");
        Call<Void> call = userService.logOut(token);
        LoadingPanel.enableLoading(c,true);
        //noinspection NullableProblems
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response)
            {
                if (response.code() == 200)
                {
                    LoadingPanel.sendMessage("Log out OK");
                    logOut();
                }
                else
                {
                    try
                    { //Atrapar error usuari existent / correu existent
                        assert response.errorBody() != null;
                        LoadingPanel.sendMessage(Objects.requireNonNull(response.errorBody().string()));
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                LoadingPanel.enableLoading(c,false);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t)
            {
                //LoadingPanel.sendMessage("Error en eliminar el Favor");
                try { LoadingPanel.setErrorDialog(c,() -> { logOutAPI();return null; });}
                catch (Exception e) { e.printStackTrace();}
            }
        });
    }

    public void getFavours(int listnumber)
    {

        //TODO
        //Aqui es fa la crida depenent del listnumber
        userService = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
        String token = PreferencesProvider.providePreferences().getString("token","");
        Call<List<DataModel.Favour>> call = userService.getFavours(null,token);
        listOfFavours = null;
        LoadingPanel.enableLoading(c,true);
        //noinspection NullableProblems
        call.enqueue(new Callback<List<DataModel.Favour>>()
        {

            @Override
            public void onResponse(Call<List<DataModel.Favour>> call, Response<List<DataModel.Favour>> response)
            {
                Log.d("Enqueue----------","Dins");
                try
                {
                    Log.d("MainClassViewModel",""+response.code());
                   List<DataModel.Favour> response_ = response.body();

                    assert response_ != null;
                    for (int i = 0; i < response_.size(); i++)
                    {
                        response_.get(i).setIcon();

                    }

                    favours = (DataModel.Favour[]) response_.toArray(new DataModel.Favour[response_.size()]);
                    allFavours.setValue(response_);
                    LoadingPanel.enableLoading(c,false);

                }
                catch (Exception e) { Log.d("Salta el catch -------", e.getMessage() + "ERROR");}
            }

            @Override
            public void onFailure(Call<List<DataModel.Favour>> call, Throwable t)
            {
                try { LoadingPanel.setErrorDialog(c,() -> { getFavours(listnumber);return null; });}
                catch (Exception e) { e.printStackTrace();}
                Log.e("---------------", Objects.requireNonNull(t.getMessage()));
            }
        });
    }


    public DataModel.Favour[] orderList(String selectedSpinner, int ascendant){

        DataModel.Favour aux = null;
        Log.d("ORDERLIST","");
        getAllFavours().observe( this, this::onGetFavoursData);
        DataModel.Favour[] listOfFavours = eventList;
        SharedPreferences mPreferences;
        mPreferences = PreferencesProvider.providePreferences();


        try{
            switch (selectedSpinner) {


                case "Distance":
                    for (DataModel.Favour value : favours) {
                        value.setDistance(mPreferences);
                        System.out.println("Valueeeeeeeeeeeeeee "+value.distance);
                    }
                    quicksortDistance(favours, 0, favours.length-1);

                    break;


                case "Amount":
                    quicksort(favours, 0, favours.length-1);
                    for (int i=0; i<favours.length;i++) {
                        System.out.println("Distance" + favours[i].toString());
                    }
                    break;

            }

        }
        catch(NullPointerException e){
            Log.d("Favours are ",""+e);
        }
        assert false;
        System.out.println("favours = "+ Arrays.toString(favours));
        System.out.println("TOTS ELS FAVORS "+allFavours.getValue());
        if (favours != null){
            mainPage.onGetFavoursArray(favours);
        }

        return favours;
    }

    public DataModel.Favour[] orderListCategory(String selectedSpinner, int ascendant){
        DataModel.Favour[] listDataFavour = new DataModel.Favour[favours.length-1];
        int numb = 0;
        try{
        switch (selectedSpinner) {
            case "Day to day things":
                numb = 0;
                for (DataModel.Favour value : favours) {
                    if (value.category.equals("daytodaythings")) {
                        listDataFavour[numb] = value;
                        numb++;
                    }
                }
                break;

            case "Computing":

                numb = 0;
                for (DataModel.Favour value : favours) {
                    if (value.category.equals("computing")) {
                        listDataFavour[numb] = value;
                        numb++;
                    }
                }

                break;

            case "Reparations":

                numb = 0;
                for (DataModel.Favour value : favours) {
                    if (value.category.equals("reparation")) {
                        listDataFavour[numb] = value;
                        numb++;
                    }
                }

                break;

            case "Others":

                numb = 0;
                for (DataModel.Favour value : favours) {
                    if (value.category.equals("others") && value != null) {
                        listDataFavour[numb] = value;
                        numb++;
                    }
                }

                break;


        }
            if (favours != null){
                mainPage.onGetFavoursArray(listDataFavour);
            }

        } catch(NullPointerException error){
            Log.e("orderListCategory ", error.getLocalizedMessage() );
        }
        return listDataFavour;
    }

    private void onGetFavoursData(List<DataModel.Favour> all_f) {
        eventList = all_f.toArray(new DataModel.Favour[0]);
    }


    public static void quicksort (DataModel.Favour[] lista1, int izq, int der){
        int i=izq;
        int j=der;
        DataModel.Favour pivote=lista1[(i+j)/2];
        do {
            while (lista1[i].amount < pivote.amount){
                i++;
            }
            while (lista1[j].amount >pivote.amount){
                j--;
            }
            if (i<=j){
                DataModel.Favour aux=lista1[i];
                lista1[i]=lista1[j];
                lista1[j]=aux;
                i++;
                j--;
            }
        }while(i<=j);
        if (izq<j){
            quicksort(lista1, izq, j);
        }
        if (i<der){
            quicksort(lista1, i, der);
        }
    }
    public static void quicksortDistance (DataModel.Favour[] lista1, int izq, int der){
        int i=izq;
        int j=der;
        DataModel.Favour pivote=lista1[(i+j)/2];
        do {
            while (lista1[i].distance < pivote.distance){
                i++;
            }
            while (lista1[j].distance >pivote.distance){
                j--;
            }
            if (i<=j){
                DataModel.Favour aux=lista1[i];
                lista1[i]=lista1[j];
                lista1[j]=aux;
                i++;
                j--;
            }
        }while(i<=j);
        if (izq<j){
            quicksort(lista1, izq, j);
        }
        if (i<der){
            quicksort(lista1, i, der);
        }
    }





}
