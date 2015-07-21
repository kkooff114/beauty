package beauty.beautydemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import beauty.beautydemo.R;
import beauty.beautydemo.adapter.LibContentGridAdapter;
import beauty.beautydemo.base.BeautyBaseFragment;
import beauty.beautydemo.custview.sticknav.GridViewForScrollView;
import beauty.beautydemo.tools.Constants;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 测试库详情--实验室
 * Created by LJW on 15/7/18.
 */
public class TabLibTestFragment extends BeautyBaseFragment {
    private String mTitle = "Defaut Value";

    @InjectView(R.id.gv_lib_try)
    GridViewForScrollView tryAdapter;

    @InjectView((R.id.gv_lib_color_board))
    GridViewForScrollView colorAdapter; //色板

    @InjectView(R.id.gv_lib_one_two_board)
    GridViewForScrollView oneTwoAdapter; //涂一次两次

    @InjectView(R.id.gv_lib_show_color_board)
    GridViewForScrollView showColorAdapter; //显色度

    @InjectView(R.id.gv_lib_durable_board)
    GridViewForScrollView durableAdapter; //持久度

    @InjectView(R.id.gv_lib_moisten_board)
    GridViewForScrollView moistenAdapter; //滋润度


    public static TabLibTestFragment newInstance(String title) {
        TabLibTestFragment tabFragment = new TabLibTestFragment();
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
        View view = inflater.inflate(R.layout.fragment_tab_lib_test, container, false);
        ButterKnife.inject(this, view);

        setupList();
        return view;
    }

    private void setupList() {

        tryAdapter.setAdapter(new LibContentGridAdapter(getActivity(), Constants.getLipTryImageList()));
        colorAdapter.setAdapter(new LibContentGridAdapter(getActivity(), Constants.getLibColorBoard()));
        oneTwoAdapter.setAdapter(new LibContentGridAdapter(getActivity(), Constants.getLiboneTwoBoard()));
        showColorAdapter.setAdapter(new LibContentGridAdapter(getActivity(), Constants.getLibShowColorBoard()));
        durableAdapter.setAdapter(new LibContentGridAdapter(getActivity(), Constants.getLibDurableBoard()));
        moistenAdapter.setAdapter(new LibContentGridAdapter(getActivity(), Constants.getLibMoistenBoard()));
    }
}
