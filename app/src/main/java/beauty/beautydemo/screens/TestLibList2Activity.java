package beauty.beautydemo.screens;

import android.animation.Animator;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import beauty.beautydemo.R;
import beauty.beautydemo.adapter.TestListAdapter;
import beauty.beautydemo.adapter.TextAdapter;
import beauty.beautydemo.base.BeautyBaseActivity;
import beauty.beautydemo.custview.expandtabview.SubView2Level;
import beauty.beautydemo.fragment.TestLibListFilterFragment;
import beauty.beautydemo.tools.Constants;
import beauty.beautydemo.tools.ScreenTools;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by LJW on 15/7/18.
 */
public class TestLibList2Activity extends BeautyBaseActivity implements SubView2Level.OnSelectListener, TextAdapter.OnTextSelectLinsner {


    @InjectView(R.id.rv_list)
    RecyclerView mList;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.content)
    RelativeLayout mFilterContent;


    private MenuItem searchMenuItem, filterMenuItem;
    private SearchView searchView;
    private TestListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_lib_list);
        ButterKnife.inject(this);

        setupToolbar();
        setupList();
        setupFilterContent();
    }

    private void setupFilterContent() {
        getSupportFragmentManager().beginTransaction().replace(R.id.content, TestLibListFilterFragment.newInstance("筛选", this, this)).commit();
    }

    private void setupToolbar() {
        toolbar.setTitle("口红");
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

    private void setupList() {

        LinearLayoutManager manager = new LinearLayoutManager(this);

        mList.setLayoutManager(manager);
        mList.setHasFixedSize(true);

        adapter = new TestListAdapter(TestLibList2Activity.this, Constants.getTestLibList());

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mList.setAdapter(adapter);
            }
        }, 300);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu_test_list, menu);
        searchMenuItem = menu.findItem(R.id.action_search);
        filterMenuItem = menu.findItem(R.id.action_filter);

        searchView = (SearchView) MenuItemCompat.getActionView(searchMenuItem);
        ComponentName componentName = getComponentName();
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(componentName));
        searchView.setQueryHint("搜索产品");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return true;
            }
        });

        return true;
    }


    boolean isFilterOpen = false;

    private Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_filter:
                    if (isFilterOpen) {
                        dismissFilterContent();
                    } else {
                        showFilterContent();
                    }
                    break;
            }

            return false;
        }
    };


    private void showFilterContent() {

        mFilterContent.animate().translationY(-ScreenTools.dpToPx(100)).setDuration(300).setInterpolator(OVERSHOOT).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                mFilterContent.setVisibility(View.VISIBLE);

                int y = -mFilterContent.getHeight();

                if (y == 0) {
                    y = -ScreenTools.getScreenHeight(TestLibList2Activity.this);
                }
                mFilterContent.setTranslationY(y);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isFilterOpen = true;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).start();

    }

    private void dismissFilterContent() {

        mFilterContent.animate().translationY(-mFilterContent.getHeight()).setInterpolator(Anticipate).setDuration(300).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mFilterContent.setVisibility(View.GONE);
                isFilterOpen = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).start();

    }


    // 品牌选择后
    @Override
    public void getValue(String showText) {
        dismissFilterContent();
        Toast.makeText(TestLibList2Activity.this, "选择:" + showText, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTextSelectLinser(String selectString) {
        dismissFilterContent();
        Toast.makeText(TestLibList2Activity.this, "选择:" + selectString, Toast.LENGTH_SHORT).show();
    }
}
