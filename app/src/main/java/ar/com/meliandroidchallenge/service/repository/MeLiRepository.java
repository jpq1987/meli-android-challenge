package ar.com.meliandroidchallenge.service.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import ar.com.meliandroidchallenge.service.model.Item;
import ar.com.meliandroidchallenge.service.request.ItemsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MeLiRepository {

    private MeLiService meliService;
    //TODO agregar variables para controlar errores
    private List<Item> items;
    private MutableLiveData<Item> itemLiveData;
    private MutableLiveData<List<Item>> itemsLiveData;
    private MutableLiveData<Boolean> isProcessing;

    public MeLiRepository() {
        meliService = RetrofitInstance.getService();
        items = new ArrayList<>();
        itemLiveData = new MutableLiveData<>();
        itemsLiveData = new MutableLiveData<>();
        isProcessing = new MutableLiveData<>();
    }

    public MutableLiveData<Item> getItemLiveData() { return itemLiveData; }

    public MutableLiveData<List<Item>> getItemsLiveData() {
        return itemsLiveData;
    }

    public MutableLiveData<Boolean> getIsProcessing() { return isProcessing; }

    public void getItemsByQueryAndPaging(String siteId, String query, int offset) {
        if (offset == 0) items = new ArrayList<>();
        Call<ItemsResponse> call = meliService.getItemsByQueryAndPaging(siteId, query, offset);
        isProcessing.postValue(true);
        call.enqueue(new Callback<ItemsResponse>() {
            @Override
            public void onResponse(Call<ItemsResponse> call, Response<ItemsResponse> response) {
                ItemsResponse itemsResponse = response.body();
                if (itemsResponse != null && itemsResponse.getResults() != null) {
                    items.addAll(itemsResponse.getResults());
                    itemsLiveData.postValue(items);
                }
                isProcessing.postValue(false);
            }

            @Override
            public void onFailure(Call<ItemsResponse> call, Throwable t) {
                // TODO improve error handling
                //mutableLiveItems.setValue(null);
                Log.d("MeLiRepository", "error retrieve items " + t.toString());
                isProcessing.postValue(false);
            }
        });
    }

    public void getItemById(String id) {
        Call<Item> call = meliService.getItemById(id);
        isProcessing.postValue(true);
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                Item itemResponse = response.body();
                if (itemResponse != null) {
                    itemLiveData.postValue(itemResponse);
                }
                isProcessing.postValue(false);
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                // TODO improve error handling
                //mutableLiveItems.setValue(null);
                Log.d("MeLiRepository", "error retrieve items " + t.toString());
                isProcessing.postValue(false);
            }
        });
    }


    public void clearItems() {
        items = new ArrayList<>();
        itemsLiveData.postValue(null);
    }
}
