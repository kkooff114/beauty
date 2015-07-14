package beauty.beautydemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;

import beauty.beautydemo.R;
import beauty.beautydemo.adapter.FeedAdapter;
import beauty.beautydemo.adapter.ViewPagerAdapter;
import beauty.beautydemo.base.BeautyBaseFragment;
import beauty.beautydemo.custview.intamaterial.FeedContextMenu;
import beauty.beautydemo.custview.intamaterial.FeedContextMenuManager;
import beauty.beautydemo.custview.reveal.RevealBackgroundView;
import beauty.beautydemo.screens.CommentsActivity;
import beauty.beautydemo.screens.NoteMainActivity;
import beauty.beautydemo.screens.TakePhotoActivity;
import beauty.beautydemo.screens.materialmenu.SimpleHeaderDrawerActivity;
import butterknife.ButterKnife;
import butterknife.InjectView;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

/**
 * Created by LJW on 15/6/27.
 */
public class MomentsFragment extends BeautyBaseFragment implements View.OnClickListener,
        FeedAdapter.OnFeedItemClickListener,
        FeedContextMenu.OnFeedContextMenuItemClickListener, MaterialTabListener {

    @InjectView(R.id.tabHost_profile)
    MaterialTabHost mTabHost;
    @InjectView(R.id.pager)
    ViewPager mPager;

    public static final String TITLE = "t";
    private String mTitle = "";
    private String[] titleTexts = {"关注", "推荐", "我的标签"};

    private FloatingActionsMenu mFloatingActionMenu; //圆圈菜单
    private FloatingActionButton action_photo, action_text; // 拍照

//    private FeedAdapter feedAdapter;

    private boolean pendingIntroAnimation;

//    private RecyclerView rcFeed;

    public boolean isPublic = false;//是否发布

    private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>(3);
    private ViewPagerAdapter adapter;

    public static MomentsFragment getInstance(String title) {

        MomentsFragment instance = new MomentsFragment();
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
        View view = inflater.inflate(R.layout.fragment_moments, container, false);
        ButterKnife.inject(this, view);

        vRevealBackground = (RevealBackgroundView) view.findViewById(R.id.vRevealBackground);
        setupRevealBackground(savedInstanceState);

//        rcFeed = (RecyclerView) view.findViewById(R.id.rcFeed);
//        setupFeed();
        if (savedInstanceState == null) {
            pendingIntroAnimation = true;
        } else {
//            feedAdapter.updateItems(false);
        }

        setupViewPaper();

        action_photo = (FloatingActionButton) view.findViewById(R.id.action_photo);
        action_photo.setOnClickListener(this);
        action_text = (FloatingActionButton) view.findViewById(R.id.action_text);
        action_text.setOnClickListener(this);

        mFloatingActionMenu = (FloatingActionsMenu) view.findViewById(R.id.multiple_actions);

        return view;
    }

    private void setupViewPaper() {
        fragmentList.add(MomentsFollowFragment.getInstance("关注"));
        fragmentList.add(MomentsFollowFragment.getInstance("推荐"));
        fragmentList.add(MomentsFollowFragment.getInstance("我的标签"));
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


//    private void setupFeed() {
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()) {
//            @Override
//            protected int getExtraLayoutSpace(RecyclerView.State state) {
//                return 300;
//            }
//        };
//        rcFeed.setLayoutManager(linearLayoutManager);
//
//        feedAdapter = new FeedAdapter(getActivity());
//        feedAdapter.setOnFeedItemClickListener(this);
//        rcFeed.setAdapter(feedAdapter);
//        rcFeed.setOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                FeedContextMenuManager.getInstance().onScrolled(recyclerView, dx, dy);
//            }
//        });
//    }


    @Override
    public void onStateChange(int state) {
        if (RevealBackgroundView.STATE_FINISHED == state) {

            animateUserProfileHeader();

            if (isPublic) {
//                showFeedLoadingItemDelayed();
            }

        } else {

            mFloatingActionMenu.setTranslationY(2 * getResources().getDimensionPixelOffset(R.dimen.btn_fab_size));
        }

        super.onStateChange(state);
    }

    private void animateUserProfileHeader() {
        mFloatingActionMenu.animate().translationY(0).setDuration(300).setInterpolator(OVERSHOOT);
//        feedAdapter.updateItems(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.action_photo: //拍照

                ((SimpleHeaderDrawerActivity) getActivity()).startActivityRelLocation(v, getActivity(), TakePhotoActivity.class);

                break;

            case R.id.action_text:
                takeNote(v);
                break;
        }
    }

    @Override
    public void onCommentsClick(View v, int position) {
        final Intent intent = new Intent(getActivity(), CommentsActivity.class);
        int[] startingLocation = new int[2];
        v.getLocationOnScreen(startingLocation);
        intent.putExtra(CommentsActivity.ARG_DRAWING_START_LOCATION, startingLocation[1]);
        startActivity(intent);
        getActivity().overridePendingTransition(0, 0);
    }


//    public void showFeedLoadingItemDelayed() {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                rcFeed.smoothScrollToPosition(0);
//                feedAdapter.showLoadingView();
//            }
//        }, 500);
//    }

    @Override
    public void onMoreClick(View v, int position) {
        FeedContextMenuManager.getInstance().toggleContextMenuFromView(v, position, this);
    }

    @Override
    public void onProfileClick(View v) {

        ((SimpleHeaderDrawerActivity) getActivity()).switchContent(PropertyMaterialFragment.getInstance("个人"), v, "5");
    }

    @Override
    public void onReportClick(int feedItem) {

    }

    @Override
    public void onSharePhotoClick(int feedItem) {

    }

    @Override
    public void onCopyShareUrlClick(int feedItem) {

    }

    @Override
    public void onCancelClick(int feedItem) {

    }


    public void takeNote(View v) {
        Intent intent = new Intent(getActivity(), NoteMainActivity.class);
        startActivity(intent);
    }


    public void takePhoto(View v) {
        ((SimpleHeaderDrawerActivity) getActivity()).startActivityRelLocation(v, getActivity(), TakePhotoActivity.class);
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
