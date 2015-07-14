package beauty.beautydemo.screens;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextSwitcher;

import com.andtinder.model.CardModel;
import com.andtinder.view.CardContainer;
import com.stackview.StackViewVertical;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beauty.beautydemo.R;
import beauty.beautydemo.adapter.StackViewAdapter;
import beauty.beautydemo.base.BeautyBaseActivity;
import beauty.beautydemo.entity.NewLookCardModel;
import beauty.beautydemo.screens.materialmenu.SimpleHeaderDrawerActivity;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by LJW on 15/7/4.
 */
public class NewLookActivity extends Activity {

    @InjectView(R.id.stackViewVertical)
    CardContainer mStackView;

    @InjectView(R.id.ts_unlike_num)
    TextSwitcher mDislikes;

    @InjectView(R.id.ts_like_num)
    TextSwitcher mLikes;

    private final SparseArray<Integer> likesCount = new SparseArray();
    private final static int DISLIKES = 1;
    private final static int LIKES = 2;

    private List<NewLookCardModel> mImagesLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_look);
        ButterKnife.inject(this);

        likesCount.put(LIKES, 0);
        likesCount.put(DISLIKES, 0);


        mImagesLink = getImageLink();
        initStackView();

    }

    private void initStackView() {
        mStackView.setAdapter(new StackViewAdapter(NewLookActivity.this, mImagesLink));
    }

    private ArrayList<NewLookCardModel> getImageLink() {

        ArrayList<NewLookCardModel> images = new ArrayList<NewLookCardModel>();

        NewLookCardModel card1 = new NewLookCardModel("Title1", "Description goes here", getResources().getDrawable(R.drawable.question0601));
        NewLookCardModel card2 = new NewLookCardModel("Title2", "Description goes here", getResources().getDrawable(R.drawable.question0602));
        NewLookCardModel card3 = new NewLookCardModel("Title3", "Description goes here", getResources().getDrawable(R.drawable.question0603));
        NewLookCardModel card4 = new NewLookCardModel("Title4", "Description goes here", getResources().getDrawable(R.drawable.question0604));
        NewLookCardModel card5 = new NewLookCardModel("Title5", "Description goes here", getResources().getDrawable(R.drawable.question0605));
        NewLookCardModel card6 = new NewLookCardModel("Title3", "Description goes here", getResources().getDrawable(R.drawable.question0603));
        NewLookCardModel card7 = new NewLookCardModel("Title4", "Description goes here", getResources().getDrawable(R.drawable.question0604));
        NewLookCardModel card8 = new NewLookCardModel("Title5", "Description goes here", getResources().getDrawable(R.drawable.question0605));
        setCardModelLinster(card1);
        setCardModelLinster(card2);
        setCardModelLinster(card3);
        setCardModelLinster(card4);
        setCardModelLinster(card5);
        setCardModelLinster(card6);
        setCardModelLinster(card7);
        setCardModelLinster(card8);
        images.add(card1);
        images.add(card2);
        images.add(card3);
        images.add(card4);
        images.add(card5);
        images.add(card6);
        images.add(card7);
        images.add(card8);

        return images;
    }

    private void setCardModelLinster(NewLookCardModel card) {
        card.setOnCardDimissedListener(new CardModel.OnCardDimissedListener() {
            @Override
            public void onLike() {
                updateLikesCounter(mDislikes, DISLIKES);
            }

            @Override
            public void onDislike() {

                updateLikesCounter(mLikes, LIKES);
            }
        });
    }

    private void updateLikesCounter(TextSwitcher tsLikesCounter, Integer flag) {
        int currentLikesCount = likesCount.get(flag) + 1;

        String likesCountText = "";
        if (flag == LIKES) {
            likesCountText = getResources().getQuantityString(
                    R.plurals.likes_count, currentLikesCount, currentLikesCount
            );
        } else {
            likesCountText = getResources().getQuantityString(
                    R.plurals.dislikes_count, currentLikesCount, currentLikesCount
            );
        }

        tsLikesCounter.setText(likesCountText);


        likesCount.put(flag, currentLikesCount);
    }

    @OnClick(R.id.iv_cancel_to_home)
    public void cancelToHome(View v) {
        Intent intent = new Intent(NewLookActivity.this, SimpleHeaderDrawerActivity.class);
        startActivity(intent);
    }
}
