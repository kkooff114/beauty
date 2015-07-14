package beauty.beautydemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import beauty.beautydemo.R;
import beauty.beautydemo.base.BeautyBaseRecyclerAdapter;
import beauty.beautydemo.entity.Tag;
import beauty.beautydemo.tools.Options;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by LJW on 15/7/14.
 */
public class TagAdapter extends BeautyBaseRecyclerAdapter<TagAdapter.ViewHolder> implements Filterable {

    private Context mContext;
    private LayoutInflater inflater;
    private ArrayList<Tag> mData;
    private ArrayList<Tag> list; // 搜索使用

    public static final String TAG_NAME = "tag_name";

    protected ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;
    private onItemOnClick lisener;

    public TagAdapter(Context context, ArrayList<Tag> data) {
        this.mContext = context;
        this.mData = data;
        this.list = new ArrayList<Tag>(data.size());
        this.list.addAll(data);
        inflater = LayoutInflater.from(context);
        options = Options.getListOptions();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_tag, parent, false));
    }

    @Override
    public void onBindViewHolderExtend(ViewHolder holder, int position) {
        imageLoader.displayImage(mData.get(position).icon, holder.mIcon, options);
        holder.mName.setText(mData.get(position).name);
        holder.mTagDesc.setText(mData.get(position).desc);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public Filter getFilter() {
        return new TagFilter(this, mData);
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.ivUserAvatar)
        CircleImageView mIcon;

        @InjectView(R.id.tv_tag_name)
        TextView mName;

        @InjectView(R.id.tv_tag_desc)
        TextView mTagDesc;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (lisener != null) {
                        lisener.onItemOnClick(mName.getText().toString());
                    }
                }
            });
        }
    }

    public interface onItemOnClick {
        public void onItemOnClick(String name);
    }

    public void setOnItemOnClickLisener(onItemOnClick lisener) {
        this.lisener = lisener;
    }


    class TagFilter extends Filter {

        private final TagAdapter adapter;
        private ArrayList<Tag> filterList = new ArrayList<>();


        public TagFilter(TagAdapter adapter, ArrayList<Tag> list) {
            super();
            this.adapter = adapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            filterList.clear();
            FilterResults results = new FilterResults();

            if (constraint.length() == 0) {
                filterList.addAll(list);
            } else {

                for (Tag tag : list) {
                    if (tag.name.contains(constraint) || tag.desc.contains(constraint)) {
                        filterList.add(tag);
                    }
                }
            }

            results.count = filterList.size();
            results.values = filterList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            adapter.mData.clear();
            adapter.mData.addAll((ArrayList<Tag>) results.values);
            adapter.notifyDataSetChanged();
        }
    }
}
