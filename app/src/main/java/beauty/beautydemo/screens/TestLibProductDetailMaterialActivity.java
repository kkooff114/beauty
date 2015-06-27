package beauty.beautydemo.screens;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.MaterialViewPager;

import beauty.beautydemo.R;
import beauty.beautydemo.custview.reveal.revealanimator.RevealMiddleware;
import beauty.beautydemo.custview.sticknav.TabLibComment;
import beauty.beautydemo.custview.sticknav.TabLibContentFragment;
import beauty.beautydemo.custview.sticknav.TabLibShop;
import beauty.beautydemo.fragment.RecyclerViewFragment;

/**
 * Created by LJW on 15/5/19.
 */
public class TestLibProductDetailMaterialActivity extends ActionBarActivity {

    private MaterialViewPager mViewPager;
    private Toolbar toolbar;
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mDrawerToggle;

    private String[] mTitles = new String[]{"试验展示", "用户评价", "店铺推荐"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_lib_product_detail_material);

        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);
        toolbar = mViewPager.getToolbar();
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, 0, 0);
        mDrawer.setDrawerListener(mDrawerToggle);


        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            int oldPosition = -1;

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return TabLibContentFragment.newInstance(mTitles[0]);
                    case 1:
                        return TabLibComment.newInstance(mTitles[1]);
                    case 2:
                        return TabLibShop.newInstance(mTitles[2]);
                    default:
                        return RecyclerViewFragment.newInstance();
                }
            }

            @Override
            public void setPrimaryItem(ViewGroup container, int position, Object object) {
                super.setPrimaryItem(container, position, object);

                //only if position changed
                if (position == oldPosition)
                    return;
                oldPosition = position;

                int color = 0;
                Drawable imageUrl = null;
                switch (position) {
                    case 0:
                        imageUrl = getResources().getDrawable(R.drawable.product1_a);
                        color = getResources().getColor(R.color.cyan);
                        break;
                    case 1:
                        imageUrl = getResources().getDrawable(R.drawable.product2_a);
                        color = getResources().getColor(R.color.green);
                        break;
                    case 2:
                        imageUrl = getResources().getDrawable(R.drawable.product3_a);
                        color = getResources().getColor(R.color.blue);
                        break;

                }

                final int fadeDuration = 400;
                mViewPager.setImageDrawable(imageUrl, fadeDuration);
                mViewPager.setColor(color, fadeDuration);

            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());


        RevealMiddleware middleware = RevealMiddleware.getInstance();

        middleware.prepareAnimation(TestLibProductDetailMaterialActivity.this);
        middleware.animate();
    }
}
