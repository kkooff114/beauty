package beauty.beautydemo.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import beauty.beautydemo.R;
import beauty.beautydemo.base.BeautyBaseFragment;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by LJW on 15/7/18.
 */
public class PropertyMateriaMyFace extends BeautyBaseFragment {

    private String mTitle = "";


    @InjectView(R.id.tv_desc)
    TextView mTvDesc;

    public static PropertyMateriaMyFace newInstance(String title) {

        PropertyMateriaMyFace instance = new PropertyMateriaMyFace();
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_property_material_my_face, container, false);
        ButterKnife.inject(this, view);

        mTvDesc.setText(mTitle);

        return view;
    }

}
