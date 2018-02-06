package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;

import java.util.ArrayList;
import java.util.List;

//Digunakan Untuk Body Part Kepala
public class BodyPartFragment extends Fragment {

    //Membuat Variable String, Untuk meyimpan informasi mengenai IMAGE ID fan NO Index.
    public static final String IMAGE_ID_LIST = "image_ids";
    public static final String LIST_INDEX = "list_index";

    public static final String TAG = "BodyPartFragment";

    //Variable yang digunakan untuk menyimpan sumber gambar dan No Index
    private List<Integer> ImageID;
    private int ImageIndex;

    //Membuat Konstruktor untuk Instansi
    public BodyPartFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Memuat instance yang tersimpan (ImageID dan ImageIndex) jika ada
        if(savedInstanceState != null){
            ImageID = savedInstanceState.getIntegerArrayList(IMAGE_ID_LIST);
            ImageIndex = savedInstanceState.getInt(LIST_INDEX);
        }

        //Inisialisasi View untuk menginflate layout/UI Fragment
        final View rootBody = inflater.inflate(R.layout.fragment_body_part, container, false);

        //Deklarsi dan Inisialisai ID ImageView
        final ImageView BodyPart = (ImageView) rootBody.findViewById(R.id.body_part_image_view);
        if (ImageID != null) {
        BodyPart.setImageResource(ImageID.get(ImageIndex));

        //Menaani Kejadian saat BodyPartImage di Klik, maka akan berpindah No Indexnya
        BodyPart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Memerikasa apakah imageID tidak Null
                    if (ImageIndex < ImageID.size() - 1) {
                        ImageIndex++;
                    } else {
                        ImageIndex = 0;
                    }
                    BodyPart.setImageResource(ImageID.get(ImageIndex));
            }
        });
        } else {
            //Jika Null, maka akan menampilkan pesan
            Log.d(TAG, "Fragment Ini tidak mempunyai daftar Image ID ");
        }
        return rootBody;
    }

    //Digunakan untuk menerima daftar gambar yang akan dikerjakan
    public void setImageID(List<Integer> ImageID) {
        this.ImageID = ImageID;
    }

    //Menentukan ID gambar mana yang akan ditampilkan
    public void setImageIndex(int ImageIndex) {
        this.ImageIndex = ImageIndex;
    }

    //Simpan kondisi yang sudah user set di Fragment ini
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putIntegerArrayList(IMAGE_ID_LIST, (ArrayList<Integer>) ImageID);
        outState.putInt(LIST_INDEX, ImageIndex);
    }
}
