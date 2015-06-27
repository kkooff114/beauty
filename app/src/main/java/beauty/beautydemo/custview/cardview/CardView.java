package beauty.beautydemo.custview.cardview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.ArrayList;
import java.util.List;

import beauty.beautydemo.R;
import beauty.beautydemo.dataprovider.DataProvider;
import beauty.beautydemo.entity.MoveViewParms;
import beauty.beautydemo.tools.CustBounceInterpolator;
import beauty.beautydemo.tools.CustovershootInterpolator;

/**
 * Created by LJW on 15/3/19.
 */
public abstract class CardView extends LinearLayout implements View.OnClickListener {

    private static final String TAG = "CardView";
    private static final int duration2 = (int) 1.5 * 1000;
    private static final int duration1 = 1 * 1000;
    private static final CustovershootInterpolator overshootInterpolator = new CustovershootInterpolator(4f);
    private static final CustBounceInterpolator bounceInterpolator = new CustBounceInterpolator();
    private static final DecelerateInterpolator decelerInterpolator = new DecelerateInterpolator(4f);
    private View rootView = null;
    private Context mContext;
    private LayoutInflater inflater;
    public final static int CARD_STATUS_NORMAL = 0;//卡片正常状态
    public final static int CARD_STATUS_OPEN = 1;//卡片打开
    public final static int CARD_STATUS_CLOSE = 2;//卡片关闭
    private int cardStatus = 0;
    private View mContentView;
    private CardGroup group;

    private ImageView close;


    private onCardMoveListener listener;

    public final static int CARD_COLOR_RED = R.color.bg_card_red;
    public final static int CARD_COLOR_ORANGE = R.color.bg_card_orange;
    public final static int CARD_COLOR_YELLOW = R.color.bg_card_yellow;
    public final static int CARD_COLOR_GREEN = R.color.bg_card_green;
    private int cardColor = 0;

    public int upPx = 0;//上移距离
    public int downPx = 0;//上移距离
    public int index = -1;//卡片在CardGroup中index

    public int cardHight = 0;
    private String title = "";

    public CardView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public CardView(Context context, CardGroup group, int cardThemeColor, String title) {
        super(context);
        mContext = context;
        cardColor = cardThemeColor;
        this.group = group;
        this.title = title;

        initView();
    }

