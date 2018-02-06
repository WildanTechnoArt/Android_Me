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

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;


// Kelas adaptor khusus yang menampilkan daftar gambar Android-Me di GridView
public class MasterListAdapter extends BaseAdapter {

    // Melacak konteks dan daftar gambar yang akan ditampilkan
    private Context mContext;
    private List<Integer> mImageIds;

    /**
     * Constructor method
     * @param imageIds The list of images to display
     */
    MasterListAdapter(Context context, List<Integer> imageIds) {
        mContext = context;
        mImageIds = imageIds;
    }

    /**
     * Returns the number of items the adapter will display
     */
    @Override
    public int getCount() {
        return mImageIds.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    /**
     * Creates a new ImageView for each item referenced by the adapter
     */
    public View getView(final int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // Jika tampilan tidak didaur ulang, ini akan membuat ImageView baru untuk menahan gambar
            imageView = new ImageView(mContext);
            // Tentukan parameter tata letak
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        // Atur sumber gambar dan kembalikan ImageView yang baru dibuat
        imageView.setImageResource(mImageIds.get(position));
        return imageView;
    }

}
