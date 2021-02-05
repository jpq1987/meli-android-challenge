package ar.com.meliandroidchallenge.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import ar.com.meliandroidchallenge.service.model.Item;
import ar.com.meliandroidchallenge.service.repository.MeLiRepository;

public class ItemDetailViewModel extends AndroidViewModel {

    private static final String TAG = ItemDetailViewModel.class.getSimpleName();

    private MeLiRepository meliRepository;
    private String itemsId;
    private LiveData<Item> itemLiveData;
    private LiveData<Boolean> isLoading;

    public ItemDetailViewModel(@NonNull Application application) {
        super(application);
        meliRepository = new MeLiRepository();
        itemLiveData = meliRepository.getItemLiveData();
        isLoading = meliRepository.getIsProcessing();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "ViewModel Destroyed");
    }

    /**
     * Exposes the {@link ItemDetailViewModel#itemLiveData} so the UI can observe it.
     */
    public LiveData<Item> getItemLiveData() {
        return itemLiveData;
    }

    /**
     * Exposes the {@link ItemDetailViewModel#isLoading} so the UI can observe it.
     */
    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    /**
     * Loads details for {@link Item}
     *
     * @param id The ID of the {@link Item} to load
     */
    public void loadItemDetail(String id) {
        meliRepository.getItemById(id);
    }
}
