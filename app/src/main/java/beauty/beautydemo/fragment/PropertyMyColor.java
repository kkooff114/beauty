package beauty.beautydemo.fragment;

import android.content.Context;
import android.util.AttributeSet;

import beauty.beautydemo.R;
import beauty.beautydemo.custview.cardview.CardGroup;
import beauty.beautydemo.custview.cardview.CardView;

/**
 * Created by LJW on 15/3/20.
 */
public class PropertyMyColor extends CardView {


    public PropertyMyColor(Context context){
        super(context);

    }

    public PropertyMyColor(Context context,CardGroup group, int color,String title){
        super(context,group,color,title);

    }

    public PropertyMyColor(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int setContentViewLayoutId() {
        return R.layout.fragment_property_my_color;
    }


}
