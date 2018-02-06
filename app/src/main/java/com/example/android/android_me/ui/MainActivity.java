 package com.example.android.android_me.ui;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

 public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener{

     //Variable Untuk Meyimpan Nilai Index dari List Gambar yang dipilih
     //Nilai Defaultnya adalah 0
     private int headIndex;
     private int bodyIndex;
     private int legIndex;

     //Variable untuk menentukan apakah pengguna menggunakan Tablet atau Tidak
     private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(findViewById(R.id.android_me_linear_layout) != null){
            //Jika terdapat 2 pane didalam sebuah device, maka variabel mTwoPane bernilai true
            mTwoPane = true;

            Button button = findViewById(R.id.next_button);
            button.setVisibility(View.GONE);

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE );

            GridView masterView = findViewById(R.id.master_list);
            masterView.setNumColumns(2);

            if(savedInstanceState == null){
                // In two-pane mode, add initial BodyPartFragments to the screen
                FragmentManager fragmentManager = getSupportFragmentManager();

                // Creating a new head fragment
                BodyPartFragment headFragment = new BodyPartFragment();
                headFragment.setImageID(AndroidImageAssets.getHeads());
                // Add the fragment to its container using a transaction
                fragmentManager.beginTransaction()
                        .add(R.id.head_container, headFragment)
                        .commit();

                // New body fragment
                BodyPartFragment bodyFragment = new BodyPartFragment();
                bodyFragment.setImageID(AndroidImageAssets.getBodies());
                fragmentManager.beginTransaction()
                        .add(R.id.body_container, bodyFragment)
                        .commit();

                // New leg fragment
                BodyPartFragment legFragment = new BodyPartFragment();
                legFragment.setImageID(AndroidImageAssets.getLegs());
                fragmentManager.beginTransaction()
                        .add(R.id.leg_container, legFragment)
                        .commit();
            }
        }else{
            mTwoPane = false;
        }
    }

     @Override
     public void OnImageSelected(int position) {
         Toast.makeText(this, "Posisi Yang Diklik = " + position, Toast.LENGTH_SHORT).show();

         //Momor bagian tubuh akan = 0 untuk fragmen kepala, 1 untuk body, 2 untuk fragmen kaki
         //Membagi oleh 12 memberi kita nilai integer ini karena setiap daftar sumber gambar memiliki ukuran
         int BodyPartNumber = position /12;

         //Untuk Menentukan Nomor Index dari Masing2 BodyPart yang di Klik
         //Nomor Index Dimulai dari 0-11
         int ListIndex = position - 12 * BodyPartNumber;

         if(mTwoPane){
             //Saat user mengklik salah satu jenis BodyPart, maka akan muncul fragment baru
             //Berdasarkan Jenis dan No Index yang di kliknya
             BodyPartFragment newFragment = new BodyPartFragment();
             switch (BodyPartNumber){
                 case 0:
                     newFragment.setImageID(AndroidImageAssets.getHeads());
                     newFragment.setImageIndex(ListIndex);
                     getSupportFragmentManager().beginTransaction()
                             .replace(R.id.head_container, newFragment)
                             .commit();
                     break;
                 case 1:
                     newFragment.setImageID(AndroidImageAssets.getBodies());
                     newFragment.setImageIndex(ListIndex);
                     getSupportFragmentManager().beginTransaction()
                             .replace(R.id.body_container, newFragment)
                             .commit();
                     break;
                 case 2:
                     newFragment.setImageID(AndroidImageAssets.getLegs());
                     newFragment.setImageIndex(ListIndex);
                     getSupportFragmentManager().beginTransaction()
                             .replace(R.id.leg_container, newFragment)
                             .commit();
                     break;
             }
         }

         //Menentukan BodyPart mana yang dipilih, serta mendefinisikan no Index gambarnya
         switch (BodyPartNumber){
             case 0:
                 headIndex = ListIndex;
                 break;
             case 1:
                 bodyIndex = ListIndex;
                 break;
             case 2:
                 legIndex = ListIndex;
                 break;
             default: break;
         }

         //Meyimpan Informasi menggunakan Bundle, yang akan dikirim pada AndroidMe activity
         Bundle b = new Bundle();
         b.putInt("headIndex", headIndex);
         b.putInt("bodyIndex", bodyIndex);
         b.putInt("legIndex", legIndex);

         final Intent intent = new Intent(this, AndroidMeActivity.class);
         intent.putExtras(b);

         Button button = findViewById(R.id.next_button);
         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 startActivity(intent);
             }
         });
    }
 }
