package beauty.beautydemo.custview.sticknav;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;

import java.util.ArrayList;

import beauty.beautydemo.R;
import beauty.beautydemo.bean.ShopProduct;
import beauty.beautydemo.tools.Constants;

/**
 * Created by LJW on 15/4/10.
 */
public class TabLibShop extends Fragment {

    public static final String TITLE = "title";
    private String mTitle = "Defaut Value";

    private RecyclerView rv_shop;


    public static TabLibShop newInstance(String title) {
        TabLibShop tabFragment = new TabLibShop();
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
        View view = inflater.inflate(R.layout.fragment_tab_lib_shop, container, false);


        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_shop = (RecyclerView) view.findViewById(R.id.rv_shop);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv_shop.setLayoutManager(layoutManager);

        // 设置固定大小
        rv_shop.setHasFixedSize(true);

        rv_shop.setAdapter(new RecyclerViewMaterialAdapter());
        MaterialViewPagerHelper.registerRecyclerView(getActivity(), rv_shop, null);
    }

    class RecyclerViewMaterialAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        static final int TYPE_PLACEHOLDER = Integer.MIN_VALUE;
        private static final int TYPE_GRID = 10;

        //the size taken by the header
        private int mPlaceholderSize = 1;


        public RecyclerViewMaterialAdapter() {

        }

        @Override
        public int getItemViewType(int position) {
            if (position < mPlaceholderSize)
                return TYPE_PLACEHOLDER;
            else
                return TYPE_GRID;
        }


        @Override
        public int getItemCount() {
            return 1 + mPlaceholderSize;
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = null;

            switch (viewType) {
                case TYPE_PLACEHOLDER: {
                    view = LayoutInflater.from(parent.getContext())
                            .inflate(com.github.florent37.materialviewpager.R.layout.material_view_pager_placeholder, parent, false);
                    return new RecyclerView.ViewHolder(view) {
                    };
                }
                default:
                    return new ShopViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_gridview, parent, false));
            }
        }


        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            switch (getItemViewType(position)) {
                case TYPE_PLACEHOLDER:
                    break;
                default:
                    ((ShopViewHolder) holder).gv_lib_shop.setAdapter(new GridViewAdapter(getActivity(), Constants.getShopList()));
                    break;
            }
        }

        class ShopViewHolder extends RecyclerView.ViewHolder {

            GridViewForScrollView gv_lib_shop;

            public ShopViewHolder(View itemView) {
                super(itemView);
                gv_lib_shop = (GridViewForScrollView) itemView.findViewById(R.id.gv_lib_shop);
            }
        }
    }

    class GridViewAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private ArrayList<ShopProduct> list;

        public GridViewAdapter(Context context, ArrayList<ShopProduct> list) {
            inflater = LayoutInflater.from(context);
            this.list = list;
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

                view = inflater.inflate(R.layout.item_gridview_shop, null);
                holder = new ViewHolder();
                holder.iv_lib_shop = (ImageView) view.findViewById(R.id.iv_lib_shop);
                holder.tv_lib_shop_name = (TextView) view.findViewById(R.id.tv_lib_shop_name);
                holder.tv_lib_shop_price = (TextView) view.findViewById(R.id.tv_lib_shop_price);
                holder.tv_lib_shop_look_num = (TextView) view.findViewById(R.id.tv_lib_shop_look_num);


                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            holder.iv_lib_shop.setImageDrawable(getActivity().getResources().getDrawable(Integer.valueOf(list.get(i).getIcon())));
            holder.tv_lib_shop_name.setText(list.get(i).getName());
            holder.tv_lib_shop_price.setText(list.get(i).getPrice());
            holder.tv_lib_shop_look_num.setText(list.get(i).getLookNum());

            return view;
        }

        class ViewHolder {
            ImageView iv_lib_shop;
            TextView tv_lib_shop_name;
            TextView tv_lib_shop_price;
            TextView tv_lib_shop_look_num;
        }
    }
}
