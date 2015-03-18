package beauty.beautydemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import beauty.beautydemo.R;

/**
 * Created by LJW on 15/3/17.
 */
public class PropertyFragment extends Fragment {
    private TextView title;
    private TextView back;
    private static final String TEXTLIB = "我的档案";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu_property, container, false);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        title = (TextView) getActivity().findViewById(R.id.title);
        title.setText(TEXTLIB);
        back = (TextView) getActivity().findViewById(R.id.back);
        back.setVisibility(View.GONE);
    }
}
