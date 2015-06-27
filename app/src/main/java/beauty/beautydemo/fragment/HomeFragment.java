package beauty.beautydemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import beauty.beautydemo.R;
import beauty.beautydemo.base.BeautyBaseFragment;

/**
 * Created by LJW on 15/6/27.
 */
public class HomeFragment extends BeautyBaseFragment {


    public static final String TITLE = "t";
    private String mTitle = "";

    public static HomeFragment newInstance(String title) {

        HomeFragment instance = new HomeFragment();
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

        View view = inflater.inflate(R.layout.fragment_home, container, false);


        return view;
    }


    @Override
    public void onStateChange(int state) {
        super.onStateChange(state);
    }
}
