package beauty.beautydemo.screens;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import beauty.beautydemo.R;
import beauty.beautydemo.base.BeautyBaseActivity;
import beauty.beautydemo.custview.sticknav.SimpleViewPagerIndicator;
import beauty.beautydemo.custview.sticknav.TabFragment;
import beauty.beautydemo.custview.sticknav.TabLibComment;
import beauty.beautydemo.custview.sticknav.TabLibContentFragment;
import beauty.beautydemo.custview.sticknav.TabLibShop;

/**
 * Created by LJW on 15/4/9.
 */
public class TestLibProductDetailActivity extends FragmentActivity {

    private String[] mTitles = new String[]{"试验展示", "用户评价", "店铺推荐"};
    private SimpleViewPagerIndicator mIndicator;
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private Fragment[] mFragments = new Fragment[mTitles.length];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_lib_product_detail);

        initViews();
        initDatas();
        initEvents();

    }

    private void initViews() {
        mIndicator = (SimpleViewPagerIndicator) findViewById(R.id.id_stickynavlayout_indicator);
        mViewPager = (ViewPager) findViewById(R.id.id_stickynavlayout_viewpager);
    }

    private void initDatas() {
        mIndicator.setTitles(mTitles);


        mFragments[0] = TabLibContentFragment.newInstance(mTitles[0]);
        mFragments[1] = TabLibComment.newInstance(mTitles[1]);
        mFragments[2] = TabLibShop.newInstance(mTitles[2]);


        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }

        };

        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
    }


    private void initEvents() {
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                mIndicator.scroll(position, positionOffset);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
