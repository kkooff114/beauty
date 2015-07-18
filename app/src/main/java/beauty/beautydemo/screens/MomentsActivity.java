package beauty.beautydemo.screens;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;


import com.edmodo.cropper.cropwindow.handle.Handle;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.melnykov.fab.ScrollDirectionListener;

import java.util.logging.Logger;

import beauty.beautydemo.R;
import beauty.beautydemo.adapter.FeedAdapter;
import beauty.beautydemo.base.BeautyBaseActivity;
import beauty.beautydemo.custview.intamaterial.FeedContextMenu;
import beauty.beautydemo.custview.intamaterial.FeedContextMenuManager;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * Created by LJW on 15/7/17.
 */
public class MomentsActivity extends BeautyBaseActivity implements FeedAdapter.OnFeedItemClickListener,
        FeedContextMenu.OnFeedContextMenuItemClickListener {


    @InjectView(R.id.rcFeed)
    RecyclerView rcFeed;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.multiple_actions)
    FloatingActionsMenu floatingActionsMenu;

    private String title = "";

    private boolean pendingIntroAnimation;
    private FeedAdapter feedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        ButterKnife.inject(this);

        initView(savedInstanceState);
    }

    private void initView(Bundle savedInstanceState) {

        setupToolBar();
        setupFeed();
        if (savedInstanceState == null) {
            pendingIntroAnimation = true;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    feedAdapter.updateItems(true);
                }
            }, 300);
        } else {
            feedAdapter.updateItems(false);
        }
    }

    private void setupToolBar() {
        title = getIntent().getStringExtra(TOOL_BAR_TITLE);
        if (title != null) {
            toolbar.setTitle(title);
        }
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_keyboard_arrow_left_white_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void setupFeed() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 300;
            }
        };
        rcFeed.setLayoutManager(linearLayoutManager);

        feedAdapter = new FeedAdapter(this);
        feedAdapter.setOnFeedItemClickListener(this);
        rcFeed.setAdapter(feedAdapter);
        rcFeed.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                FeedContextMenuManager.getInstance().onScrolled(recyclerView, dx, dy);

                if (dy < 0) {
                    //下滑
                    fabShow();
                } else {
                    //上滑
                    fabHide();
                }
            }

        });

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                fabShow();
            }
        }, 1000);

    }

    boolean isAnimation = false;
    boolean isFabShow = true;

    private void fabShow() {
        if (isAnimation) {
            return;
        }
        floatingActionsMenu.animate().translationY(0).setDuration(300).setInterpolator(OVERSHOOT).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAnimation = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isFabShow = true;
                isAnimation = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                isAnimation = false;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).start();
    }

    private void fabHide() {
        if (isAnimation) {
            return;
        }
        floatingActionsMenu.animate().translationY(2 * getResources().getDimensionPixelOffset(R.dimen.btn_fab_size)).setDuration(300).setInterpolator(OVERSHOOT).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAnimation = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isFabShow = false;
                isAnimation = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                isAnimation = false;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).start();
    }


    // 拍照
    @OnClick(R.id.action_photo)
    void takePhotoClick(View v) {
        takePhoto(v);
    }

    //记录笔记
    @OnClick(R.id.action_text)
    void takeNoteClick(View v) {
        takeNote(v);
    }

    public void takeNote(View v) {
        Intent intent = new Intent(this, NoteMainActivity.class);
        startActivity(intent);
    }


    public void takePhoto(View v) {

        Intent intent = new Intent(this, MultiImageSelectorActivity.class);
        // 是否显示拍摄图片
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
        // 最大可选择图片数量
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 1);
        // 选择模式
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_SINGLE);

        //选择完启动的activity
        intent.putExtra(MultiImageSelectorActivity.EXTRA_START_ACTIVITY, PhotoFixCropActivity.class);

        startActivity(intent);
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

    @Override
    public void onCommentsClick(View v, int position) {
        final Intent intent = new Intent(this, CommentsActivity.class);
        int[] startingLocation = new int[2];
        v.getLocationOnScreen(startingLocation);
        intent.putExtra(CommentsActivity.ARG_DRAWING_START_LOCATION, startingLocation[1]);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    @Override
    public void onMoreClick(View v, int position) {
        FeedContextMenuManager.getInstance().toggleContextMenuFromView(v, position, this);
    }

    @Override
    public void onProfileClick(View v) {

    }
}
