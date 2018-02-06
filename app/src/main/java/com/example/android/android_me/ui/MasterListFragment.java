package com.example.android.android_me.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

public class MasterListFragment extends Fragment {

    //Digunakan untuk menentukan Informasi menenai Gambar yang di klik pada GridView
    OnImageClickListener mCallback;

    //Memanggil method OnImageSelected untuk kita gunakan pada Host Activity
    public interface OnImageClickListener{
        void OnImageSelected(int position);
    }

    public MasterListFragment(){
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //Untuk memastikan bahwa Activity Container telah menerapkan callback
        //Jika tidak, maka akan melempar Exception
        try{
            mCallback = (OnImageClickListener) context;
        }catch (ClassCastException ex){
            throw new ClassCastException(context.toString() + "Harus Mengimplementasikan OnImageClickListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootMasterFragment = inflater.inflate(R.layout.fragment_master_list, container, false);
        GridView masterView = rootMasterFragment.findViewById(R.id.master_list);
        masterView.setAdapter(new MasterListAdapter(getContext(), AndroidImageAssets.getAll()));

        //Membuat Listener, untuk menangani kejadian saat Gambar pada GridView diklik
        masterView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mCallback.OnImageSelected(position);
            }
        });
        return rootMasterFragment;
    }
}
