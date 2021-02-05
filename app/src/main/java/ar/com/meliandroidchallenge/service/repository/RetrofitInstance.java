package ar.com.meliandroidchallenge.service.repository;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit rInstance = null;

    public static MeLiService getService(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        if(rInstance == null){
            rInstance = new Retrofit
                    .Builder()
                    .client(client)
                    .baseUrl(MeLiService.HTTPS_API_MELI_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return rInstance.create(MeLiService.class);
    }
}
