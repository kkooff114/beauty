package beauty.beautydemo.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;

import beauty.beautydemo.R;
import beauty.beautydemo.adapter.ViewPagerAdapter;
import beauty.beautydemo.base.BeautyBaseFragment;
import beauty.beautydemo.custview.reveal.RevealBackgroundView;
import beauty.beautydemo.screens.TakePhotoActivity;
import beauty.beautydemo.screens.materialmenu.SimpleHeaderDrawerActivity;
import de.hdodenhof.circleimageview.CircleImageView;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;


/**
 * Created by LJW on 15/6/22.
 */
public class PropertyMaterialFragment extends BeautyBaseFragment implements MaterialTabListener, View.OnClickListener {

    public static final String TITLE = "t";
    private String mTitle = "";
    private MaterialTabHost mTabHost;
    private ViewPagerAdapter adapter;
    private ViewPager mPager;

    private CircleImageView mHeadPropertyImage;
    private TextView mHeadPropertyName;
    private LinearLayout mHeadFollowers, mHeadPhoto, mHeadFollowing, mHeadStatus;

    private RelativeLayout mPropertyHead, mPropertyTabhost;//头模块, tabhost模块
    private FloatingActionsMenu mFloatingActionMenu; //圆圈菜单
    private FloatingActionButton action_photo; // 拍照

    private long profileHeaderAnimationStartTime = 0;

    private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>(4);
    private String[] titleTexts = {"", "", "", ""};

    public static PropertyMaterialFragment getInstance(String title) {

        PropertyMaterialFragment instance = new PropertyMaterialFragment();
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_property_material, container, false);


        mTabHost = (MaterialTabHost) view.findViewById(R.id.tabHost_profile);
        mPager = (ViewPager) view.findViewById(R.id.pager);
        action_photo = (FloatingActionButton) view.findViewById(R.id.action_photo);
        action_photo.setOnClickListener(this);

        vRevealBackground = (RevealBackgroundView) view.findViewById(R.id.vRevealBackground);
        setupRevealBackground(savedInstanceState);

        // 头像, 姓名
        mHeadPropertyImage = (CircleImageView) view.findViewById(R.id.profile_image);
        mHeadPropertyName = (TextView) view.findViewById(R.id.tv_profile_name);

        // 个人照片数量, 粉丝, 关注
        mPropertyHead = (RelativeLayout) view.findViewById(R.id.rv_property_head);
        mPropertyTabhost = (RelativeLayout) view.findViewById(R.id.rv_property_tabhost);
        mFloatingActionMenu = (FloatingActionsMenu) view.findViewById(R.id.multiple_actions);
        mHeadFollowers = (LinearLayout) view.findViewById(R.id.ll_head_followers);
        mHeadPhoto = (LinearLayout) view.findViewById(R.id.ll_head_photo);
        mHeadFollowing = (LinearLayout) view.findViewById(R.id.ll_head_following);
        mHeadStatus = (LinearLayout) view.findViewById(R.id.ll_head_status);

        //TODO 在fragmentList中添加Fragment
        fragmentList.add(PropertyMaterialTab1.newInstance("", ""));
        fragmentList.add(PropertyMaterialTab1.newInstance("", ""));
        fragmentList.add(PropertyMaterialTab1.newInstance("", ""));
        fragmentList.add(PropertyMaterialTab1.newInstance("", ""));


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
                            .setIcon(getIcon(i))
                            .setTabListener(this)
            );

        }


        return view;
    }


    private Drawable getIcon(int position) {
        switch (position) {
            case 0:
                return getActivity().getResources().getDrawable(R.drawable.ic_style_white_18dp);
            case 1:
                return getActivity().getResources().getDrawable(R.drawable.ic_grid_on_white_18dp);
            case 2:
                return getActivity().getResources().getDrawable(R.drawable.ic_explore_white_18dp);
            case 3:
                return getActivity().getResources().getDrawable(R.drawable.ic_settings_white_18dp);
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
            mPropertyHead.setVisibility(View.VISIBLE);
            mPropertyTabhost.setVisibility(View.VISIBLE);

            animateUserProfileHeader();

            mPropertyHead.animate()
                    .translationY(0)
                    .setInterpolator(new OvershootInterpolator(1.f))
                    .setStartDelay(300)
                    .setDuration(ANIM_DURATION_FAB)
                    .start();
        } else {
            mPropertyHead.setVisibility(View.INVISIBLE);
            mPropertyTabhost.setVisibility(View.INVISIBLE);

            mFloatingActionMenu.setTranslationY(2 * getResources().getDimensionPixelOffset(R.dimen.btn_fab_size));
        }

        super.onStateChange(state);

    }

    private void animateUserProfileHeader() {
        if (!lockedAnimations) {
            profileHeaderAnimationStartTime = System.currentTimeMillis();

//            mPropertyHead.setTranslationY(-mPropertyHead.getHeight());
            mHeadPropertyImage.setTranslationY(-mHeadPropertyImage.getHeight());
            mHeadPropertyName.setTranslationY(-mHeadPropertyName.getHeight());
            mPropertyTabhost.setTranslationY(-mPropertyTabhost.getHeight());
            mHeadStatus.setAlpha(0);

//            mPropertyHead.animate().translationY(0).setDuration(300).setInterpolator(INTERPOLATOR);
            mHeadPropertyImage.animate().translationY(0).setDuration(300).setStartDelay(100).setInterpolator(INTERPOLATOR);
            mHeadPropertyName.animate().translationY(0).setDuration(300).setStartDelay(200).setInterpolator(INTERPOLATOR);
            mPropertyTabhost.animate().translationY(0).setDuration(300).setStartDelay(500).setInterpolator(INTERPOLATOR);
            mHeadStatus.animate().alpha(1).setDuration(200).setStartDelay(400).setInterpolator(INTERPOLATOR);
            mFloatingActionMenu.animate().translationY(0).setDuration(300).setStartDelay(800).setInterpolator(OVERSHOOT);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.action_photo: //拍照

                ((SimpleHeaderDrawerActivity)getActivity()).startActivityRelLocation(v, getActivity(), TakePhotoActivity.class);

                break;
        }
    }
}
