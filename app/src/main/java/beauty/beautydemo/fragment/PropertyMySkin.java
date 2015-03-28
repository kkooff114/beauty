package beauty.beautydemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import beauty.beautydemo.R;
import beauty.beautydemo.custview.CardGroup;
import beauty.beautydemo.custview.CardView;

/**
 * Created by LJW on 15/3/19.
 */
public class PropertyMySkin extends CardView {

    public PropertyMySkin(Context context) {
        super(context);

    }

    public PropertyMySkin(Context context, CardGroup group, int color) {
        super(context, group, color);

    }

    public PropertyMySkin(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int setContentViewLayoutId() {
        return R.layout.fragment_property_my_skin;
    }
}
