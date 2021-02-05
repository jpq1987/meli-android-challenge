package ar.com.meliandroidchallenge.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ar.com.meliandroidchallenge.service.model.Item;
import ar.com.meliandroidchallenge.service.repository.MeLiRepository;

public class SearchViewModel extends AndroidViewModel {
    private static final String TAG = SearchViewModel.class.getSimpleName();

    private MeLiRepository meliRepository;
    private int offset;
    private String query;
    private LiveData<List<Item>> itemsLiveData;
    private LiveData<Boolean> isProcessing;

    public SearchViewModel(@NonNull Application application) {
        super(application);
        meliRepository = new MeLiRepository();
        itemsLiveData = meliRepository.getItemsLiveData();
        isProcessing = meliRepository.getIsProcessing();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "ViewModel Destroyed");
    }

    /**
     * Exposes the {@link SearchViewModel#itemsLiveData} so the UI can observe it.
     */
    public LiveData<List<Item>> getItemsLiveData() {
        return itemsLiveData;
    }

    /**
     * Exposes the {@link SearchViewModel#isProcessing} so the UI can observe it.
     */
    public LiveData<Boolean> getIsProcessing() {
        return isProcessing;
    }

    /**
     * Searches {@link Item} that matches with the query, starting from specified offset
     *
     * @param query The string to search
     */
    public void getItemsByQuery(String query) {
        this.query = query;
        meliRepository.getItemsByQueryAndPaging("MLA", this.query, 0);
    }

    /**
     * Loads more {@link Item} that matches with the last query, starting from specified offset
     *
     * @param offset The offset to start to search
     */
    public void getLoadMoreItems(int offset) {
        meliRepository.getItemsByQueryAndPaging("MLA", this.query, offset);
    }

    /**
     * Cleans the {@link Item} search performed
     *
     */
    public void cleanItemsResult() {
        this.query = "";
        meliRepository.clearItems();
    }
}
