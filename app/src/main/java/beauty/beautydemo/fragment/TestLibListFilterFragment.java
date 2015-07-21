package beauty.beautydemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import beauty.beautydemo.R;
import beauty.beautydemo.adapter.TextAdapter;
import beauty.beautydemo.adapter.ViewPagerAdapter;
import beauty.beautydemo.base.BeautyBaseFragment;
import beauty.beautydemo.custview.expandtabview.SubView2Level;
import butterknife.ButterKnife;
import butterknife.InjectView;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

/**
 * Created by LJW on 15/7/19.
 */
public class TestLibListFilterFragment extends BeautyBaseFragment implements MaterialTabListener {

    private String mTitle = "";

    @InjectView(R.id.tabHost_profile)
    MaterialTabHost mTabHost;
    @InjectView(R.id.pager)
    ViewPager mPager;

    private String[] titleTexts = {"品牌", "颜色", "综合评分"};
    private ViewPagerAdapter adapter;
    private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>(3);

    private TestLibBrandFilterFragment brandFilterFragment;
    private TestLibColorFilterFragment colorFilterFragment;
    private TestLibColorFilterFragment rateFilterFragment;

    private SubView2Level.OnSelectListener sub2levelonSelectListener;
    private TextAdapter.OnTextSelectLinsner onTextSelectLinsner;


    public static TestLibListFilterFragment newInstance(String title, SubView2Level.OnSelectListener sub2levelonSelectListener, TextAdapter.OnTextSelectLinsner onTextSelectLinsner) {

        TestLibListFilterFragment instance = new TestLibListFilterFragment();
        instance.sub2levelonSelectListener = sub2levelonSelectListener;
        instance.onTextSelectLinsner = onTextSelectLinsner;
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(TITLE);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_lib_filter, container, false);
        ButterKnife.inject(this, view);


        setupViewPaper();

        return view;
    }


    private void setupViewPaper() {
        brandFilterFragment = TestLibBrandFilterFragment.newInstance("品牌", sub2levelonSelectListener);
        colorFilterFragment = TestLibColorFilterFragment.newInstance("颜色", onTextSelectLinsner);
        rateFilterFragment = TestLibColorFilterFragment.newInstance("评分", onTextSelectLinsner);

        fragmentList.add(brandFilterFragment);
        fragmentList.add(colorFilterFragment);
        fragmentList.add(rateFilterFragment);

        adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), titleTexts, fragmentList);


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
                            .setText(titleTexts[i])
                            .setTabListener(this)
            );

        }
    }

    @Override
    public void onTabSelected(MaterialTab materialTab) {
        mPager.setCurrentItem(materialTab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }
}
