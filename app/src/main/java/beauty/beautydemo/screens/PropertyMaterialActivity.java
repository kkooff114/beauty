package beauty.beautydemo.screens;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewTreeObserver;

import java.util.ArrayList;

import beauty.beautydemo.R;
import beauty.beautydemo.adapter.ViewPagerAdapter;
import beauty.beautydemo.base.BeautyBaseActivity;
import beauty.beautydemo.custview.reveal.RevealBackgroundView;
import beauty.beautydemo.fragment.PropertyMaterialTab1;
import beauty.beautydemo.fragment.TabLibCommentFragment;
import beauty.beautydemo.fragment.TabLibShopFragment;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

/**
 * 个人属性页面
 * Created by LJW on 15/6/25.
 */
public class PropertyMaterialActivity extends BeautyBaseActivity implements MaterialTabListener,
        RevealBackgroundView.OnStateChangeListener {


    private MaterialTabHost mTabHost;
    private ViewPagerAdapter adapter;
    private ViewPager mPager;
    RevealBackgroundView vRevealBackground;

    private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>(4);
    private String[] titleTexts = {"", "", "", ""};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_property_material);

        initView(savedInstanceState);

        initData();
    }


    private void initView(Bundle savedInstanceState) {
        mTabHost = (MaterialTabHost) findViewById(R.id.tabHost_profile);
        mPager = (ViewPager) findViewById(R.id.pager);
        vRevealBackground = (RevealBackgroundView) findViewById(R.id.vRevealBackground);

        setupRevealBackground(savedInstanceState);
    }

    private void initData() {

        fragmentList.add(PropertyMaterialTab1.newInstance("1", "2"));
        fragmentList.add(TabLibCommentFragment.newInstance(titleTexts[1]));
        fragmentList.add(TabLibShopFragment.newInstance(titleTexts[2]));
        fragmentList.add(TabLibShopFragment.newInstance(titleTexts[3]));

        adapter = new ViewPagerAdapter(getSupportFragmentManager(), titleTexts, fragmentList);

        mPager.setAdapter(adapter);
        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // when user do a swipe the selected tab change

                mTabHost.setSelectedNavigationItem(position);

            }
        });

        // insert all tabs from pagerAdapter data
        for (int i = 0; i < adapter.getCount(); i++) {
            mTabHost.addTab(
                    mTabHost.newTab()
                            .setIcon(getIcon(i))
                            .setTabListener(this)
            );

        }

    }

    private void setupRevealBackground(Bundle savedInstanceState) {
        vRevealBackground.setOnStateChangeListener(this);
        if (savedInstanceState == null) {
            final int[] startingLocation = getIntent().getIntArrayExtra(ARG_REVEAL_START_LOCATION);
            vRevealBackground.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    vRevealBackground.getViewTreeObserver().removeOnPreDrawListener(this);
                    vRevealBackground.startFromLocation(startingLocation);
                    return true;
                }
            });
        } else {
            vRevealBackground.setToFinishedFrame();
//            userPhotosAdapter.setLockedAnimations(true);
        }
    }


    private Drawable getIcon(int position) {
        switch (position) {
            case 0:
                return getResources().getDrawable(R.drawable.ic_style_white_18dp);
            case 1:
                return getResources().getDrawable(R.drawable.ic_grid_on_white_18dp);
            case 2:
                return getResources().getDrawable(R.drawable.ic_explore_white_18dp);
            case 3:
                return getResources().getDrawable(R.drawable.ic_settings_white_18dp);
        }
        return null;
    }

    @Override
    public void onTabSelected(MaterialTab tab) {
        mPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }


    @Override
    public void onStateChange(int state) {

        if (RevealBackgroundView.STATE_FINISHED == state) {
//            rvUserProfile.setVisibility(View.VISIBLE);
//            userPhotosAdapter = new UserProfileAdapter(this);
//            rvUserProfile.setAdapter(userPhotosAdapter);
        } else {
//            rvUserProfile.setVisibility(View.INVISIBLE);
        }
    }
}
