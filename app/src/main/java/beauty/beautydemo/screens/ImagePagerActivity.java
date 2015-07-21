/**
 * ****************************************************************************
 * Copyright 2011-2013 Sergey Tarasevich
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * *****************************************************************************
 */
package beauty.beautydemo.screens;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;

import beauty.beautydemo.R;
import beauty.beautydemo.base.BeautyBaseActivity;
import beauty.beautydemo.entity.MyIcon;
import beauty.beautydemo.tools.Options;

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public class ImagePagerActivity extends BeautyBaseActivity {

    private static final String STATE_POSITION = "STATE_POSITION";

    final private String picPath = Environment.getExternalStorageDirectory()
            + "/cestbonPhoto/";// 照片存储位置

    private ImageView imageViewSave;
    private ImagePagerAdapter imagePagerAdapter;
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;

    ViewPager pager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_image_pager);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        ArrayList<MyIcon> imageUrls = (ArrayList<MyIcon>) bundle.get("list");
        int pagerPosition = bundle.getInt("position", 0);

        if (savedInstanceState != null) {
            pagerPosition = savedInstanceState.getInt(STATE_POSITION);
        }

        options = Options.getListOptions();

        pager = (ViewPager) findViewById(R.id.pager);

        imagePagerAdapter = new ImagePagerAdapter(imageUrls);
        pager.setAdapter(imagePagerAdapter);
        pager.setCurrentItem(pagerPosition);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_POSITION, pager.getCurrentItem());
    }


    private class ImagePagerAdapter extends PagerAdapter {

        private ArrayList<MyIcon> images;
        private LayoutInflater inflater;

        ImagePagerAdapter(ArrayList<MyIcon> images) {
            this.images = images;
            inflater = getLayoutInflater();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            View imageLayout = inflater.inflate(R.layout.item_pager_image,
                    view, false);
            imageLayout.setTag(position);
            assert imageLayout != null;
            ImageView imageView = (ImageView) imageLayout
                    .findViewById(R.id.image);
            TextView tvDesc = (TextView) imageLayout.findViewById(R.id.tv_desc);
            tvDesc.setText(images.get(position).desc);

            final ProgressBar spinner = (ProgressBar) imageLayout
                    .findViewById(R.id.loading);
            imageLoader.displayImage(images.get(position).icon, imageView, options,
                    new SimpleImageLoadingListener() {
                        @Override
                        public void onLoadingStarted(String imageUri, View view) {
                            spinner.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onLoadingFailed(String imageUri, View view,
                                                    FailReason failReason) {
                            String message = null;
                            switch (failReason.getType()) {
                                case IO_ERROR:
                                    message = "读写错误";
                                    break;
                                case DECODING_ERROR:
                                    message = "图片不能被解析";
                                    break;
                                case NETWORK_DENIED:
                                    message = "下载错误";
                                    break;
                                case OUT_OF_MEMORY:
                                    message = "内存溢出";
                                    break;
                                case UNKNOWN:
                                    message = "未知错误";
                                    break;
                            }
                            Toast.makeText(ImagePagerActivity.this, message,
                                    Toast.LENGTH_SHORT).show();

                            spinner.setVisibility(View.GONE);
                        }

                        @Override
                        public void onLoadingComplete(String imageUri,
                                                      View view, Bitmap loadedImage) {
                            spinner.setVisibility(View.GONE);
                        }
                    });

            view.addView(imageLayout, 0);
            return imageLayout;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }
    }
}