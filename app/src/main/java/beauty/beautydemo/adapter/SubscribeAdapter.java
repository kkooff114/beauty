package beauty.beautydemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import beauty.beautydemo.R;
import beauty.beautydemo.base.BeautyBaseRecyclerAdapter;
import beauty.beautydemo.entity.SubscribeListItem;
import beauty.beautydemo.screens.SubscribeContentListActivity;
import beauty.beautydemo.tools.Options;
import beauty.beautydemo.tools.ScreenTools;
import butterknife.ButterKnife;
import butterknife.InjectView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by LJW on 15/7/7.
 */
public class SubscribeAdapter extends BeautyBaseRecyclerAdapter<SubscribeAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private ArrayList<SubscribeListItem> list;

    public static String TYPE = "type";

    protected ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;

    public SubscribeAdapter(Context context, ArrayList<SubscribeListItem> list) {

        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
        options = Options.getListOptions();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_subscribe, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolderExtend(ViewHolder holder, int position) {
        imageLoader.displayImage(list.get(position).icon, holder.mUserIcon, options);
        holder.mUsername.setText(list.get(position).name);
        holder.mContent.setText(list.get(position).message);
        holder.mTime.setText(list.get(position).time);
    }

    private int startDelay = 0;
    private int lastAnimatedPosition = -1;
    private void runEnterAnimation(View view, int position) {

        if (position > lastAnimatedPosition) {
            lastAnimatedPosition = position;
            view.setTranslationY(ScreenTools.getScreenHeight(context));
            view.animate()
                    .translationY(0)
                    .setInterpolator(new DecelerateInterpolator(3.f))
                    .setStartDelay(startDelay)
                    .setDuration(700)
                    .start();
        }
        startDelay += 100;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.ivUserAvatar)
        CircleImageView mUserIcon;
        @InjectView(R.id.tv_sub_name)
        TextView mUsername;
        @InjectView(R.id.tv_content)
        TextView mContent;
        @InjectView(R.id.tv_time)
        TextView mTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SubscribeContentListActivity.class);
                    intent.putExtra(TYPE, mUsername.getText().toString());
                    context.startActivity(intent);
                }
            });
        }
    }
}
