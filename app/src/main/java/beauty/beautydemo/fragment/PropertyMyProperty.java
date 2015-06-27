package beauty.beautydemo.fragment;

import android.content.Context;
import android.util.AttributeSet;

import beauty.beautydemo.R;
import beauty.beautydemo.custview.cardview.CardGroup;
import beauty.beautydemo.custview.cardview.CardView;

/**
 * Created by LJW on 15/4/7.
 */
public class PropertyMyProperty  extends CardView{

    public PropertyMyProperty(Context context) {
        super(context);

    }

    public PropertyMyProperty(Context context, CardGroup group, int color,String title) {
        super(context, group, color,title);

    }

    public PropertyMyProperty(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int setContentViewLayoutId() {
        return R.layout.fragment_property_my_property;
    }
}
