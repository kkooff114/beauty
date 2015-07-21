package beauty.beautydemo.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;

import beauty.beautydemo.R;
import beauty.beautydemo.custview.reveal.RevealBackgroundView;
import beauty.beautydemo.screens.materialmenu.SimpleHeaderDrawerActivity;
import butterknife.ButterKnife;

/**
 * Created by LJW on 15/6/26.
 */
public class BeautyBaseFragment extends Fragment implements RevealBackgroundView.OnStateChangeListener {

    public static final String TITLE = "title";
    public static final String TITLEStrings = "titlestrings";
    public static final String ARG_REVEAL_START_LOCATION = "reveal_start_location";
    public static final String ACTION_SHOW_LOADING_ITEM = "action_show_loading_item";

    public static final int ANIM_DURATION_FAB = 400;

    public static final Interpolator INTERPOLATOR = new DecelerateInterpolator();
    public static final Interpolator OVERSHOOT = new OvershootInterpolator(1.f);

    public boolean lockedAnimations = false;

    public RevealBackgroundView vRevealBackground;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(getActivity());
    }

    public void setupRevealBackground(Bundle savedInstanceState) {
        vRevealBackground.setFillPaintColor(getActivity().getResources().getColor(R.color.bg_fragment));
        vRevealBackground.setOnStateChangeListener(this);
        if (savedInstanceState == null) {
            final int[] startingLocation = getArguments().getIntArray(ARG_REVEAL_START_LOCATION);
            if(startingLocation == null){
                return;
            }
            vRevealBackground.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    vRevealBackground.getViewTreeObserver().removeOnPreDrawListener(this);
                    vRevealBackground.startFromLocation(startingLocation);
                    return true;
                }
            });
        } else {
            vRevealBackground.setToFinishedFrame();
//            userPhotosAdapter.setLockedAnimations(true);
        }
    }


    @Override
    public void onStateChange(int state) {

        if (RevealBackgroundView.STATE_FINISHED == state && getActivity() instanceof SimpleHeaderDrawerActivity) {
            ((SimpleHeaderDrawerActivity) getActivity()).removePreFragement();
        }
    }
}
