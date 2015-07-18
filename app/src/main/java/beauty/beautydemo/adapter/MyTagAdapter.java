package beauty.beautydemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import beauty.beautydemo.R;
import beauty.beautydemo.base.BeautyBaseActivity;
import beauty.beautydemo.base.BeautyBaseRecyclerAdapter;
import beauty.beautydemo.entity.Tag;
import beauty.beautydemo.screens.AllTagActivity;
import beauty.beautydemo.screens.MomentsActivity;
import butterknife.ButterKnife;
import butterknife.InjectView;
import ch.haclyon.driveimageview.DriveImageModel;
import ch.haclyon.driveimageview.DriveImageView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by LJW on 15/7/17.
 */
public class MyTagAdapter extends BeautyBaseRecyclerAdapter<MyTagAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Tag> mData;
    private LayoutInflater inflater;

    public MyTagAdapter(Context context, ArrayList<Tag> list) {
        inflater = LayoutInflater.from(context);
        mContext = context;
        mData = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(inflater.inflate(R.layout.item_tag_grid, parent, false));

    }

    @Override
    public void onBindViewHolderExtend(ViewHolder holder, int position) {

        if (position == mData.size() - 1) {
            holder.mIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_add_grey_500_48dp));
            holder.mName.setText("增加标签");
            return;
        }

        holder.mIcon.setImageDrawable(mContext.getResources().getDrawable(mData.get(position).iconInt));
        holder.mName.setText(mData.get(position).name);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.ivUserAvatar)
        CircleImageView mIcon;
        @InjectView(R.id.tv_name)
        TextView mName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mName.getText().toString().equals("增加标签")) {
                        Intent intent = new Intent(mContext, AllTagActivity.class);
                        intent.putExtra(BeautyBaseActivity.TOOL_BAR_TITLE, mName.getText().toString());
                        mContext.startActivity(intent);
                        return;
                    }
                    Intent intent = new Intent(mContext, MomentsActivity.class);
                    intent.putExtra(BeautyBaseActivity.TOOL_BAR_TITLE, mName.getText().toString());
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
