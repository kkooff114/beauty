package beauty.beautydemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

import beauty.beautydemo.R;
import beauty.beautydemo.adapter.FeedAdapter;
import beauty.beautydemo.base.BeautyBaseFragment;
import beauty.beautydemo.custview.FabShowLisner;
import beauty.beautydemo.custview.intamaterial.FeedContextMenu;
import beauty.beautydemo.custview.intamaterial.FeedContextMenuManager;
import beauty.beautydemo.custview.reveal.RevealBackgroundView;
import beauty.beautydemo.screens.CommentsActivity;
import beauty.beautydemo.screens.NoteMainActivity;
import beauty.beautydemo.screens.TakePhotoActivity;
import beauty.beautydemo.screens.materialmenu.SimpleHeaderDrawerActivity;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by LJW on 15/7/15.
 */
public class MomentsFollowFragment extends BeautyBaseFragment implements View.OnClickListener,
        FeedAdapter.OnFeedItemClickListener,
        FeedContextMenu.OnFeedContextMenuItemClickListener {

    public static final String TITLE = "t";
    private String mTitle = "";
    private boolean pendingIntroAnimation;


    private FabShowLisner lisner;//list上下滑动监听

    @InjectView(R.id.rcFeed)
    RecyclerView rcFeed;


    private FeedAdapter feedAdapter;

    public static MomentsFollowFragment getInstance(String title, FabShowLisner lisner) {

        MomentsFollowFragment instance = new MomentsFollowFragment();
        instance.setLisner(lisner);
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
        View view = inflater.inflate(R.layout.fragment_moments_follow, container, false);
        ButterKnife.inject(this, view);

//        vRevealBackground = (RevealBackgroundView) view.findViewById(R.id.vRevealBackground);
//        setupRevealBackground(savedInstanceState);

        setupFeed();
        if (savedInstanceState == null) {
            pendingIntroAnimation = true;
            feedAdapter.updateItems(true);
        } else {
            feedAdapter.updateItems(false);
        }

        return view;
    }


    public void setupFeed() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()) {
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 300;
            }
        };
        rcFeed.setLayoutManager(linearLayoutManager);

        feedAdapter = new FeedAdapter(getActivity());
        feedAdapter.setOnFeedItemClickListener(this);
        rcFeed.setAdapter(feedAdapter);
        rcFeed.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                FeedContextMenuManager.getInstance().onScrolled(recyclerView, dx, dy);

                if (lisner != null) {
                    FeedContextMenuManager.getInstance().onScrolled(recyclerView, dx, dy);

                    if (dy < 0) {
                        //下滑
                        lisner.down();
                    } else {
                        //上滑
                        lisner.up();
                    }
                }
            }
        });

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                lisner.down();
            }
        }, 1000);

    }


    @Override
    public void onStateChange(int state) {
        if (RevealBackgroundView.STATE_FINISHED == state) {

//            animateUserProfileHeader();

//            if (isPublic) {
//                showFeedLoadingItemDelayed();
//            }

        } else {

//            mFloatingActionMenu.setTranslationY(2 * getResources().getDimensionPixelOffset(R.dimen.btn_fab_size));
        }

        super.onStateChange(state);
    }

    public void animateUserProfileHeader() {
//        mFloatingActionMenu.animate().translationY(0).setDuration(300).setInterpolator(OVERSHOOT);
        feedAdapter.updateItems(true);
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


    public void showFeedLoadingItemDelayed() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rcFeed.smoothScrollToPosition(0);
                feedAdapter.showLoadingView();
            }
        }, 500);
    }

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

    public void setLisner(FabShowLisner lisner) {
        this.lisner = lisner;
    }
}
