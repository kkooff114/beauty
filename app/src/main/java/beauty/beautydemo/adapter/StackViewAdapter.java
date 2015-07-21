package beauty.beautydemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andtinder.model.CardModel;
import com.andtinder.view.CardStackAdapter;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

import java.util.List;

import beauty.beautydemo.R;
import beauty.beautydemo.entity.NewLookCardModel;
import beauty.beautydemo.tools.Options;

public class StackViewAdapter extends CardStackAdapter {

    private Context mContext;
    private List<NewLookCardModel> mPhotos;

    protected ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;


    public StackViewAdapter(Context context, List<NewLookCardModel> photos) {
        super(context);
        mContext = context;
        mPhotos = photos;
        options = Options.getListOptions();

        for (NewLookCardModel cardModel : photos) {
            super.add(cardModel);
        }
    }


    @Override
    protected View getCardView(int i, CardModel cardModel, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.std_card_inner_cust, viewGroup, false);
            assert view != null;
        }

        ((ImageView) view.findViewById(R.id.image)).setImageDrawable(cardModel.getCardImageDrawable());
//        ((TextView) view.findViewById(R.id.title)).setText(cardModel.getTitle());
        ((TextView) view.findViewById(R.id.description)).setText(cardModel.getDescription());

        view.findViewById(R.id.image_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "试妆", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.image_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "收藏", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}