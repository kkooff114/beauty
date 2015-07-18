package beauty.beautydemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import beauty.beautydemo.R;
import beauty.beautydemo.base.BeautyBaseRecyclerAdapter;
import beauty.beautydemo.custview.imageprocessing.filter.BasicFilter;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by LJW on 15/7/16.
 */
public class ImageFilterAdapter extends BeautyBaseRecyclerAdapter<ImageFilterAdapter.ViewHolder> {


    private List<BasicFilter> filters;
    private LayoutInflater inflater;
    private Context mContext;


    public ImageFilterAdapter(Context context, ArrayList<BasicFilter> list) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        filters = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_image_filter, parent, false));
    }

    @Override
    public void onBindViewHolderExtend(ViewHolder holder, int position) {

        holder.mFilterDesc.setText("滤镜" + position + "\n" + filters.get(position).getClass().getSimpleName());
    }

    @Override
    public int getItemCount() {
        return filters.size();
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
