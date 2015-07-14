package beauty.beautydemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by LJW on 15/7/4.
 */
public class NewLookStackViewAdapter extends RecyclerView.Adapter<NewLookStackViewAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<String> mUrl;
    private LayoutInflater inflater;

    public NewLookStackViewAdapter(Context context, ArrayList<String> url){
        this.mContext = context;
        this.mUrl = url;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mUrl.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
