package beauty.beautydemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import beauty.beautydemo.R;
import beauty.beautydemo.base.BeautyBaseRecyclerAdapter;
import beauty.beautydemo.custview.imagefilter.lib.GPUImageFilter;
import beauty.beautydemo.entity.FilterEntity;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by LJW on 15/7/22.
 */
public class ImageFilterAdapter extends BeautyBaseRecyclerAdapter<ImageFilterAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<FilterEntity> mData;
    private LayoutInflater inflater;

    public ImageFilterAdapter(Context context, ArrayList<FilterEntity> list) {
        this.mContext = context;
        inflater = LayoutInflater.from(context);
        mData = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_image_filter, parent, false));
    }

    @Override
    public void onBindViewHolderExtend(ViewHolder holder, int position) {

        holder.mFilterDesc.setText(mData.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.tv_filter_desc)
        TextView mFilterDesc;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);

        }
    }
}
