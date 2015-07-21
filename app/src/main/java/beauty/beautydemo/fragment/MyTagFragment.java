package beauty.beautydemo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import beauty.beautydemo.R;
import beauty.beautydemo.adapter.MyTagAdapter;
import beauty.beautydemo.base.BeautyBaseFragment;
import beauty.beautydemo.custview.FabShowLisner;
import beauty.beautydemo.custview.intamaterial.FeedContextMenuManager;
import beauty.beautydemo.custview.recyclerviewitemdecoration.DividerGridItemDecoration;
import beauty.beautydemo.entity.Tag;
import beauty.beautydemo.tools.Constants;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by LJW on 15/7/17.
 */
public class MyTagFragment extends BeautyBaseFragment {
    public static final String TITLE = "title";
    private String mTitle = "Defaut Value";

    @InjectView(R.id.rv_mytag)
    RecyclerView mList;

    private MyTagAdapter adapter;
    private FabShowLisner lisner;//list上下滑动监听


    public static MyTagFragment newInstance(String title, FabShowLisner lisner) {
        MyTagFragment tabFragment = new MyTagFragment();
        tabFragment.setLisner(lisner);
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_tag_fragment, container, false);
        ButterKnife.inject(this, view);

        setupRecycleView();

        return view;

    }

    private void setupRecycleView() {
        LinearLayoutManager manager = new GridLayoutManager(getActivity(), 3);

        mList.setLayoutManager(manager);
        mList.setHasFixedSize(true);
        mList.addItemDecoration(new DividerGridItemDecoration(getActivity()));

        ArrayList<Tag> li = Constants.getTagList();
        li.add(new Tag());
        adapter = new MyTagAdapter(getActivity(), li);
        mList.setAdapter(adapter);
        mList.setOnScrollListener(new RecyclerView.OnScrollListener() {
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

    public void setLisner(FabShowLisner lisner) {
        this.lisner = lisner;
    }
}
