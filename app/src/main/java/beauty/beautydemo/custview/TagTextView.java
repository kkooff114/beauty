package beauty.beautydemo.custview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import beauty.beautydemo.R;
import beauty.beautydemo.tools.ScreenTools;

/**
 * Created by LJW on 15/7/14.
 */
public class TagTextView extends TextView {
    Context mContext;
    public TagTextView(Context context) {
        super(context);
        mContext = context;
        initTextView();
    }

    public TagTextView(Context context, String tag) {
        super(context);
        mContext = context;
        setText(tag);
        initTextView();
    }

    public TagTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initTextView();
    }

    public TagTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initTextView();
    }

    private void initTextView() {
        setBackgroundResource(R.drawable.bg_tag);
        setPaddingRelative((int) getResources().getDimension(R.dimen.dimen8), (int) getResources().getDimension(R.dimen.dimen4),
                (int) getResources().getDimension(R.dimen.dimen8), (int) getResources().getDimension(R.dimen.dimen4));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins((int) getResources().getDimension(R.dimen.dimen8), (int) getResources().getDimension(R.dimen.dimen8),
                (int) getResources().getDimension(R.dimen.dimen8), (int) getResources().getDimension(R.dimen.dimen8));
        setLayoutParams(params);
        setTextSize(12);
        setTextColor(getResources().getColor(R.color.md_pink_200));
    }
}
