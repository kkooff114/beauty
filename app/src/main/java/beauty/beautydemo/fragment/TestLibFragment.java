package beauty.beautydemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import beauty.beautydemo.R;
import beauty.beautydemo.base.BeautyBaseFragment;
import beauty.beautydemo.custview.reveal.RevealBackgroundView;
import beauty.beautydemo.screens.TestLibList2Activity;
import beauty.beautydemo.screens.TestLibListActivity;

/**
 * Created by LJW on 15/3/17.
 */
public class TestLibFragment extends BeautyBaseFragment implements View.OnClickListener {

    private static final String TEXTLIB = "测试库";
    public static final String TITLE = "t";
    private String mTitle = "";

    private LinearLayout ll_test_lib_head,ll_testlib_bottom;

    private LinearLayout ll_lipstick, ll_makeup;

    public static TestLibFragment newInstance(String title) {

        TestLibFragment instance = new TestLibFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_testlib, container, false);

        vRevealBackground = (RevealBackgroundView) view.findViewById(R.id.vRevealBackground);
        setupRevealBackground(savedInstanceState);

        ll_test_lib_head = (LinearLayout) view.findViewById(R.id.ll_test_lib_head);
        ll_lipstick = (LinearLayout) view.findViewById(R.id.ll_lipstick);
        ll_makeup = (LinearLayout) view.findViewById(R.id.ll_makeup);
        ll_testlib_bottom = (LinearLayout) view.findViewById(R.id.ll_testlib_bottom);
        ll_lipstick.setOnClickListener(this);
        ll_makeup.setOnClickListener(this);

        return view;

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_lipstick:

                Intent intent = new Intent(getActivity(), TestLibList2Activity.class);
                startActivity(intent);
                break;

            case R.id.ll_makeup:

                break;
        }
    }


    @Override
    public void onStateChange(int state) {
        if (RevealBackgroundView.STATE_FINISHED == state) {

            ll_test_lib_head.setVisibility(View.VISIBLE);
            ll_lipstick.setVisibility(View.VISIBLE);
            ll_makeup.setVisibility(View.VISIBLE);
            ll_testlib_bottom.setVisibility(View.VISIBLE);

            animateBlock();

        } else {

            ll_test_lib_head.setVisibility(View.INVISIBLE);
            ll_lipstick.setVisibility(View.INVISIBLE);
            ll_makeup.setVisibility(View.INVISIBLE);
            ll_testlib_bottom.setVisibility(View.INVISIBLE);
        }

        super.onStateChange(state);
    }

    private void animateBlock() {
        ll_test_lib_head.setTranslationY(-ll_test_lib_head.getHeight());
        ll_lipstick.setTranslationX(-ll_lipstick.getWidth());
        ll_makeup.setTranslationX(ll_makeup.getWidth());
        ll_testlib_bottom.setTranslationY(ll_testlib_bottom.getHeight());

        ll_test_lib_head.animate().translationY(0).setDuration(300).setStartDelay(100).setInterpolator(INTERPOLATOR);
        ll_lipstick.animate().translationX(0).setDuration(300).setStartDelay(200).setInterpolator(INTERPOLATOR);
        ll_makeup.animate().translationX(0).setDuration(300).setStartDelay(300).setInterpolator(INTERPOLATOR);
        ll_testlib_bottom.animate().translationY(0).setDuration(300).setStartDelay(400).setInterpolator(INTERPOLATOR);
    }
}
