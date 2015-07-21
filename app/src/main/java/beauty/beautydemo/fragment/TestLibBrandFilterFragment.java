package beauty.beautydemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import beauty.beautydemo.R;
import beauty.beautydemo.base.BeautyBaseFragment;
import beauty.beautydemo.custview.expandtabview.SubView2Level;
import butterknife.ButterKnife;

/**
 * Created by LJW on 15/7/19.
 */
public class TestLibBrandFilterFragment extends BeautyBaseFragment {

    private String mTitle = "";
    private SubView2Level.OnSelectListener listener;

    public static TestLibBrandFilterFragment newInstance(String title, SubView2Level.OnSelectListener listener) {

        TestLibBrandFilterFragment instance = new TestLibBrandFilterFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        instance.listener = listener;
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
        View view = inflater.inflate(R.layout.fragment_test_lib_filter_brand, container, false);
        ((SubView2Level) view).setOnSelectListener(listener);
        ButterKnife.inject(this, view);


        return view;
    }
}
