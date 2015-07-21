package beauty.beautydemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import beauty.beautydemo.R;

/**
 * 实验室,实验内容图片adapter
 * Created by LJW on 15/7/18.
 */
public class LibContentGridAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<String> list;
    private Context mContext;

    public LibContentGridAdapter(Context context, ArrayList<String> list) {
        inflater = LayoutInflater.from(context);
        this.list = list;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;

        if (view == null) {

            view = inflater.inflate(R.layout.item_gridview_imageview, viewGroup, false);
            holder = new ViewHolder();
            holder.iv = (ImageView) view.findViewById(R.id.iv_item_gridview);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.iv.setImageDrawable(mContext.getResources().getDrawable(Integer.valueOf(list.get(i))));

        return view;
    }

    class ViewHolder {
        private ImageView iv;
    }


}
