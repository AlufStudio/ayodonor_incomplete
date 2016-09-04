package id.delta.ayodonor.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.delta.ayodonor.R;


/**
 * Created by Administrator on 8/28/16.
 */
public class JadwalFragment extends Fragment {

    public static JadwalFragment newInstance() {
        return new JadwalFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_stok, container, false);

        return view;
    }
}
