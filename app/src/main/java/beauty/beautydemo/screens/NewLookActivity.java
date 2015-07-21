package beauty.beautydemo.screens;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextSwitcher;

import com.andtinder.model.CardModel;
import com.andtinder.view.CardContainer;

import java.util.ArrayList;
import java.util.List;

import beauty.beautydemo.R;
import beauty.beautydemo.adapter.StackViewAdapter;
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

        NewLookCardModel card1 = new NewLookCardModel("NewLook1", "变妆", getResources().getDrawable(R.drawable.newlook1));
        NewLookCardModel card2 = new NewLookCardModel("NewLook2", "20世纪30年代：憧憬影坛女星的妆容。这种妆容让人感觉到“坚强女性”，乍一看发型像海螺小姐。而漫画《海螺小姐》据说从1946年开始连载的。", getResources().getDrawable(R.drawable.newlook2));
        NewLookCardModel card3 = new NewLookCardModel("NewLook3", "20世纪50年代：模仿美式造型和赫本，模仿奥黛丽·赫本等巨星。", getResources().getDrawable(R.drawable.newlook3));
        NewLookCardModel card4 = new NewLookCardModel("NewLook4", "20世纪60年代：模仿西洋人脸型的妆容。这种妆容在2000年以后也以60年代复古风的形式出现在广告和杂志上。", getResources().getDrawable(R.drawable.newlook4));
        NewLookCardModel card5 = new NewLookCardModel("NewLook5", "20世纪70年代前半期：民族嬉皮士风格。这种风格直到2015年的现在，也在部分人群中有一定的人气。唱作人及个性的艺术家喜欢这种妆容。", getResources().getDrawable(R.drawable.newlook5));
        NewLookCardModel card6 = new NewLookCardModel("NewLook6", "20世纪70年代后半期：脱离欧美风重新认识日本美。", getResources().getDrawable(R.drawable.newlook6));
        NewLookCardModel card7 = new NewLookCardModel("NewLook7", "20世纪80年代初期到中期：日本职业女性的形象。这种妆容类似女演员宫泽理惠的妆容。", getResources().getDrawable(R.drawable.newlook7));
        NewLookCardModel card8 = new NewLookCardModel("NewLook8", "20世纪80年代后期到90年代初期：泡沫经济时期的齐发控。当时著名的女演员浅野温子和浅野优子的妆容就是这个感觉。", getResources().getDrawable(R.drawable.newlook8));
        NewLookCardModel card9 = new NewLookCardModel("NewLook9", "20世纪90年代后期到21世纪初：跨世纪的冷艳之美。日本90年代著名歌星安室奈美惠等当时就是这种妆容。", getResources().getDrawable(R.drawable.newlook9));
        NewLookCardModel card10 = new NewLookCardModel("NewLook10", "2011年以前：招桃花的盛装。类似人气模特蛯原友里的妆容。", getResources().getDrawable(R.drawable.newlook10));
        NewLookCardModel card11 = new NewLookCardModel("NewLook11", "2011年3月11日以后：可爱治愈型美妆。", getResources().getDrawable(R.drawable.newlook11));
        NewLookCardModel card12 = new NewLookCardModel("NewLook12", "21世纪10年代中期：景气恢复时期的泡沫经济复兴。最近在大街上经常看到化这种妆的女大学生。", getResources().getDrawable(R.drawable.newlook12));
        NewLookCardModel card13 = new NewLookCardModel("NewLook13", "2020年时流行妆容预想一：日式色彩。以日本传统化妆的三种颜色“红”、“白”、“黑”为基调。白色使肌肤充满透明感、内眼角明亮，黑色突出眼眶，红色突出唇部。将日本传统和现代风格调和，表现出日本女性的凛然之美。", getResources().getDrawable(R.drawable.newlook13));
        NewLookCardModel card14 = new NewLookCardModel("NewLook14", "2020年时流行妆容预想二：轻松休闲。用蓝色眼线画出双重眼影，整体轻松明快。在富有光泽的肌肤上配以橙色唇彩，轻快运动形象油然而生，表现出飒爽之美。", getResources().getDrawable(R.drawable.newlook14));
        setCardModelLinster(card1);
        setCardModelLinster(card2);
        setCardModelLinster(card3);
        setCardModelLinster(card4);
        setCardModelLinster(card5);
        setCardModelLinster(card6);
        setCardModelLinster(card7);
        setCardModelLinster(card8);
        setCardModelLinster(card9);
        setCardModelLinster(card10);
        setCardModelLinster(card11);
        setCardModelLinster(card12);
        setCardModelLinster(card13);
        setCardModelLinster(card14);
        images.add(card14);
        images.add(card13);
        images.add(card12);
        images.add(card11);
        images.add(card10);
        images.add(card9);
        images.add(card8);
        images.add(card7);
        images.add(card6);
        images.add(card5);
        images.add(card4);
        images.add(card3);
        images.add(card2);
        images.add(card1);

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
