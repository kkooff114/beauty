package beauty.beautydemo.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import beauty.beautydemo.R;
import beauty.beautydemo.entity.SubcribeContentListItem;
import beauty.beautydemo.screens.SubscribeContentDetailActivity;
import beauty.beautydemo.tools.Options;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by LJW on 15/7/8.
 */
public class SubscribeContentAdapter extends RecyclerView.Adapter<SubscribeContentAdapter.ViewHolder> {

    public static final String URL = "url";
    private static final DecelerateInterpolator DECCELERATE_INTERPOLATOR = new DecelerateInterpolator();
    private static final AccelerateInterpolator ACCELERATE_INTERPOLATOR = new AccelerateInterpolator();
    private static final OvershootInterpolator OVERSHOOT_INTERPOLATOR = new OvershootInterpolator(4);
//    private final Map<RecyclerView.ViewHolder, AnimatorSet> likeAnimations = new HashMap<>();

    private Context mContext;
    private ArrayList<SubcribeContentListItem> mListData;
    private LayoutInflater inflater;
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;

    public SubscribeContentAdapter(Context context, ArrayList<SubcribeContentListItem> list) {
        inflater = LayoutInflater.from(context);
        this.mContext = context;
        mListData = list;
        options = Options.getListOptions();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(inflater.inflate(R.layout.item_subscribe_content, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mTitle.setText(mListData.get(position).title);
        imageLoader.displayImage(mListData.get(position).img, holder.mImage, options);
        holder.mContent.setText(mListData.get(position).content);
        holder.mPublicTime.setText(mListData.get(position).publicTime);
        holder.mUrl.setText(mListData.get(position).url);
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.tv_title)
        TextView mTitle;
        @InjectView(R.id.ivFeedCenter)
        ImageView mImage;
        @InjectView(R.id.tv_content)
        TextView mContent;
        @InjectView(R.id.tv_public_time)
        TextView mPublicTime;
        @InjectView(R.id.tv_url)
        TextView mUrl;
        @InjectView(R.id.tsLikesCounter)
        TextSwitcher tsLikesCounter;
        @InjectView(R.id.btnLike)
        ImageButton btnLike;
        @InjectView(R.id.btnMore)
        ImageButton btnMore;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, SubscribeContentDetailActivity.class);
                    intent.putExtra(URL, mUrl.getText().toString());
                    mContext.startActivity(intent);
                }
            });
        }

        @OnClick(R.id.btnLike)
        void likeClick(View v) {
            updateLikesCounter();
            updateHeartButton();

        }

        @OnClick(R.id.btnMore)
        void moreClick(View v) {
            
        }

        private void updateLikesCounter() {
            int currentLikesCount = 123 + 1;
            String likesCountText = mContext.getResources().getQuantityString(
                    R.plurals.likes_count, currentLikesCount, currentLikesCount
            );
            tsLikesCounter.setText(likesCountText);
        }

        private void updateHeartButton() {

            AnimatorSet animatorSet = new AnimatorSet();

            ObjectAnimator rotationAnim = ObjectAnimator.ofFloat(btnLike, "rotation", 0f, 360f);
            rotationAnim.setDuration(300);
            rotationAnim.setInterpolator(ACCELERATE_INTERPOLATOR);

            ObjectAnimator bounceAnimX = ObjectAnimator.ofFloat(btnLike, "scaleX", 0.2f, 1f);
            bounceAnimX.setDuration(300);
            bounceAnimX.setInterpolator(OVERSHOOT_INTERPOLATOR);

            ObjectAnimator bounceAnimY = ObjectAnimator.ofFloat(btnLike, "scaleY", 0.2f, 1f);
            bounceAnimY.setDuration(300);
            bounceAnimY.setInterpolator(OVERSHOOT_INTERPOLATOR);
            bounceAnimY.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    btnLike.setImageResource(R.drawable.ic_heart_red);
                }
            });

            animatorSet.play(rotationAnim);
            animatorSet.play(bounceAnimX).with(bounceAnimY).after(rotationAnim);

            animatorSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {

                }
            });

            animatorSet.start();
        }

    }


}
