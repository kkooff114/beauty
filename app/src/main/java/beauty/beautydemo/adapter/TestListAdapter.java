package beauty.beautydemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;

import java.util.ArrayList;

import beauty.beautydemo.R;
import beauty.beautydemo.base.BeautyBaseRecyclerAdapter;
import beauty.beautydemo.bean.LibListItem;
import beauty.beautydemo.screens.TestLibProductDetailMaterialTab2Activity;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by LJW on 15/7/18.
 */
public class TestListAdapter extends BeautyBaseRecyclerAdapter<TestListAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private ArrayList<LibListItem> mData;

    public TestListAdapter(Context context, ArrayList<LibListItem> items) {
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);
        this.mData = items;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_testlist, parent, false));
    }

    @Override
    public void onBindViewHolderExtend(ViewHolder holder, int position) {

        holder.pImageView.setImageDrawable(mContext.getResources().getDrawable(Integer.valueOf(mData.get(position).getProductImage())));
        holder.tryImageView.setImageDrawable(mContext.getResources().getDrawable(Integer.valueOf(mData.get(position).getTryImage())));
        holder.pNameCN.setText(mData.get(position).getProductNameCN());
        holder.pNameEN.setText(mData.get(position).getProductNameEN());
        holder.pModel.setText(mData.get(position).getProductModel());
        holder.tv_one_rate.setText(mData.get(position).getOneRate());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        @InjectView(R.id.iv_a)
        ImageView pImageView;
        @InjectView(R.id.iv_b1)
        ImageView tryImageView;
        @InjectView(R.id.tv_product_name_cn)
        TextView pNameCN;
        @InjectView(R.id.tv_product_name_en)
        TextView pNameEN;
        @InjectView(R.id.tv_product_model)
        TextView pModel;
        @InjectView(R.id.tv_one_rate)
        TextView tv_one_rate;
        @InjectView(R.id.iv_compare)
        ImageView mCompare;
        @InjectView(R.id.iv_star)
        ImageView mStar;

        @InjectView(R.id.rl)
        RelativeLayout mrlClick;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);

            mrlClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, TestLibProductDetailMaterialTab2Activity.class);
                    mContext.startActivity(intent);
                }
            });

            mCompare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "选择产品加入比较", Toast.LENGTH_SHORT).show();
                }
            });
            mStar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "收藏该产品", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
