package beauty.beautydemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.ksoichiro.android.observablescrollview.ScrollState;

import java.util.ArrayList;

import beauty.beautydemo.R;
import beauty.beautydemo.adapter.SubscribeAdapter;
import beauty.beautydemo.base.BeautyBaseFragment;
import beauty.beautydemo.custview.reveal.RevealBackgroundView;
import beauty.beautydemo.entity.SubscribeListItem;
import beauty.beautydemo.tools.Constants;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by LJW on 15/7/7.
 */
public class SubscribeListFragment extends BeautyBaseFragment {


    public static final String TITLE = "t";
    private String mTitle = "";

    SubscribeAdapter adapter;

    @InjectView(R.id.rv_subscribe_list)
    RecyclerView mList;


    public static SubscribeListFragment newInstance(String title) {

        SubscribeListFragment instance = new SubscribeListFragment();
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
        View view = inflater.inflate(R.layout.fragment_subscribe_list, container, false);
        ButterKnife.inject(this, view);

        vRevealBackground = (RevealBackgroundView) view.findViewById(R.id.vRevealBackground);
        setupRevealBackground(savedInstanceState);

        return view;
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mList.setLayoutManager(linearLayoutManager);
        mList.setHasFixedSize(true);

        ArrayList<SubscribeListItem> list = Constants.getSubList();
        adapter = new SubscribeAdapter(getActivity(), list);
        mList.setAdapter(adapter);
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

    @Override
    public void onStateChange(int state) {

        if (RevealBackgroundView.STATE_FINISHED == state) {
            initList();
        }
        super.onStateChange(state);

    }
}
