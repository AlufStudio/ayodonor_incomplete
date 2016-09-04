package id.delta.ayodonor.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.delta.ayodonor.R;
import id.delta.ayodonor.retrofit.models.stok.Datum;

/**
 * Created by Administrator on 8/29/16.
 */
public final class StokAdapters extends RecyclerView.Adapter<StokAdapters.ViewHolder>{

    Context context;
    List<Datum> stokList = new ArrayList<>();

    public StokAdapters(Context context, List<Datum> stokList){
        this.context = context;
        this.stokList = stokList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stok_darah, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Datum data = stokList.get(position);
        holder.itDarah.setText(data.getGolDarah());
        holder.itJumlah.setText(data.getJumStok());
        holder.itLokasi.setText(data.getLokasi());
    }

    @Override
    public int getItemCount() {
        return stokList.size();
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.item_darah)TextView itDarah;
        @Bind(R.id.item_jumlah)TextView itJumlah;
        @Bind(R.id.item_lokasi)TextView itLokasi;

        public ViewHolder (View view){
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
