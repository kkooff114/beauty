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
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by LJW on 15/7/19.
 */
public class TextAdapter extends BeautyBaseRecyclerAdapter<TextAdapter.ViewHolder> {


    private Context mContext;
    private LayoutInflater inflater;
    private ArrayList<String> mData;

    public TextAdapter(Context context, ArrayList<String> list) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        mData = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_text, parent, false));
    }

    @Override
    public void onBindViewHolderExtend(ViewHolder holder, int position) {

        holder.mText.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.tv)
        TextView mText;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onTextSelectLinsner != null) {
                        onTextSelectLinsner.onTextSelectLinser(mText.getText().toString());
                    }
                }
            });

        }
    }

    private OnTextSelectLinsner onTextSelectLinsner;

    public void setOnTextSelectLinsner(OnTextSelectLinsner onTextSelectLinsner) {
        this.onTextSelectLinsner = onTextSelectLinsner;
    }

    public interface OnTextSelectLinsner {
        public void onTextSelectLinser(String selectString);
    }
}
