package beauty.beautydemo.screens;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import beauty.beautydemo.R;
import beauty.beautydemo.adapter.FirstSelectTagAdapter;
import beauty.beautydemo.adapter.MyTagAdapter;
import beauty.beautydemo.base.BeautyBaseActivity;
import beauty.beautydemo.custview.intamaterial.FeedContextMenuManager;
import beauty.beautydemo.custview.recyclerviewitemdecoration.DividerGridItemDecoration;
import beauty.beautydemo.entity.Tag;
import beauty.beautydemo.tools.Constants;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by LJW on 15/7/17.
 */
public class FirstSelectTagActivity extends BeautyBaseActivity {


    @InjectView(R.id.rv_mytag)
    RecyclerView mList;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    private MenuItem doneMenuItem;

    private FirstSelectTagAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_tag);
        ButterKnife.inject(this);

        setupToobar();

        setupRecycleView();
    }

    private void setupToobar() {
        toolbar.setTitle("选择您的偏好");
        setSupportActionBar(toolbar);

        toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
    }


    private void setupRecycleView() {
        LinearLayoutManager manager = new GridLayoutManager(this, 3);

        mList.setLayoutManager(manager);
        mList.setHasFixedSize(true);
        mList.addItemDecoration(new DividerGridItemDecoration(this));

        ArrayList<Tag> li = Constants.getTagList();
        adapter = new FirstSelectTagAdapter(this, li);
        mList.setAdapter(adapter);
        mList.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                FeedContextMenuManager.getInstance().onScrolled(recyclerView, dx, dy);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_first_select_tag, menu);
        doneMenuItem = menu.findItem(R.id.action_inbox);

        return true;

    }

    private Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_done:
                    Intent intent = new Intent(FirstSelectTagActivity.this, NewLookActivity.class);
                    startActivity(intent);
            }

            return true;
        }
    };
}
