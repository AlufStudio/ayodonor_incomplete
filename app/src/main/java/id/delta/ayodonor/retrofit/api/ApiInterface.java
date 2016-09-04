package id.delta.ayodonor.retrofit.api;

import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import id.delta.ayodonor.retrofit.models.darah.DarahResponse;
import id.delta.ayodonor.retrofit.models.provinsi.ProvinsiResponse;
import id.delta.ayodonor.retrofit.models.stok.StokResponse;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 8/28/16.
 */
public interface ApiInterface {

    String BASE_URL = "http://ibacor.com/api/";
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(new OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS).build())
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
            .build();


    // http://ibacor.com/api/ayodonor?view=list_darah
    // http://ibacor.com/api/ayodonor?view=list_propinsi
    // http://ibacor.com/api/ayodonor?view=stok_darah&gol_darah=...&kode_propinsi=...&page=...
    // http://ibacor.com/api/ayodonor?view=detail_stok&stok_id=...
    // http://ibacor.com/api/ayodonor?view=jadwal_donor&kode_propinsi=...&page=...

    @GET("ayodonor")
    Call<ProvinsiResponse> getProvinsi(@Query("view")String view);

    @GET("ayodonor")
    Call<DarahResponse> getDarah(@Query("view")String view);

    @GET("ayodonor")
    Call<StokResponse> getStok(@Query("view")String view, @Query("gol_darah")String gol_darah, @Query("kode_propinsi") String kode_propinsi, @Query("page") int page);

}
