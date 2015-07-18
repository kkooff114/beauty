package beauty.beautydemo.screens;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;

import beauty.beautydemo.R;
import beauty.beautydemo.base.BeautyBaseActivity;
import butterknife.ButterKnife;
import butterknife.InjectView;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * Created by LJW on 15/7/15.
 */
public class PhotoEditActivity extends BeautyBaseActivity {

    @InjectView(R.id.iv_resultView)
    ImageView resultView;

    private Uri photoUri; //前一个界面传过来的图片
    private Uri destination;// 裁剪完后的图片
    private int photoSize;

    private ArrayList<String> mSelectPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_photo);
        ButterKnife.inject(this);

        mSelectPath = getIntent().getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT); // 照片list
        final File file = new File(mSelectPath.get(0));
        photoUri = Uri.fromFile(file);
        beginCrop(photoUri);
    }

    private void beginCrop(Uri source) {



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }


}
