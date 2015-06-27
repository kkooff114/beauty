package beauty.beautydemo.fragment;

import android.content.Context;
import android.util.AttributeSet;

import beauty.beautydemo.R;
import beauty.beautydemo.custview.cardview.CardGroup;
import beauty.beautydemo.custview.cardview.CardView;

/**
 * Created by LJW on 15/4/7.
 */
public class PropertyAskFor  extends CardView {

    public PropertyAskFor(Context context) {
        super(context);

    }

    public PropertyAskFor(Context context, CardGroup group, int color,String title) {
        super(context, group, color,title);

    }

    public PropertyAskFor(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int setContentViewLayoutId() {
        return R.layout.fragment_property_ask_for;
    }
}
