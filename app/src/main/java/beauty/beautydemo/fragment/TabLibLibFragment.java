package beauty.beautydemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import beauty.beautydemo.R;

/**
 * Created by LJW on 15/6/6.
 */
public class TabLibLibFragment extends Fragment {

    public static final String TITLE = "title";
    private String mTitle = "Defaut Value";


    public static TabLibLibFragment newInstance(String title) {
        TabLibLibFragment tabFragment = new TabLibLibFragment();
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
        View view = inflater.inflate(R.layout.fragment_tab_lib_lib, container, false);


        return view;

    }


}
