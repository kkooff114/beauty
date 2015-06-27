package beauty.beautydemo.screens;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import beauty.beautydemo.R;
import beauty.beautydemo.adapter.ViewPagerAdapter;
import beauty.beautydemo.custview.materialTab.MaterialTab;
import beauty.beautydemo.custview.materialTab.MaterialTabHost;
import beauty.beautydemo.custview.materialTab.MaterialTabListener;
import beauty.beautydemo.fragment.TabLibCommentFragment;
import beauty.beautydemo.fragment.TabLibContentFragment;
import beauty.beautydemo.fragment.TabLibShopFragment;

/**
 * Created by LJW on 15/6/1.
 */
public class TestLibProductDetailMaterialTab2Activity extends FragmentActivity implements MaterialTabListener {

    MaterialTabHost tabHost;
    ViewPager pager;
    ViewPagerAdapter adapter;

    private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>(3);
    private String[] titleTexts = {"小结", "实验室", "比价"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_lib_product_detail_material_tab);

        initView();
        initData();
    }

    private void initView() {
        pager = (ViewPager) findViewById(R.id.pager);
        tabHost = (MaterialTabHost) this.findViewById(R.id.tabHost);

        fragmentList.add(TabLibContentFragment.newInstance(titleTexts[0]));
        fragmentList.add(TabLibCommentFragment.newInstance(titleTexts[1]));
        fragmentList.add(TabLibShopFragment.newInstance(titleTexts[2]));
    }

    private void initData() {


        adapter = new ViewPagerAdapter(getSupportFragmentManager(), titleTexts, fragmentList);
        pager.setAdapter(adapter);
        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // when user do a swipe the selected tab change

                tabHost.setSelectedNavigationItem(position);

            }
        });

        // insert all tabs from pagerAdapter data
        for (int i = 0; i < adapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab()
                            .setText(adapter.getPageTitle(i))
                            .setTabListener(this)
            );

        }

    }



    @Override
    public void onTabSelected(MaterialTab tab) {
        pager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }
}
