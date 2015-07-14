package beauty.beautydemo.screens;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import beauty.beautydemo.R;
import beauty.beautydemo.adapter.SubscribeAdapter;
import beauty.beautydemo.adapter.SubscribeContentAdapter;
import beauty.beautydemo.base.BeautyBaseActivity;
import beauty.beautydemo.entity.SubcribeContentListItem;
import beauty.beautydemo.tools.Constants;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by LJW on 15/7/8.
 */
public class SubscribeContentListActivity extends BeautyBaseActivity {

    @InjectView(R.id.rv_sub_content)
    RecyclerView mList;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

    private String type = "韩妆";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe_content_list);
        ButterKnife.inject(this);

        type = getIntent().getStringExtra(SubscribeAdapter.TYPE);
        setupToolBar();
        setupList();
    }

    private void setupToolBar() {
        mToolbar.setTitle(type);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupList() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mList.setLayoutManager(manager);
        mList.setHasFixedSize(true);

        ArrayList<SubcribeContentListItem> list = Constants.getSubContentList(type);
        mList.setAdapter(new SubscribeContentAdapter(this, list));

    }

}
