package beauty.beautydemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import beauty.beautydemo.R;
import beauty.beautydemo.adapter.MyIconAdapter;
import beauty.beautydemo.adapter.PropertyCardAdapter;
import beauty.beautydemo.base.BeautyBaseFragment;
import beauty.beautydemo.screens.MyIconActivity;
import beauty.beautydemo.tools.Constants;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 个人-icon
 * Created by LJW on 15/7/18.
 */
public class PropertyMaterialMyIcon extends BeautyBaseFragment {

    private String mTitle = "";

    private ArrayList<String> titleStrings = new ArrayList<String>();

    private LayoutInflater inflater;


    @InjectView(R.id.rv_list)
    RecyclerView mList;

    @InjectView(R.id.ll_root)
    LinearLayout mRoot;

    public static PropertyMaterialMyIcon newInstance(String title, ArrayList<String> titles) {

        PropertyMaterialMyIcon instance = new PropertyMaterialMyIcon();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        bundle.putStringArrayList(TITLEStrings, titles);
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(TITLE);
            titleStrings = getArguments().getStringArrayList(TITLEStrings);
        }
        inflater = LayoutInflater.from(getActivity());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_property_material_my_icon, container, false);
        ButterKnife.inject(this, view);

        setupList();

//        addCards(titleStrings);
        return view;
    }

    private void addCards(ArrayList<String> strings) {
        for (int i = 0; i < strings.size(); i++) {
            View view = inflater.inflate(R.layout.include_property_card, mRoot, false);
            ((TextView) view.findViewById(R.id.tv_name)).setText(strings.get(i)); // 名称
            ((TextView) view.findViewById(R.id.tv_count)).setText(i * 30); // 名称
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), MyIconActivity.class);
                    getActivity().startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void setupList() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
//        manager.setOrientation(LinearLayoutManager.HORIZONTAL);

        mList.setLayoutManager(manager);
        mList.setHasFixedSize(true);
//        mList.setAdapter(new MyIconAdapter(getActivity(), Constants.getMyIcon()));
        mList.setAdapter(new PropertyCardAdapter(getActivity(), titleStrings));
    }
}
