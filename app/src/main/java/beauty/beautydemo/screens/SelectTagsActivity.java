package beauty.beautydemo.screens;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import beauty.beautydemo.R;
import beauty.beautydemo.adapter.TagAdapter;
import beauty.beautydemo.base.BeautyBaseActivity;
import beauty.beautydemo.custview.TagTextView;
import beauty.beautydemo.entity.Tag;
import beauty.beautydemo.tools.Constants;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by LJW on 15/7/14.
 */
public class SelectTagsActivity extends BeautyBaseActivity implements TagAdapter.onItemOnClick {

    @InjectView(R.id.rv_tagslist)
    RecyclerView mList;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.ll_tags)
    LinearLayout mllTags;

    private SearchView searchView;
    private TagAdapter adapter;

    private ArrayList<String> tagList = new ArrayList<>(4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_tags);
        ButterKnife.inject(this);

        setupToolBar();
        setupRecycleView();
    }

    private void setupRecycleView() {

        LinearLayoutManager manager = new LinearLayoutManager(this);
        mList.setLayoutManager(manager);
        mList.setHasFixedSize(true);

        ArrayList<Tag> list = Constants.getTagList();
        adapter = new TagAdapter(SelectTagsActivity.this, list);
        adapter.setOnItemOnClickLisener(this);
        adapter.setDelay(getResources().getInteger(R.integer.activity_change_duration));
        mList.setAdapter(adapter);
        mList.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mList.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    adapter.setAnimationsLocked(true);
                }
            }
        });
    }

    private void setupToolBar() {
        toolbar.setTitle("选择标签");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_done_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(BeautyBaseActivity.FLAG_SELECT_TAGS, tagList);
                setResult(0, intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tag_search, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        ComponentName componentName = getComponentName();

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(componentName));
        searchView.setQueryHint(getString(R.string.search_tag));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return true;
            }
        });

        return true;
    }


    /**
     * 增加tag布局
     *
     * @param tag
     * @param context
     * @return
     */
    private TagTextView getTagsView(String tag, Context context) {

        TagTextView textView = new TagTextView(context, tag);
        return textView;

    }


    @Override
    public void onItemOnClick(String name) {
        if (tagList.size() > 3) {
            Toast.makeText(SelectTagsActivity.this, "最多只能选择4个tag", Toast.LENGTH_SHORT).show();
            return;
        }
        tagList.add(name);
        mllTags.addView(getTagsView(name, SelectTagsActivity.this));
    }
}
