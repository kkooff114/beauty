package beauty.beautydemo.screens;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.edmodo.cropper.CropImageView;

import java.io.File;
import java.util.ArrayList;

import beauty.beautydemo.R;
import beauty.beautydemo.base.BeautyBaseActivity;
import beauty.beautydemo.tools.ScreenTools;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * Created by LJW on 15/7/15.
 */
public class PhotoCropActivity extends BeautyBaseActivity {

    private static final int DEALY_TIME = 300;


    @InjectView(R.id.CropImageView)
    CropImageView mCropImageView; //剪裁前的图片

    @InjectView(R.id.CropedImageView)
    ImageView mCropedImageView; //剪裁后的图片

    @InjectView(R.id.ll_toobar1)
    LinearLayout mllToobar1;
    @InjectView(R.id.ll_toobar2)
    LinearLayout mllToobar2;

    @InjectView(R.id.tv_tab_1)
    TextView mTab1;
    @InjectView(R.id.tv_tab_2)
    TextView mTab2;
    @InjectView(R.id.tv_tab_3)
    TextView mTab3;


    private Uri photoUri; //前一个界面传过来的图片

    Bitmap croppedImage, cropImage;

    private ArrayList<String> mSelectPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_photo);
        ButterKnife.inject(this);

        int width = ScreenTools.getScreenWidth(PhotoCropActivity.this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width,width);
        mCropedImageView.setLayoutParams(params);
        getPhotoUriFromPreActivity();

    }

    private void getPhotoUriFromPreActivity() {
        mSelectPath = getIntent().getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT); // 照片list
        final File file = new File(mSelectPath.get(0));
        photoUri = Uri.fromFile(file);
        cropImage = BitmapFactory.decodeFile(mSelectPath.get(0));
        mCropImageView.setImageBitmap(cropImage);
        mCropImageView.setFixedAspectRatio(true);
        mCropImageView.setAspectRatio(1, 1);
    }


    @OnClick(R.id.iv_back)
    void back(View v) {
        finish();
    }

    @OnClick(R.id.iv_take_crop)
    void takeCrop(View view) {
        croppedImage = mCropImageView.getCroppedImage();
        mCropedImageView.setImageBitmap(croppedImage);
        mCropedImageView.setVisibility(View.VISIBLE);

        recycleCropImageView();

        animateTooBar();
    }

    //滤镜
    @OnClick(R.id.tv_tab_1)
    void selectLvjing(View v) {
        mTab1.setTextColor(getResources().getColor(R.color.md_grey_700));
        mTab2.setTextColor(getResources().getColor(R.color.md_grey_500));
        mTab3.setTextColor(getResources().getColor(R.color.md_grey_500));

    }

    //文字
    @OnClick(R.id.tv_tab_2)
    void selectText(View v) {
        mTab1.setTextColor(getResources().getColor(R.color.md_grey_500));
        mTab2.setTextColor(getResources().getColor(R.color.md_grey_700));
        mTab3.setTextColor(getResources().getColor(R.color.md_grey_500));

    }

    //标签
    @OnClick(R.id.tv_tab_3)
    void selectTag(View v) {
        mTab1.setTextColor(getResources().getColor(R.color.md_grey_500));
        mTab2.setTextColor(getResources().getColor(R.color.md_grey_500));
        mTab3.setTextColor(getResources().getColor(R.color.md_grey_700));

    }

    private void animateTooBar() {
        mllToobar2.setVisibility(View.VISIBLE);
        mllToobar2.setTranslationX(mllToobar2.getWidth());

        mllToobar1.animate().translationX(-mllToobar1.getWidth()).setDuration(DEALY_TIME).start();
        mllToobar2.animate().translationX(0).setDuration(DEALY_TIME).start();
    }


    /**
     * 回收剪裁前的图片
     */
    private void recycleCropImageView() {
        mCropImageView.setVisibility(View.GONE);
    }
}
