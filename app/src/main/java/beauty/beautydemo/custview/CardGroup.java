package beauty.beautydemo.custview;

import android.content.Context;
import android.nfc.Tag;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nineoldandroids.animation.ObjectAnimator;

import java.util.ArrayList;
import java.util.List;

import beauty.beautydemo.R;
import beauty.beautydemo.dataprovider.DataProvider;
import beauty.beautydemo.tools.ScreenTools;

/**
 * Created by LJW on 15/3/22.
 */
public class CardGroup extends LinearLayout{

    private final static String TAG = "CardGroup";

    private Context mContext;

    private ArrayList<CardView> mCards = new ArrayList<>();


    public CardGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i(TAG, "width: " + widthMeasureSpec + ", height: " + heightMeasureSpec);

        //让子view大小不受父view限制
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//        heightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.UNSPECIFIED);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    public void addCard(CardView card) {
        mCards.add(card);
    }

    public void displayCards() {
        for (int i = 0; i < mCards.size(); i++) {

//            LayoutParams llp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            this.addView(mCards.get(i));
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        //状态栏高度
        int statusHeight = DataProvider.getStatusHeight();

        int cardWidht = 0;
        int cardHight = 0;

        // 初始下移的长度, 屏幕高度 - 底部菜单高度
        int h = DataProvider.getWindowHeight() - (int) getResources().getDimension(R.dimen.tab_menu_height);
        // 0为状态栏下面开始计算
        h -= statusHeight;
        h -= (int) getResources().getDimension(R.dimen.card_title_height) * getChildCount();

        for (int i = 0; i < getChildCount(); i++) {
            Log.i(TAG, "" + l + "," + t + "," + r + "," + b);
            CardView view = (CardView) getChildAt(i);
            // 设置卡片高度
            if (cardHight == 0) {
                cardHight = view.cardHight;
                b = cardHight;
            }
            // 设置卡片宽度
            if (cardWidht == 0) {
                cardWidht = r - (int) getResources().getDimension(R.dimen.card_corner) / 4;
                l = (int) getResources().getDimension(R.dimen.card_corner) / 4;
                r = cardWidht;
            }
            // 设置卡片位置
            view.layout(l, t + h, r, b + h);

            view.index = i;
            //设置其上升所需的距离
            view.upPx = -(t + h - (int) getResources().getDimension(R.dimen.card_corner) / 2);
            //设置其下降所需的距离
            int index = getChildCount() - i;
            //
            // cardHeight * 3 + 最终显示区域高度 - 3 * 最终距离
            // cardHeight * 2 + 最终显示区域高度 - 2 * 最终距离
            // cardHeight * 1 + 最终显示区域高度 - 1 * 最终距离
            //底部缩起来后的相距
            int ya = ScreenTools.dp2px(mContext, ScreenTools.px2dp(mContext, getResources().getDimension(R.dimen.tab_menu_height)) / getChildCount());
            view.downPx = (int) getResources().getDimension(R.dimen.card_title_height) * index + (int) getResources().getDimension(R.dimen.tab_menu_height) - ya * index;

            t += (int) getResources().getDimension(R.dimen.card_title_height);
            b += (int) getResources().getDimension(R.dimen.card_title_height);
        }


    }

    /**
     * 卡片展开通知卡片向上向下移动
     *
     * @param selfIndex
     */
    public List<ObjectAnimator> notifyCardsAction(int selfIndex) {
        List<ObjectAnimator> list = new ArrayList<>();

        for (CardView card : mCards) {
            if (card.index != selfIndex) {
                list.add(card.getCardDownObjectAnimator());
            } else {
                list.add(card.getCardUpObjectAnimator());
            }
        }

        return list;
    }

    /**
     * 卡片还原的时候,通知所有卡片移动
     *
     * @return
     */
    public List<ObjectAnimator> notifyCardReverseAction(int index) {
        List<ObjectAnimator> list = new ArrayList<>();

        for (CardView card : mCards) {
            ObjectAnimator l = card.getCardReverseAnimator(index);
            if (l != null) {
                list.add(l);
            }
        }

        return list;
    }
}
