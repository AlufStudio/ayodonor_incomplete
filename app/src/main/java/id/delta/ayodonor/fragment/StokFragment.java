package id.delta.ayodonor.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.delta.ayodonor.R;
import id.delta.ayodonor.adapters.DataAdapter;
import id.delta.ayodonor.adapters.EndlessRecyclerOnScrollListener;
import id.delta.ayodonor.adapters.OnLoadMoreListener;
import id.delta.ayodonor.adapters.StokAdapters;
import id.delta.ayodonor.retrofit.api.ApiInterface;
import id.delta.ayodonor.retrofit.models.darah.DarahResponse;
import id.delta.ayodonor.retrofit.models.provinsi.Datum;
import id.delta.ayodonor.retrofit.models.provinsi.ProvinsiResponse;
import id.delta.ayodonor.retrofit.models.stok.StokResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 8/28/16.
 */
public class StokFragment extends Fragment {

    @Bind(R.id.spinner_darah)Spinner spDarah;
    @Bind(R.id.spinner_provinsi)Spinner spProvinsi;
    @Bind(R.id.recycler_stok)RecyclerView rcStok;
    @Bind(R.id.tombol_cek)Button btnCek;

    private String[] darahList = { "Semua Darah", "O Positif", "O Negatif" , "A Positif", "A Negatif", "B Positif", "B Negatif" , "AB Positif", "AB Negatif" };
    String stDarah;
    String stProvinsi;
    private ArrayList<String> darah;
    private ArrayList<String> provinsi;
    private ArrayList<String> kode;
    ProvinsiResponse provinsiResponse;
    DarahResponse darahResponse;
    StokResponse stokResponse;
    Datum data;
    ApiInterface apiInterface;
    ProgressDialog pgDialog;
    private DataAdapter adapter;

    protected Handler handler;
    int nextPage;


    // StokAdapter adapter;
  //  StokAdapters adapter;
    List<id.delta.ayodonor.retrofit.models.stok.Datum> stokList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_stok, container, false);
        ButterKnife.bind(this, view);
        apiInterface = ApiInterface.retrofit.create(ApiInterface.class);
        getProvinsi();
        getDarah();
        isiSpinner();
        setupRecycler();
        handler = new Handler();
        pgDialog = new ProgressDialog(view.getContext());
        pgDialog.setIndeterminate(true);
        pgDialog.setCancelable(false);
        return view;
    }

    private void isiSpinner(){
        provinsi = new ArrayList<String>();
        kode = new ArrayList<String>();

        ArrayAdapter<String> adapterDarah = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, darahList);
        adapterDarah.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDarah.setAdapter(adapterDarah);
    }

    private void setupRecycler(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcStok.setLayoutManager(linearLayoutManager);
        adapter = new DataAdapter(stokList, rcStok);
        rcStok.setHasFixedSize(true);
        adapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                stokList.add(null);
                nextPage = stokResponse.getPage().getNextPage();
                getStok(nextPage);
                adapter.notifyItemInserted(stokList.size()-1);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        stokList.remove(stokList.size()-1);
                        adapter.notifyItemRemoved(stokList.size());
                        int start = stokList.size();
                        int end = start + 10;
                        for (int i = start +1; i<=end;i++){

                            adapter.notifyItemInserted(stokList.size());
                        }
                        adapter.setLoaded();
                    }
                }, 2000);
            }
        });
      /*  adapter = new StokAdapters(getContext(), stokList);
        rcStok.setOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                nextPage = stokResponse.getPage().getNextPage();
                getStok(nextPage);
            }
        });*/

        btnCek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(provinsi==null){
                    getProvinsi();
                }else{

                }
            }
        });

    }

    private void getDarah(){
        darah = new ArrayList<String>();
        apiInterface.getDarah("list_darah").enqueue(new Callback<DarahResponse>() {
            @Override
            public void onResponse(Call<DarahResponse> call, Response<DarahResponse> response) {
                darahResponse = response.body();
                if(darahResponse.getStatus().equals("success")){
                    for(int i=0; i<darahResponse.getData().size();i++){
                        darah.add(darahResponse.getData().get(i));
                    }
                }
                spDarah.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        stDarah = darah.get(i);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

            @Override
            public void onFailure(Call<DarahResponse> call, Throwable t) {
                infoGagal(t);
            }
        });
    }

    private void getProvinsi(){
        apiInterface.getProvinsi("list_propinsi").enqueue(new Callback<ProvinsiResponse>() {
            @Override
            public void onResponse(Call<ProvinsiResponse> call, Response<ProvinsiResponse> response) {
                provinsiResponse = response.body();
                if (provinsiResponse.getStatus().equals("success")){
                    for(int i = 0; i<provinsiResponse.getData().size(); i++){
                        data = provinsiResponse.getData().get(i);
                        provinsi.add(data.getPropinsi());
                        kode.add(data.getKode());
                    }
                    spProvinsi.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, provinsi));
                    spProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            stProvinsi = kode.get(i);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    btnCek.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            getStok(1);
                            stokList.clear();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ProvinsiResponse> call, Throwable t) {
                infoGagal(t);
            }
        });
    }

    private void getStok(int halaman){
    //    pgDialog.setMessage("Sedang mengambil data...");
    //    pgDialog.show();
        apiInterface.getStok("stok_darah", stDarah, stProvinsi, halaman).enqueue(new Callback<StokResponse>() {
            @Override
            public void onResponse(Call<StokResponse> call, Response<StokResponse> response) {
                pgDialog.dismiss();
                stokResponse = response.body();
                if (stokResponse.getStatus().equals("success")){
                    for(int i = 0; i<stokResponse.getData().size(); i++){
                        id.delta.ayodonor.retrofit.models.stok.Datum datum = stokResponse.getData().get(i);
                        stokList.add(datum);
                        rcStok.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<StokResponse> call, Throwable t) {
                infoGagal(t);
            }
        });
    }

    private void infoGagal(Throwable t){
        pgDialog.dismiss();
        if(t instanceof SocketTimeoutException) {
            Snackbar.make(getView(), "Request Timeout. Please try again!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }else{
            Snackbar.make(getView(), "Connection Error!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        Log.i("FAILURE", t.toString());
    }


}
