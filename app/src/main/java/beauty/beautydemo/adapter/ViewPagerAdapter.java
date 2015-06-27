package beauty.beautydemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by LJW on 15/6/22.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> fragmentList;
    private String[] titleTexts;

    public ViewPagerAdapter(FragmentManager fm, String[] titleTexts, ArrayList<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.titleTexts = titleTexts;
    }

    public Fragment getItem(int num) {
        return fragmentList.get(num);
    }

    @Override
    public int getCount() {

        return fragmentList.size();

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleTexts[position];
    }


}


