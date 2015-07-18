package beauty.beautydemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by LJW on 15/7/17.
 */
public class FirstSelectTagAdapter extends BeautyBaseRecyclerAdapter<FirstSelectTagAdapter.ViewHolder> {


    private Context mContext;
    private ArrayList<Tag> mData;
    private LayoutInflater inflater;

    public FirstSelectTagAdapter(Context context, ArrayList<Tag> list) {
        inflater = LayoutInflater.from(context);
        mContext = context;
        mData = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(inflater.inflate(R.layout.item_tag_grid_add, parent, false));

    }

    @Override
    public void onBindViewHolderExtend(ViewHolder holder, int position) {


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
        @InjectView(R.id.ch1)
        CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkBox.isChecked()){
                        checkBox.setChecked(false);
                    }else{
                        checkBox.setChecked(true);
                    }
                }
            });
        }
    }
}
