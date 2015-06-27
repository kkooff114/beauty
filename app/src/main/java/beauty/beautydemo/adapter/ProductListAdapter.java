package beauty.beautydemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import beauty.beautydemo.R;
import beauty.beautydemo.bean.LibListItem;
import beauty.beautydemo.screens.TestLibProductDetailActivity;

/**
 * Created by LJW on 15/5/16.
 */
public class ProductListAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    private Context context;
    private List<LibListItem> list;

    public ProductListAdapter(Context context, List<LibListItem> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_test_lib_card, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(context, TestLibProductDetailActivity.class);

        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(
                R.anim.in_from_right, R.anim.out_to_left);
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        private ImageView pImageView;
        private ImageView tryImageView;
        private TextView pNameCN;
        private TextView pNameEN;
        private TextView pModel;
        private TextView rate;

        public CardViewHolder(View view) {
            super(view);
        }
    }
}
