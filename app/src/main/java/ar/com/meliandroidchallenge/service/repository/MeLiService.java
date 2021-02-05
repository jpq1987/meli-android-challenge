package ar.com.meliandroidchallenge.service.repository;

import ar.com.meliandroidchallenge.service.model.Item;
import ar.com.meliandroidchallenge.service.request.ItemsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MeLiService {
    String HTTPS_API_MELI_URL = "https://api.mercadolibre.com/";

    @GET("sites/{SITE_ID}/search")
    Call<ItemsResponse> getItemsByQueryAndPaging(@Path("SITE_ID") String siteId, @Query("q") String query, @Query("offset") int offset);

    @GET("items/{ITEM_ID}")
    Call<Item> getItemById(@Path("ITEM_ID") String id);

    @GET("reviews/item/{ITEM_ID}")
    Call<Item> getItemReviewsById(@Path("ITEM_ID") String id);
}
