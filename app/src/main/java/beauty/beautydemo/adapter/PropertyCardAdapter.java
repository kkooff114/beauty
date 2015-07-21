package beauty.beautydemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import beauty.beautydemo.R;
import beauty.beautydemo.base.BeautyBaseActivity;
import beauty.beautydemo.base.BeautyBaseRecyclerAdapter;
import beauty.beautydemo.screens.MyIconActivity;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 个人档案卡片adapter
 * Created by LJW on 15/7/19.
 */
public class PropertyCardAdapter extends BeautyBaseRecyclerAdapter<PropertyCardAdapter.ViewHolder> {


    private LayoutInflater inflater;
    private Context mContext;
    private ArrayList<String> mData;

    public PropertyCardAdapter(Context context, ArrayList<String> titles) {
        this.inflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mData = titles;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.include_property_card, parent, false));
    }

    @Override
    public void onBindViewHolderExtend(ViewHolder holder, int position) {
        if (position == 0) {
            holder.mIcon.setImageResource(R.drawable.ic_sub_tmz);
            holder.mCount.setText((position + 3) * 24 + "");
        } else if (position == 1) {
            holder.mIcon.setImageResource(R.drawable.ic_sub_sbz);
            holder.mCount.setText((position + 3) * 24 + "");
        } else {
            holder.mIcon.setImageResource(R.drawable.ic_sub_hm);
            holder.mCount.setText((position + 3) * 24 + "");
        }

        holder.mName.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.iv_icon)
        ImageView mIcon;
        @InjectView(R.id.tv_name)
        TextView mName;
        @InjectView(R.id.tv_count)
        TextView mCount;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, MyIconActivity.class);
                    intent.putExtra(BeautyBaseActivity.TITLE, mName.getText().toString());
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
