package cat.udl.tidic.a_favour.viewmodels;

import android.location.Location;
import android.util.Log;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cat.udl.tidic.a_favour.models.CategoryEnum;
import cat.udl.tidic.a_favour.models.Favour;
import cat.udl.tidic.a_favour.models.FavourTypeEnum;
import cat.udl.tidic.a_favour.repositories.FavourRepo;

public class FavoursViewModel {


    private final String TAG = "EventsViewModel";
    private FavourRepo favourRepo;

    public FavoursViewModel() {
        favourRepo = new FavourRepo();
    }

    public void getFavours(String user_id, FavourTypeEnum type, CategoryEnum category){
        favourRepo.getFavours(user_id,type, category);
    }

    public MutableLiveData<List<Favour>> getResponseFavourData() {
        return this.favourRepo.getmDataResponseFavour();
    }

    public MutableLiveData<String> getResponseFavourMsg() {
        return this.favourRepo.getmMsgResponseFavour();
    }

    public void sortByAscendingDistance(Location currentLocation){
        List<Favour> favours = this.favourRepo.getmDataResponseFavour().getValue();
        Collections.sort(favours, new Comparator<Favour>() {
            @Override
            public int compare(Favour o1, Favour o2) {
                return (o1.getDistanceTo(currentLocation) - (o2.getDistanceTo(currentLocation)));
            }
        });
        Log.d(TAG,"sortByAscendingDistance() -> " + favours);
        this.favourRepo.getmDataResponseFavour().setValue(favours);
    }

    public void sortByDescendingDistance(Location currentLocation){
        List<Favour> favours = this.favourRepo.getmDataResponseFavour().getValue();
        Collections.sort(favours, new Comparator<Favour>() {
            @Override
            public int compare(Favour o1, Favour o2) {
                return (o1.getDistanceTo(currentLocation) - (o2.getDistanceTo(currentLocation)));
            }
        });
        Log.d(TAG,"sortByDescendingDistance() -> " + favours);
        Collections.reverse(favours);
        this.favourRepo.getmDataResponseFavour().setValue(favours);
    }

    public void sortByAscendingPrice(){
        List<Favour> favours = this.favourRepo.getmDataResponseFavour().getValue();
        Collections.sort(favours, new Comparator<Favour>() {
            @Override
            public int compare(Favour o1, Favour o2) {
                return (int) ((o1.getAmount() - (o2.getAmount())));
            }
        });
        Log.d(TAG,"sortByAscendingPrice() -> " + favours);
        this.favourRepo.getmDataResponseFavour().setValue(favours);
    }

    public void sortByDescendingPrice(){
        List<Favour> favours = this.favourRepo.getmDataResponseFavour().getValue();
        Collections.sort(favours, new Comparator<Favour>() {
            @Override
            public int compare(Favour o1, Favour o2) {
                return (int) ((o1.getAmount() - (o2.getAmount())));
            }
        });
        Log.d(TAG,"sortByDescendingPrice() -> " + favours);
        Collections.reverse(favours);
        this.favourRepo.getmDataResponseFavour().setValue(favours);
    }


}
