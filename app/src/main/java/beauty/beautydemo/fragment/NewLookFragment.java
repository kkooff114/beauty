package beauty.beautydemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import beauty.beautydemo.R;
import beauty.beautydemo.adapter.ImagePagerAdapter;
import beauty.beautydemo.base.BeautyBaseFragment;
import butterknife.InjectView;

/**
 * Created by LJW on 15/6/29.
 */
public class NewLookFragment extends BeautyBaseFragment {

    public static final String TITLE = "t";
    private String mTitle = "";

    @InjectView(R.id.vp_new_look)
    ViewPager mViewPager;

    @InjectView(R.id.iv_delect)
    ImageView mDelect;

    @InjectView(R.id.iv_favorite)
    ImageView mFavorite;

    public static NewLookFragment newInstance(String title) {

        NewLookFragment instance = new NewLookFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        instance.setArguments(bundle);
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_look, container, false);

        initViewPager();

        return view;
    }

    private void initViewPager() {
//        mViewPager.setAdapter(new ImagePagerAdapter(getActivity()));
    }



}
