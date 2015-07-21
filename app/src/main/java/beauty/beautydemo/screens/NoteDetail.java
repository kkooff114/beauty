package beauty.beautydemo.screens;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import beauty.beautydemo.R;
import beauty.beautydemo.base.BeautyBaseActivity;
import beauty.beautydemo.tools.Options;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by LJW on 15/7/18.
 */
public class NoteDetail extends BeautyBaseActivity {

    public static final String IMAGE = "image";

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.iv)
    ImageView mImageView;

    protected ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        ButterKnife.inject(this);

        setupToolbar();
        options = Options.getListOptions();

        String image = getIntent().getStringExtra(IMAGE);

//        imageLoader.displayImage(image, mImageView, options);
        mImageView.setImageResource(R.drawable.newlook1);
    }


    private void setupToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_keyboard_arrow_left_white_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitSuper();
            }
        });
    }
}
