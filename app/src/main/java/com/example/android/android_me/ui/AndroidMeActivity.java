/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.android_me.ui;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

//Activity ini akan menampilkan gambar Android custom yang terdiri dari tiga bagian tubuh: kepala, badan, dan kaki
public class AndroidMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

        //Memeriksa Jika Tidak ada Instance yang tersimpan, maka akan membuat Fragment baru
        if(savedInstanceState == null){
            //Inisialisai Objek BodyPartFragment
            BodyPartFragment headPart = new BodyPartFragment();
            headPart.setImageID(AndroidImageAssets.getHeads());
            int headIndex = getIntent().getIntExtra("headIndex", 0);
            headPart.setImageIndex(headIndex);

            BodyPartFragment bodyPart = new BodyPartFragment();
            bodyPart.setImageID(AndroidImageAssets.getBodies());
            int bodyIndex = getIntent().getIntExtra("bodyIndex", 0);
            bodyPart.setImageIndex(bodyIndex);

            BodyPartFragment legPart = new BodyPartFragment();
            legPart.setImageID(AndroidImageAssets.getLegs());
            int legIndex = getIntent().getIntExtra("legIndex", 0);
            legPart.setImageIndex(legIndex);

            //Membuat FragmentManager dan Transaction untuk menambahkan Fragment kedalam Activity
            FragmentManager FM = getSupportFragmentManager();
            FM.beginTransaction()
                    .add(R.id.head_container, headPart)
                    .add(R.id.body_container, bodyPart)
                    .add(R.id.leg_container, legPart)
                    .commit();
        }
    }
}
