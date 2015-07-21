package beauty.beautydemo.screens;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import beauty.beautydemo.R;
import beauty.beautydemo.adapter.MyIconAdapter;
import beauty.beautydemo.base.BeautyBaseActivity;
import beauty.beautydemo.tools.Constants;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by LJW on 15/7/19.
 */
public class MyIconActivity extends BeautyBaseActivity {

    private String title = "";

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.tv_name_1)
    TextView mName1;
    @InjectView(R.id.tv_name_2)
    TextView mName2;

    @InjectView(R.id.rv_1)
    RecyclerView mList1;

    @InjectView(R.id.rv_2)
    RecyclerView mList2;
    private String upList1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_icon);
        ButterKnife.inject(this);

        title = getIntent().getStringExtra(TITLE);

        setupToolbar();

        if (title.equals("我的Icon") || title.equals("我的风格")) {
            setupList1(title);
            setupList2(title);
        } else if (title.equals("我的裸妆")) {
            setupList11(title);
        } else {
            mList1.setVisibility(View.GONE);
            mList2.setVisibility(View.GONE);
        }

    }

    private void setupToolbar() {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_keyboard_arrow_left_white_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitSuper();
            }
        });
    }

    public void setupList1(String upList1) {
        mName1.setText(upList1 + "1");
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mList1.setLayoutManager(manager);
        mList1.setHasFixedSize(true);

        mList1.setAdapter(new MyIconAdapter(this, Constants.getMyIcon()));
    }

    public void setupList2(String upList1) {
        mName2.setText(upList1 + "2");
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mList2.setLayoutManager(manager);
        mList2.setHasFixedSize(true);

        mList2.setAdapter(new MyIconAdapter(this, Constants.getMyIcon()));
    }

    public void setupList11(String upList11) {
        mName1.setText(upList11 + "1");
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mList1.setLayoutManager(manager);
        mList1.setHasFixedSize(true);

        mList1.setAdapter(new MyIconAdapter(this, Constants.getMyLuozhuang()));
    }
}
