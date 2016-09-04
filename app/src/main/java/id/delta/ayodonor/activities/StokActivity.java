package id.delta.ayodonor.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.delta.ayodonor.R;

/**
 * Created by Administrator on 8/28/16.
 */
public class StokActivity extends AppCompatActivity {

    @Bind(R.id.recycler_provinsi)RecyclerView recyclerView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provinsi);
        ButterKnife.bind(this);
        setupRecycler();

    }

    private void setupRecycler(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
       // adapter = new ProvinsiAdapter(this, provinsiList);
      //  recyclerView.setAdapter(adapter);
    }
}
