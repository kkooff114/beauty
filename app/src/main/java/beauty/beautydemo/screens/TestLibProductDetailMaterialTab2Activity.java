package beauty.beautydemo.screens;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import java.util.ArrayList;

import beauty.beautydemo.R;
import beauty.beautydemo.adapter.ViewPagerAdapter;
import beauty.beautydemo.base.BeautyBaseActivity;
import beauty.beautydemo.fragment.TabLibContentFragment;
import beauty.beautydemo.fragment.TabLibShopFragment;
import beauty.beautydemo.fragment.TabLibTestFragment;
import butterknife.ButterKnife;
import butterknife.InjectView;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

/**
 * Created by LJW on 15/6/1.
 */
public class TestLibProductDetailMaterialTab2Activity extends BeautyBaseActivity implements MaterialTabListener {

    MaterialTabHost tabHost;
    ViewPager pager;
    ViewPagerAdapter adapter;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>(3);
    private String[] titleTexts = {"小结", "实验室", "比价"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_lib_product_detail_material_tab);
        ButterKnife.inject(this);

        setupToolbar();
        initView();
        initData();
    }

    private void setupToolbar() {
        toolbar.setTitle("产品详情");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_keyboard_arrow_left_white_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitSuper();
            }
        });

        toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_product_detail, menu);

        return true;
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_share:
                    Toast.makeText(TestLibProductDetailMaterialTab2Activity.this, "分享本产品", Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        }
    };

    private void initView() {
        pager = (ViewPager) findViewById(R.id.pager);
        tabHost = (MaterialTabHost) this.findViewById(R.id.tabHost);

        fragmentList.add(TabLibContentFragment.newInstance(titleTexts[0]));
        fragmentList.add(TabLibTestFragment.newInstance(titleTexts[1]));
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
