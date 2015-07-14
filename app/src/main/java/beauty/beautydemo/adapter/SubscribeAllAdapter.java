package beauty.beautydemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.lang.reflect.Array;
import java.util.ArrayList;

import beauty.beautydemo.R;
import beauty.beautydemo.base.BeautyBaseRecyclerAdapter;
import beauty.beautydemo.entity.SubscribeListItem;
import beauty.beautydemo.tools.Options;
import beauty.beautydemo.tools.ScreenTools;
import butterknife.ButterKnife;
import butterknife.InjectView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by LJW on 15/7/12.
 */
public class SubscribeAllAdapter extends BeautyBaseRecyclerAdapter<SubscribeAllAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private ArrayList<SubscribeListItem> mData;
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;

    private int lastAnimatedPosition = -1;
    private boolean animateItems = true;
    private static final int ANIMATED_ITEMS_COUNT = 2;

    public SubscribeAllAdapter(Context context, ArrayList<SubscribeListItem> mData) {
        this.mContext = context;
        this.mData = mData;
        inflater = LayoutInflater.from(context);
        options = Options.getListOptions();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_sub_all, parent, false));
    }

    @Override
    public void onBindViewHolderExtend(ViewHolder holder, int position) {
        imageLoader.displayImage(mData.get(position).icon, holder.mIcon, options);
        holder.mUsername.setText(mData.get(position).name);
        if (mData.get(position).isSubcribe) {
            holder.mAction.setImageResource(R.drawable.ic_check_box_grey_500_18dp);
        } else {
            holder.mAction.setImageResource(R.drawable.ic_add_grey_500_18dp);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private int startDelay = 0;
    private void runEnterAnimation(View view, int position) {
        if (!animateItems) {
            return;
        }

        if (position > lastAnimatedPosition) {
            lastAnimatedPosition = position;
            view.setTranslationY(ScreenTools.getScreenHeight(mContext));
            view.animate()
                    .translationY(0)
                    .setInterpolator(new DecelerateInterpolator(3.f))
                    .setStartDelay(startDelay)
                    .setDuration(700)
                    .start();
        }
        startDelay += 100;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.ivUserAvatar)
        CircleImageView mIcon;
        @InjectView(R.id.tv_sub_name)
        TextView mUsername;
        @InjectView(R.id.ib_action)
        ImageView mAction;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
