package beauty.beautydemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;

/**
 * Created by LJW on 15/6/22.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private final String TAG = "FragmentStatePagerAd";

    private ArrayList<Fragment> fragmentList;
    private String[] titleTexts;

    public ViewPagerAdapter(FragmentManager fm, String[] titleTexts, ArrayList<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.titleTexts = titleTexts;
    }

    public Fragment getItem(int num) {
        Logger.i(titleTexts[0]);
        return fragmentList.get(num);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Logger.i(titleTexts[0]);
        Log.v(TAG, "Removing item #" + position + ": f=" + object
                + " v=" + ((Fragment) object).getView());
        super.destroyItem(container, position, object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Logger.i(titleTexts[0]);
        return super.instantiateItem(container, position);
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


