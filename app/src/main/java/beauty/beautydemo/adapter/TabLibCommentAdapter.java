package beauty.beautydemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import beauty.beautydemo.R;
import beauty.beautydemo.bean.Comment;

/**
 * Created by LJW on 15/5/20.
 */
public class TabLibCommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Comment> list;
    private SparseArray head;

    static final int TYPE_PLACEHOLDER = Integer.MIN_VALUE;
    private static final int mPlaceholderSize = 1;

    public static final int comment_all = 0;
    public static final int comment_single = 1;
    public static final int comment_color = 2;
    public static final int comment_place = 3;

    public static enum ITEM_TYPE {
        TYPE_PLACEHOLDER,
        ITEM_TYPE_HEAD,
        ITEM_TYPE_COMMENT
    }

    public TabLibCommentAdapter(Context context, ArrayList<Comment> list, SparseArray<String> head) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.list = list;
        this.head = head;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == ITEM_TYPE.TYPE_PLACEHOLDER.ordinal()) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(com.github.florent37.materialviewpager.R.layout.material_view_pager_placeholder, parent, false);
            return new RecyclerView.ViewHolder(view) {
            };
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_HEAD.ordinal()) {
            return new HeadViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment_head, parent, false));
        } else {
            return new CommentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof HeadViewHolder) {
            HeadViewHolder h = (HeadViewHolder) holder;
            h.tv1.setText(String.valueOf(head.get(comment_all)));
            h.tv2.setText(String.valueOf(head.get(comment_single)));
            h.tv3.setText(String.valueOf(head.get(comment_color)));
            h.tv4.setText(String.valueOf(head.get(comment_place)));
        } else if(holder instanceof CommentViewHolder){

            position = position - 1 - mPlaceholderSize;
            CommentViewHolder c = (CommentViewHolder) holder;
            c.icon.setImageDrawable(context.getResources().getDrawable(Integer.valueOf(list.get(position).getIcon())));
            c.name.setText(list.get(position).getUserName());
            c.time.setText(list.get(position).getTime());
            c.comment.setText(list.get(position).getContent());
        }

    }

    @Override
    public int getItemCount() {
        return list.size() + 1 + mPlaceholderSize;
    }

    @Override
    public int getItemViewType(int position) {
        if (position ==0)
            return ITEM_TYPE.TYPE_PLACEHOLDER.ordinal();
        else if (position == 1) {
            return ITEM_TYPE.ITEM_TYPE_HEAD.ordinal();
        } else {
            return ITEM_TYPE.ITEM_TYPE_COMMENT.ordinal();
        }

    }

    public class HeadViewHolder extends RecyclerView.ViewHolder {

        public TextView tv1;
        public TextView tv2;
        public TextView tv3;
        public TextView tv4;

        public HeadViewHolder(View view) {
            super(view);

            tv1 = (TextView) view.findViewById(R.id.tv_comment_all);
            tv2 = (TextView) view.findViewById(R.id.tv_comment_single);
            tv3 = (TextView) view.findViewById(R.id.tv_comment_color);
            tv4 = (TextView) view.findViewById(R.id.tv_comment_place);
        }
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {

        public ImageView icon;
        public TextView name;
        public TextView time;
        public TextView comment;

        public CommentViewHolder(View view) {
            super(view);

//            icon = (ImageView) view.findViewById(R.id.iv_comment_icon);
//            name = (TextView) view.findViewById(R.id.tv_comment_name);
//            time = (TextView) view.findViewById(R.id.tv_comment_time);
//            comment = (TextView) view.findViewById(R.id.tv_comment);
        }
    }


}
