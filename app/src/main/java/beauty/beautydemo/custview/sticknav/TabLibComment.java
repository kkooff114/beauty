package beauty.beautydemo.custview.sticknav;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;

import java.util.ArrayList;
import java.util.List;

import beauty.beautydemo.R;
import beauty.beautydemo.adapter.TabLibCommentAdapter;
import beauty.beautydemo.adapter.TestRecyclerViewAdapter;
import beauty.beautydemo.tools.Constants;

/**
 * Created by LJW on 15/4/10.
 */
public class TabLibComment extends Fragment {

    public static final String TITLE = "title";
    private String mTitle = "Defaut Value";
    private RecyclerView lv_comment;


    public static TabLibComment newInstance(String title) {
        TabLibComment tabFragment = new TabLibComment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_lib_comment, container, false);


        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lv_comment = (RecyclerView) view.findViewById(R.id.lv_comment);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        lv_comment.setLayoutManager(layoutManager);

        // 设置固定大小
        lv_comment.setHasFixedSize(true);

        lv_comment.setAdapter(new TabLibCommentAdapter(getActivity(), Constants.getLibComment(), getCommentHead()));

//        List<Object> mContentItems = new ArrayList<>();
//        for (int i = 0; i < 100; ++i)
//            mContentItems.add(new Object());
//        RecyclerView.Adapter mAdapter;
//        mAdapter = new RecyclerViewMaterialAdapter(new TestRecyclerViewAdapter(mContentItems));
//        lv_comment.setAdapter(mAdapter);
        MaterialViewPagerHelper.registerRecyclerView(getActivity(), lv_comment, null);
    }


//    class adapter extends BaseAdapter {
//
//        private ArrayList<Comment> list;
//        private LayoutInflater inflater;
//        private SparseArray head;
//        private final static int TYPE_HEAD = 0;
//        private final static int TYPE_NOMAL = 1;
//
//        public adapter(Context context, ArrayList<Comment> list, SparseArray<String> head) {
//            inflater = LayoutInflater.from(context);
//            this.list = list;
//            this.head = head;
//        }
//
//        @Override
//        public int getCount() {
//            return list.size();
//        }
//
//        @Override
//        public Object getItem(int i) {
//            System.out.println("getItem "+i);
//            return list.get(i);
//
//        }
//
//        @Override
//        public long getItemId(int i) {
//            System.out.println("getItemid "+i);
//            return i;
//        }
//
//        @Override
//        public int getItemViewType(int position) {
//            System.out.println("getItemViewType "+position);
//
//            if (position == 0) {
//                return TYPE_HEAD;
//            } else {
//                return TYPE_NOMAL;
//            }
//        }
//
//        @Override
//        public int getViewTypeCount() {
//            return 2;
//        }
//
//        @Override
//        public View getView(int i, View view, ViewGroup viewGroup) {
//            System.out.println("getView "+i);
//
//            int type = getItemViewType(i);
//
//            ViewHolder holder = null;
//            ViewHolderHead holderHead = null;
//            if (view == null) {
//
//                switch (type) {
//                    case TYPE_HEAD:
//                        view = inflater.inflate(R.layout.item_comment_head, null);
//                        holderHead = new ViewHolderHead();
//                        holderHead.tv1 = (TextView) view.findViewById(R.id.tv_comment_all);
//                        holderHead.tv2 = (TextView) view.findViewById(R.id.tv_comment_single);
//                        holderHead.tv3 = (TextView) view.findViewById(R.id.tv_comment_color);
//                        holderHead.tv4 = (TextView) view.findViewById(R.id.tv_comment_place);
//
//
//                        view.setTag(holderHead);
//
//                        break;
//
//                    case TYPE_NOMAL:
//                        holder = new ViewHolder();
//                        view = inflater.inflate(R.layout.item_comment, null);
//                        holder.icon = (ImageView) view.findViewById(R.id.iv_comment_icon);
//                        holder.name = (TextView) view.findViewById(R.id.tv_comment_name);
//                        holder.time = (TextView) view.findViewById(R.id.tv_comment_time);
//                        holder.comment = (TextView) view.findViewById(R.id.tv_comment);
//
//                        view.setTag(holder);
//
//                        break;
//                }
//            } else {
//                switch (type) {
//
//                    case TYPE_HEAD:
//                        holderHead = (ViewHolderHead) view.getTag();
//                        break;
//
//                    case TYPE_NOMAL:
//                        holder = (ViewHolder) view.getTag();
//                        break;
//                }
//
//            }
//
//            switch (type) {
//
//                case TYPE_HEAD:
//                    holderHead.tv1.setText(String.valueOf(head.get(comment_all)));
//                    holderHead.tv2.setText(String.valueOf(head.get(comment_single)));
//                    holderHead.tv3.setText(String.valueOf(head.get(comment_color)));
//                    holderHead.tv4.setText(String.valueOf(head.get(comment_place)));
//
//                    break;
//
//                case TYPE_NOMAL:
//
//                    holder.icon.setImageDrawable(getActivity().getResources().getDrawable(Integer.valueOf(list.get(i).getIcon())));
//                    holder.name.setText(list.get(i).getUserName());
//                    holder.time.setText(list.get(i).getTime());
//                    holder.comment.setText(list.get(i).getContent());
//
//                    break;
//            }
//
//            return view;
//        }
//
//        class ViewHolder {
//            ImageView icon;
//            TextView name;
//            TextView time;
//            TextView comment;
//        }
//
//        class ViewHolderHead {
//            TextView tv1;
//            TextView tv2;
//            TextView tv3;
//            TextView tv4;
//        }
//    }


    public static SparseArray<String> getCommentHead() {
        SparseArray<String> list = new SparseArray<>();

        list.put(TabLibCommentAdapter.comment_all, "明媚动人太美，阳光照出来不忍直视，好像正能量女神");
        list.put(TabLibCommentAdapter.comment_single, "亮就一个字！在这个系列里面最橙的一个。我们东方人就算用高调的纯色唇釉，还是这种颜色最容易应用。");
        list.put(TabLibCommentAdapter.comment_color, "ysl的纯橘色，你懂得！在所有唇釉里面，非常显色好表现了。基本用来直接当颜色不成问题。");
        list.put(TabLibCommentAdapter.comment_place, "日妆显能量，晚上也ＨＯＬＤ的住场合");

        return list;
    }
}
