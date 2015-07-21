package beauty.beautydemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import beauty.beautydemo.R;
import beauty.beautydemo.base.BeautyBaseRecyclerAdapter;
import beauty.beautydemo.entity.MyIcon;
import beauty.beautydemo.screens.ImagePagerActivity;
import beauty.beautydemo.tools.Options;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by LJW on 15/7/18.
 */
public class MyIconAdapter extends BeautyBaseRecyclerAdapter<MyIconAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private ArrayList<MyIcon> mData;
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;


    public MyIconAdapter(Context context, ArrayList<MyIcon> list) {
        this.mContext = context;
        inflater = LayoutInflater.from(context);
        this.mData = list;
        options = Options.getListOptions();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_my_icon, parent, false));
    }

    @Override
    public void onBindViewHolderExtend(ViewHolder holder,final int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ImagePagerActivity.class);
                intent.putExtra("list", mData);
                intent.putExtra("position", position);
                mContext.startActivity(intent);
            }
        });

        imageLoader.displayImage(mData.get(position).icon, holder.mMyIcon, options);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.iv_my_icon)
        ImageView mMyIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);

        }
    }


}