    public CardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        rootView = this;
        initView();

    }


    /**
     * 初始化界面,findById()
     */

    private void initView() {

        this.setOrientation(VERTICAL);
        this.setBackgroundColor(getResources().getColor(cardColor));
        switch (cardColor) {
            case CARD_COLOR_RED:
                this.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_corner_red));
                break;
            case CARD_COLOR_GREEN:
                this.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_corner_green));
                break;
            case CARD_COLOR_YELLOW:
                this.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_corner_yellow));
                break;
            case CARD_COLOR_ORANGE:
                this.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_corner_orange));
                break;
            default:
                this.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_corner_red));
                break;
        }

        int statusHeight = DataProvider.getStatusHeight();

        //card_head
        final RelativeLayout head = new RelativeLayout(mContext);
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen.card_title_height));
//        head.setBackgroundColor(getResources().getColor(R.color.theme_color));
        head.setLayoutParams(rlp);
        //card_head close
        close = new ImageView(mContext);
        RelativeLayout.LayoutParams ivp = new RelativeLayout.LayoutParams((int) getResources().getDimension(R.dimen.card_title_close), (int) getResources().getDimension(R.dimen.card_title_close));
        ivp.leftMargin = (int) getResources().getDimension(R.dimen.card_title_close) / 2;
        close.setLayoutParams(ivp);
        close.setId(R.id.card_head_close);
        close.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_close_grey));
        close.setOnClickListener(this);
        close.setVisibility(INVISIBLE);
        head.addView(close);
        //card_head menu
        final ImageView menu = new ImageView(mContext);
        RelativeLayout.LayoutParams ivmp = new RelativeLayout.LayoutParams((int) (getResources().getDimension(R.dimen.card_title_close) * 1.5), (int) getResources().getDimension(R.dimen.card_title_close));
        ivmp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        menu.setId(R.id.card_head_menu);
        menu.setLayoutParams(ivmp);
        menu.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_menu_grey_liang));
        head.addView(menu);
        //card_head title
        final TextView titleTV = new TextView(mContext);
        RelativeLayout.LayoutParams tvlp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvlp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        tvlp.addRule(RelativeLayout.BELOW, R.id.card_head_menu);
        titleTV.setLayoutParams(tvlp);
        titleTV.setText(title);
        titleTV.setTextColor(mContext.getResources().getColor(R.color.white));
        titleTV.setTextSize(18);
        head.addView(titleTV);

        this.addView(head);

        //card_body
        // card_body_height
        int bodyHeight = DataProvider.getWindowHeight() - (int) getResources().getDimension(R.dimen.card_title_height) - (int) getResources().getDimension(R.dimen.card_body_margin_bottom) - statusHeight;
        cardHight = bodyHeight + (int) getResources().getDimension(R.dimen.card_title_height);
        Log.i(TAG, "bodyHeight:" + bodyHeight);
        final LinearLayout ll = new LinearLayout(mContext);
        LayoutParams llp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, bodyHeight);
        ll.setId(R.id.card_body);
        ll.setOrientation(VERTICAL);
        ll.setLayoutParams(llp);

        this.addView(ll);


        initCardAnimationPosition();

    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    protected void initCardAnimationPosition() {


    }


    //展开卡片手势事件
    @Override
    public boolean dispatchTouchEvent(final MotionEvent event) {

        if (cardStatus == CARD_STATUS_OPEN || cardStatus == CARD_STATUS_CLOSE) {
            return super.dispatchTouchEvent(event);
        } else {

            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:
                    Log.i(TAG, "action_down " + event.getY());

                    break;

                case MotionEvent.ACTION_MOVE:
                    Log.i(TAG, "action_move");

                    break;

                case MotionEvent.ACTION_UP:
                    Log.i(TAG, "action_up");

                    break;
            }

            return super.dispatchTouchEvent(event);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        if (cardStatus == CARD_STATUS_OPEN || cardStatus == CARD_STATUS_CLOSE) {
            return super.onInterceptTouchEvent(ev);
        }

        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "intercept_move");
                break;

            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, "intercept_move");

                break;

            case MotionEvent.ACTION_UP:
                Log.i(TAG, "intercept_up");

                break;

        }

        return true;
    }


    float y = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {


        if (cardStatus == CARD_STATUS_OPEN || cardStatus == CARD_STATUS_CLOSE) {
            return super.onInterceptTouchEvent(event);
        }

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                y = event.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, "touchEvent_move");

                break;

            case MotionEvent.ACTION_UP:
                Log.i(TAG, "touchEvent_up " + event.getY());
                if (y - event.getY() > getResources().getDimension(R.dimen.card_handle_move_length)) {
//                    //卡片移动
                    cardAction();
//
//                    // 将自定义的content界面设置到卡片中
                    LinearLayout cardBody = (LinearLayout) this.findViewById(R.id.card_body);
                    inflater = LayoutInflater.from(mContext);
                    mContentView = inflater.inflate(setContentViewLayoutId(), null);
                    LayoutParams llp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    cardBody.addView(mContentView, llp);
                }
                break;

        }

        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_head_close://卡片关闭

                cardReverseAction(this.index);

                break;

        }
    }

    /**
     * 卡片移动
     */
    private void cardAction() {

        close.setVisibility(VISIBLE);

        ArrayList<Animator> list = new ArrayList<>();
        if (listener != null) {
            List<MoveViewParms> parms = listener.onMove();
            if (parms != null && parms.size() > 0) {
                for (int i = 0; i < parms.size(); i++) {
                    ObjectAnimator oa = ObjectAnimator.ofFloat(parms.get(i).view, "translationY", parms.get(i).px);
                    oa.setInterpolator(decelerInterpolator);
                    oa.setDuration(duration1);
                    list.add(oa);
                }
            }
        }

        //通知卡片上移下移动画
        List<ObjectAnimator> downAnimatorList = group.notifyCardsAction(index);
        if (downAnimatorList != null) {
            for (int i = 0; i < downAnimatorList.size(); i++) {
                list.add(downAnimatorList.get(i));
            }
        }

        AnimatorSet set = new AnimatorSet();
        set.playTogether(list);
        set.setDuration(duration2).start();
    }

    /**
     * 卡片复原动作
     */
    private void cardReverseAction(int index) {
        close.setVisibility(INVISIBLE);
        ArrayList<Animator> list = new ArrayList<>();

        if (listener != null) {
            List<MoveViewParms> parms = listener.onMove();
            if (parms != null && parms.size() > 0) {
                for (int i = 0; i < parms.size(); i++) {
                    ObjectAnimator oa = ObjectAnimator.ofFloat(parms.get(i).view, "translationY", 0);
                    oa.setInterpolator(decelerInterpolator);
                    oa.setDuration(duration1);
                    list.add(oa);
                }
            }
        }

        // 卡片复原动画
        List<ObjectAnimator> cardReverseList = group.notifyCardReverseAction(index);
        if (cardReverseList != null) {
            for (int i = 0; i < cardReverseList.size(); i++) {
                list.add(cardReverseList.get(i));
            }
        }

        AnimatorSet set = new AnimatorSet();
        set.playTogether(list);
        set.setDuration(duration2).start();

    }

    /**
     * 获得卡片下移时的动画对象
     */
    public ObjectAnimator getCardDownObjectAnimator() {
        cardStatus = CARD_STATUS_CLOSE;
        ObjectAnimator oa = ObjectAnimator.ofFloat(this, "translationY", downPx);
        oa.setInterpolator(bounceInterpolator);
        return oa;
    }

    /**
     * 获得卡片上移时的动画对象
     */
    public ObjectAnimator getCardUpObjectAnimator() {
        cardStatus = CARD_STATUS_OPEN;
        ObjectAnimator oa = ObjectAnimator.ofFloat(this, "translationY", upPx);
        oa.setInterpolator(overshootInterpolator);
        return oa;
    }

    /**
     * 获得卡片复原的动画
     *
     * @return
     */
    public ObjectAnimator getCardReverseAnimator(int index) {
//        if (cardStatus == CARD_STATUS_OPEN) {
//            cardStatus = CARD_STATUS_NORMAL;
//            return ObjectAnimator.ofFloat(this, "translationY", -upPx);
//
//        } else if (cardStatus == CARD_STATUS_CLOSE) {
//            cardStatus = CARD_STATUS_NORMAL;
//            return ObjectAnimator.ofFloat(this, "translationY", -downPx);
//
//        }
        cardStatus = CARD_STATUS_NORMAL;
        ObjectAnimator oa = ObjectAnimator.ofFloat(this, "translationY", 0);
        if (this.index == index) {
            oa.setInterpolator(decelerInterpolator);
        } else {
            oa.setInterpolator(decelerInterpolator);
        }
        return oa;

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public abstract int setContentViewLayoutId();


    /**
     * 返回card root view
     *
     * @return
     */
    public View getRootView() {
        return rootView;
    }


    /**
     * 当卡片开始移动时触发
     * 可用于移动title,底部menu等一些阻碍card全屏的控件
     */
    public interface onCardMoveListener {
        public List<MoveViewParms> onMove();
    }

    public void setListener(onCardMoveListener listener) {
        this.listener = listener;
    }

}
