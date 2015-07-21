package beauty.beautydemo.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import beauty.beautydemo.R;
import beauty.beautydemo.adapter.TextAdapter;
import beauty.beautydemo.base.BeautyBaseFragment;
import beauty.beautydemo.custview.recyclerviewitemdecoration.DividerGridItemDecoration;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by LJW on 15/7/19.
 */
public class TestLibColorFilterFragment extends BeautyBaseFragment {
    private String mTitle = "";


    @InjectView(R.id.rv)
    RecyclerView mList;

    private TextAdapter.OnTextSelectLinsner linsner;

    public static TestLibColorFilterFragment newInstance(String title, TextAdapter.OnTextSelectLinsner linsner) {

        TestLibColorFilterFragment instance = new TestLibColorFilterFragment();
        Bundle bundle = new Bundle();
        instance.linsner = linsner;
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_lib_filter_color, container, false);
        ButterKnife.inject(this, view);


        setupList();
        return view;
    }

    private void setupList() {
        LinearLayoutManager manager = new GridLayoutManager(getActivity(), 3);

        mList.setLayoutManager(manager);
        mList.setHasFixedSize(true);
        mList.addItemDecoration(new DividerGridItemDecoration(getActivity()));

        ArrayList<String> data = new ArrayList<String>();
        if (mTitle.equals("颜色")) {
            data.add("正红");
            data.add("橘红");
        } else {
            data.add("评分高");
            data.add("评分低");
        }
        TextAdapter adapter = new TextAdapter(getActivity(), data);
        adapter.setOnTextSelectLinsner(linsner);
        mList.setAdapter(adapter);
    }
}
