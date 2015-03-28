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

import beauty.beautydemo.R;
import beauty.beautydemo.custview.CardGroup;
import beauty.beautydemo.custview.CardView;

/**
 * Created by LJW on 15/3/20.
 */
public class PropertyMyColor extends CardView {


    public PropertyMyColor(Context context){
        super(context);

    }

    public PropertyMyColor(Context context,CardGroup group, int color){
        super(context,group,color);

    }

    public PropertyMyColor(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int setContentViewLayoutId() {
        return R.layout.fragment_property_my_color;
    }


}
