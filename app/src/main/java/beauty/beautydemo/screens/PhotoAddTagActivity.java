package beauty.beautydemo.screens;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import beauty.beautydemo.R;
import beauty.beautydemo.adapter.ImageFilterAdapter;
import beauty.beautydemo.base.BeautyBaseActivity;
import beauty.beautydemo.custview.RecyclerItemClickListener;
import beauty.beautydemo.custview.imageprocessing.FastImageProcessingPipeline;
import beauty.beautydemo.custview.imageprocessing.FastImageProcessingView;
import beauty.beautydemo.custview.imageprocessing.filter.BasicFilter;
import beauty.beautydemo.custview.imageprocessing.input.GLTextureOutputRenderer;
import beauty.beautydemo.custview.imageprocessing.input.ImageResourceInput;
import beauty.beautydemo.custview.imageprocessing.output.ScreenEndpoint;
import beauty.beautydemo.custview.phototagview.TagInfo;
import beauty.beautydemo.custview.phototagview.TagInfo.Direction;
import beauty.beautydemo.custview.phototagview.TagInfo.Type;
import beauty.beautydemo.custview.phototagview.TagView;
import beauty.beautydemo.custview.phototagview.TagView.TagViewListener;
import beauty.beautydemo.custview.phototagview.TagViewLeft;
import beauty.beautydemo.custview.phototagview.TagViewRight;
import beauty.beautydemo.custview.phototagview.TouchImageView;
import beauty.beautydemo.tools.Constants;
import beauty.beautydemo.tools.NormalUtils;
import beauty.beautydemo.tools.ScreenTools;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by LJW on 15/7/16.
 */
public class PhotoAddTagActivity extends BeautyBaseActivity implements View.OnClickListener, TagViewListener, RecyclerItemClickListener.OnItemClickListener {

    private String TAG = "AddTagActivity";
    private final static int TEXTSIZE = 12;        //Tagview的字体大小

    private RelativeLayout mImageRootLayout;
    private LinearLayout mTagLinearLayout;

    private ImageView mTagNormal;
    private ImageView mTagGeo;
    private ImageView mTagUser;

    @InjectView(R.id.at_image)
    FastImageProcessingView mImageView;

    @InjectView(R.id.wm_layout_2)
    LinearLayout mTabWaterMake;

    @InjectView(R.id.tv_tag_show)
    TextView mTagShow;

    @InjectView(R.id.rv_image_filter)
    RecyclerView mImageFilterRecyclerView;

    @InjectView(R.id.tv_tab_1)
    TextView mTabImageFilter;
    @InjectView(R.id.tv_tab_2)
    TextView mTabText;
    @InjectView(R.id.tv_tab_3)
    TextView mTabTag;

    @InjectView(R.id.wm_mark4)
    ImageView mWmMark4;
    @InjectView(R.id.wm_mark5)
    ImageView mWmMark5;
    @InjectView(R.id.wm_mark6)
    ImageView mWmMark6;

    private Uri mImageUri;            //目标图片的Uri
    private String mImagePath;        //目标图片的路径

    private Boolean isTagLayShow = false;    //标签图标区域是否显示

    private float mPointX = 0;
    private float mPointY = 0;
    private float x1;
    private float y1;

    private List<TagView> tagViews = new ArrayList<TagView>();
    private List<TagInfo> tagInfoList = new ArrayList<TagInfo>();

    private final Handler mHandler = new Handler();

    /**
     * 记录当前手指位置在屏幕上的横坐标值
     */
    private float xInScreen;

    /**
     * 记录当前手指位置在屏幕上的纵坐标值
     */
    private float yInScreen;

    /**
     * 记录手指按下时在屏幕上的横坐标的值
     */
    private float xDownInScreen;

    /**
     * 记录手指按下时在屏幕上的纵坐标的值
     */
    private float yDownInScreen;

    /**
     * 记录系统状态栏的高度
     */
    private static int statusBarHeight;

    /**
     * 记录手指按下时在小悬浮窗的View上的横坐标的值
     */
    private float xInView;

    /**
     * 记录手指按下时在小悬浮窗的View上的纵坐标的值
     */
    private float yInView;


    private int[] mMarkRes_2 = {
            R.drawable.wm_5,
            R.drawable.wm_6
    };
    private TouchImageView mWaterMarkView = null;

    private ImageFilterAdapter filterAdapter;

    private ArrayList<BasicFilter> filters;

    private FastImageProcessingPipeline pipeline;
    private ImageResourceInput input;
    private ScreenEndpoint screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tag);
        ButterKnife.inject(this);

//        ArrayList<String> mSelectPath = getIntent().getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT); // 照片list
        mImagePath = getIntent().getStringExtra(CROP_IMAGE_URI);
        mImageUri = Uri.parse(mImagePath);
        Logger.d("imagePath:" + mImagePath);
        Logger.d("mImageUri:" + mImageUri);

        initView();

    }

    private void initView() {
//		mImageView.setOnClickListener(this);
        mImageView.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int action = event.getAction();
//				Logger.w(TAG, "!!!!!!!!!!  action:" + action);
                switch (action) {
                    case MotionEvent.ACTION_DOWN:

                        break;
                    case MotionEvent.ACTION_MOVE:

                        break;
                    case MotionEvent.ACTION_UP:
                        mPointX = event.getX();
                        mPointY = event.getY();

					/*Logger.w(TAG, "!!!!!!!!!!" + String.valueOf(mPointX));
                    Logger.w(TAG, String.valueOf(mPointY)); */
                        showTagLinearLayout(mPointX, mPointY);
                        break;
                }

                return true;
            }
        });

        mImageRootLayout = (RelativeLayout) findViewById(R.id.at_image_layout);

        LayoutParams params = new LayoutParams(ScreenTools.getScreenWidth(PhotoAddTagActivity.this), ScreenTools.getScreenWidth(PhotoAddTagActivity.this));
        mImageView.setLayoutParams(params);

        //设置图片内容

        pipeline = new FastImageProcessingPipeline();
        mImageView.setPipeline(pipeline);

        input = new ImageResourceInput(mImageView, mImagePath);
        screen = new ScreenEndpoint(pipeline);

        input.addTarget(screen);

        filters = Constants.getAllFilter(PhotoAddTagActivity.this);
        for (BasicFilter filter : filters) {
            filter.addTarget(screen);
        }

        pipeline.addRootRenderer(input);
        pipeline.startRendering();
//        if (mImageUri != null) {
//            mImageView.setImageURI(mImageUri);
//        }


        mTagLinearLayout = (LinearLayout) findViewById(R.id.at_tag_layout);
        LayoutParams params2 = new LayoutParams(ScreenTools.getScreenWidth(PhotoAddTagActivity.this), ScreenTools.getScreenWidth(PhotoAddTagActivity.this));
        mTagLinearLayout.setLayoutParams(params2);

        mTagNormal = (ImageView) findViewById(R.id.at_tag_image1);
        mTagGeo = (ImageView) findViewById(R.id.at_tag_image2);
        mTagUser = (ImageView) findViewById(R.id.at_tag_image3);
        mTagNormal.setOnClickListener(this);
        mTagGeo.setOnClickListener(this);
        mTagUser.setOnClickListener(this);

    }


    @OnClick(R.id.iv_back_2)
    void back(View v) {
        saveImageAndFinish();
    }

    // 贴纸
    @OnClick(R.id.tv_tab_2)
    void tabText(View v) {
        // tab颜色
        mTabText.setTextColor(getResources().getColor(R.color.md_grey_700));
        mTabTag.setTextColor(getResources().getColor(R.color.md_grey_400));
        mTabImageFilter.setTextColor(getResources().getColor(R.color.md_grey_400));

        //tab显示内容
        mTabWaterMake.setVisibility(View.VISIBLE);
        mTagShow.setVisibility(View.GONE);
        mImageFilterRecyclerView.setVisibility(View.GONE);

        //设置贴纸是否拦截点击事件
        if (mWaterMarkView != null) {
            mWaterMarkView.isInterceptTouchEvent = true;
        }

    }

    //标签
    @OnClick(R.id.tv_tab_3)
    void tabTag(View v) {
        // tab颜色
        mTabText.setTextColor(getResources().getColor(R.color.md_grey_400));
        mTabTag.setTextColor(getResources().getColor(R.color.md_grey_700));
        mTabImageFilter.setTextColor(getResources().getColor(R.color.md_grey_400));

        //tab显示内容
        mTabWaterMake.setVisibility(View.GONE);
        mTagShow.setVisibility(View.VISIBLE);
        mImageFilterRecyclerView.setVisibility(View.GONE);

        //设置贴纸是否拦截点击事件
        if (mWaterMarkView != null) {
            mWaterMarkView.isInterceptTouchEvent = false;
        }
    }

    //滤镜
    @OnClick(R.id.tv_tab_1)
    void tabImageFilter(View v) {
        // tab颜色
        mTabText.setTextColor(getResources().getColor(R.color.md_grey_400));
        mTabTag.setTextColor(getResources().getColor(R.color.md_grey_400));
        mTabImageFilter.setTextColor(getResources().getColor(R.color.md_grey_700));

        //tab显示内容
        mTabWaterMake.setVisibility(View.GONE);
        mTagShow.setVisibility(View.GONE);
        mImageFilterRecyclerView.setVisibility(View.VISIBLE);

        //设置贴纸是否拦截点击事件
        if (mWaterMarkView != null) {
            mWaterMarkView.isInterceptTouchEvent = false;
        }

        if (filterAdapter == null) {
            setupImageFilterList();
        }
    }

    //发布
    @OnClick(R.id.iv_publish)
    void publish(View v) {
        Intent intent = new Intent(PhotoAddTagActivity.this, PublishActivity.class);
        intent.putExtra(PUBLISH_IMAGE_PATH, mImagePath);
        startActivity(intent);

    }

    private void setupImageFilterList() {
        LinearLayoutManager manager = new LinearLayoutManager(PhotoAddTagActivity.this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mImageFilterRecyclerView.setLayoutManager(manager);
        mImageFilterRecyclerView.setHasFixedSize(true);
        mImageFilterRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);

        filterAdapter = new ImageFilterAdapter(PhotoAddTagActivity.this, filters);
        mImageFilterRecyclerView.setAdapter(filterAdapter);

        mImageFilterRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(PhotoAddTagActivity.this, this));
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
//            case R.id.at_show:

//                JSONArray tagInfoArray = new JSONArray();
//                for(TagInfo info : tagInfoList){
//                    JSONObject infoJson = info.getjson();
//                    tagInfoArray.put(infoJson);
//                }
//                Intent intent = new Intent(this, ShowTagActivity.class);
//                intent.putExtra(TakePicActivity.CROP_IMAGE_URI, mImagePath);
//                intent.putExtra("tagInfoList", tagInfoArray.toString());
//                startActivity(intent);

//                break;
            case R.id.at_tag_image1:
                editTagInfo(1);
                break;
            case R.id.at_tag_image2:
                editTagInfo(2);
                break;
            case R.id.at_tag_image3:
                editTagInfo(3);
                break;

            default:
                break;
        }
    }

    private void saveImageAndFinish() {
        final Bitmap croppedImage = getLayoutBitmap(mImageRootLayout);

        mHandler.post(new Runnable() {
            @Override
            public void run() {

                saveDrawableToCache(croppedImage, mImagePath);
                croppedImage.recycle();
            }
        });

        Intent intent = new Intent();
        intent.putExtra("tag_image_path", mImagePath);
        setResult(RESULT_OK, intent);
        finish();

    }

    private void saveDrawableToCache(Bitmap bitmap, String filePath) {

        try {
            File file = new File(filePath);

            file.createNewFile();

            OutputStream outStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            outStream.flush();
            outStream.close();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    //shark_5 获取Layout的截图
    public Bitmap getLayoutBitmap(View view) {
        view.invalidate();
        //shark_5
        view.setDrawingCacheEnabled(true);
        view.measure(
                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(),
                view.getMeasuredHeight());

        view.buildDrawingCache();

        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

    /**
     * 点击图片后添加小圆点
     */
    private void addPoint(float x, float y) {
//		Bitmap photo = ((BitmapDrawable)mImageView.getDrawable()).getBitmap();
        Bitmap photo = BitmapFactory.decodeFile(mImagePath);
        Bitmap point = BitmapFactory.decodeResource(this.getResources(), R.drawable.brand_tag_point_white_bg);
        Bitmap a = createBitmap(photo, point, x, y);
//        mImageView.setImageBitmap(a);
        input.setImage(a);
        //TODO
        photo.recycle();
        point.recycle();
//		a.recycle();
//		saveMyBitmap(a);
    }

    /**
     * 点击图片后添加小圆点
     */
    private Bitmap createBitmap(Bitmap src, Bitmap point, float x, float y) {
        if (src == null) {
            return null;
        }
        int w = src.getWidth();
        int h = src.getHeight();
        Logger.w("createBitmap w:" + w);
        Logger.w("createBitmap h:" + h);
        int ww = point.getWidth();
        int wh = point.getHeight();
        Logger.w("watermark ww:" + ww);
        Logger.w("watermark wh:" + wh);

        //create the new blank bitmap
        Bitmap newb = Bitmap.createBitmap(w, h, Config.ARGB_8888);
        //创建一个新的和SRC长度宽度一样的位图
        Canvas cv = new Canvas(newb);
        //draw src into
        cv.drawBitmap(src, 0, 0, null);//在 0，0坐标开始画入src
        //draw watermark into
        cv.drawBitmap(point, (x - (ww / 2)), (y - (wh / 2)), null);//在src的右下角画入水印
        //save all clip
        cv.save(Canvas.ALL_SAVE_FLAG);//保存
        //store
        cv.restore();//存储

        point.recycle();
        return newb;
    }

    /**
     * 编辑标签信息
     */
    private void editTagInfo(int k) {
        //标签图标区域消失
        showTagLinearLayout(0, 0);
//		Toast.makeText(this, k + "", Toast.LENGTH_SHORT).show();

        TagInfo tagInfo = new TagInfo();
        tagInfo.bid = 2L;
        tagInfo.bname = "Hello PMMQ " + k;
        tagInfo.direct = getDirection(tagInfo.bname);
        tagInfo.pic_x = mPointX / ScreenTools.getScreenWidth(PhotoAddTagActivity.this);
        tagInfo.pic_y = mPointY / ScreenTools.getScreenWidth(PhotoAddTagActivity.this);
        tagInfo.type = getRandomType();
        switch (tagInfo.direct) {
            case Left:
                tagInfo.leftMargin = (int) (mPointX - ScreenTools.dpToPx(15));    //根据屏幕密度计算使动画中心在点击点，15dp是margin
                tagInfo.topMargin = (int) (mPointY - ScreenTools.dpToPx(15));
                tagInfo.rightMargin = 0;
                tagInfo.bottomMargin = 0;
                break;
            case Right:
                tagInfo.leftMargin = 0;
                tagInfo.topMargin = (int) (mPointY - ScreenTools.dpToPx(15));
                tagInfo.rightMargin = ScreenTools.getScreenWidth(PhotoAddTagActivity.this) - (int) mPointX - ScreenTools.dpToPx(15);
                tagInfo.bottomMargin = 0;
                break;
        }
        Logger.w("11 tagInfo.pic_x:" + tagInfo.pic_x);
        Logger.w("tagInfo.pic_y:" + tagInfo.pic_y);
        addTagInfo(tagInfo);

        Logger.w("-@@@@------- tagInfo.leftMargin:" + tagInfo.leftMargin);
        Logger.w("tagInfo.topMargin:" + tagInfo.topMargin);
        Logger.w("tagInfo.rightMargin:" + tagInfo.rightMargin);
        Logger.w("tagInfo.bottomMargin:" + tagInfo.bottomMargin);
    }

    /**
     * 添加标签
     */
    private void addTagInfo(final TagInfo tagInfo) {
        TagView tagView = null;
        switch (tagInfo.direct) {
            case Left:
                tagView = new TagViewLeft(this, null);
                break;
            case Right:
                tagView = new TagViewRight(this, null);
                break;
        }
        tagView.setData(tagInfo);
        tagView.setTagViewListener(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(tagInfo.leftMargin, tagInfo.topMargin, tagInfo.rightMargin, tagInfo.bottomMargin);
        mImageRootLayout.addView(tagView, params);
        //添加TagView的移动事件
        tagView.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // 手指按下时记录必要数据,纵坐标的值都需要减去状态栏高度
                        xInView = event.getX();
                        yInView = event.getY();
                        xDownInScreen = event.getRawX();
                        yDownInScreen = event.getRawY() - getStatusBarHeight();
                        xInScreen = event.getRawX();
                        yInScreen = event.getRawY() - getStatusBarHeight();
                        Logger.w("statusBarHeight:" + statusBarHeight);

                        x1 = event.getRawX();
                        y1 = event.getRawY() - getStatusBarHeight();

                        break;
                    case MotionEvent.ACTION_MOVE:

                        xInScreen = event.getRawX();
                        yInScreen = event.getRawY() - getStatusBarHeight();
                        //手指超出边界时，使y值等于边界值，由于此处view等于屏幕宽度，所以不考虑x值
                        if ((yInScreen - yInView) < 0) {
                            yInScreen = yInView;
                            Logger.w("yInScreen:" + yInScreen);
                        } else if ((yInScreen + v.getHeight() - yInView) > ScreenTools.getScreenWidth(PhotoAddTagActivity.this)) {
                            yInScreen = ScreenTools.getScreenWidth(PhotoAddTagActivity.this) - v.getHeight() + yInView;
                            Logger.w("yInScreen:" + yInScreen);
                        }

                        updateTagViewPosition(v, tagInfo);

                        xDownInScreen = xInScreen;
                        yDownInScreen = yInScreen;

                        break;
                    case MotionEvent.ACTION_UP:
                        Logger.w("图片各个角Left：" + v.getLeft() + "-Right：" + v.getRight() + "-Top：" + v.getTop() + "-Bottom：" + v.getBottom());

                        switch (tagInfo.direct) {
                            case Left:
                                tagInfo.pic_x = (v.getLeft() + ScreenTools.dpToPx(15)) / ScreenTools.getScreenWidth(PhotoAddTagActivity.this);
                                tagInfo.pic_y = (v.getTop() + ScreenTools.dpToPx(15)) / ScreenTools.getScreenWidth(PhotoAddTagActivity.this);
                                break;
                            case Right:
                                tagInfo.pic_x = (v.getRight() - ScreenTools.dpToPx(15)) / ScreenTools.getScreenWidth(PhotoAddTagActivity.this);
                                tagInfo.pic_y = (v.getTop() + ScreenTools.dpToPx(15)) / ScreenTools.getScreenWidth(PhotoAddTagActivity.this);
                                break;
                        }

                        Logger.w("22 tagInfo.pic_x:" + tagInfo.pic_x);
                        Logger.w("tagInfo.pic_y:" + tagInfo.pic_y);
                        if (Math.abs(x1 - xInScreen) < 5 && Math.abs(y1 - yInScreen) < 5) {
                            return false;
                        } else {
                            return true;
                        }

//					break;
                }

                return false;
            }
        });
        tagInfoList.add(tagInfo);
        tagViews.add(tagView);
    }

    /**
     * 移动TagView，更新位置
     */
    private void updateTagViewPosition(View v, TagInfo tagInfo) {
        //计算位移
        float xMove = xInScreen - xDownInScreen;
        float yMove = yInScreen - yDownInScreen;

        //获取View的宽度，为什么不用v.getWidth();?因为Right方向的布局有问题，是从屏幕左边缘开始，不符合需求
        int viewWidth = (int) getTagViewWidth(tagInfo.bname);

        switch (tagInfo.direct) {
            case Left:
                tagInfo.leftMargin += xMove;
                tagInfo.topMargin += yMove;
                //边界计算
                if (tagInfo.leftMargin < 0) {
                    tagInfo.leftMargin = 0;
                } else if ((tagInfo.leftMargin + viewWidth) > ScreenTools.getScreenWidth(PhotoAddTagActivity.this)) {
                    tagInfo.leftMargin = ScreenTools.getScreenWidth(PhotoAddTagActivity.this) - viewWidth;
                }
                break;
            case Right:
                tagInfo.topMargin += yMove;
                tagInfo.rightMargin -= xMove;
                Logger.w("1111111 tagInfo.rightMargin:" + tagInfo.rightMargin);
                //边界计算
                if (tagInfo.rightMargin < 0) {
                    tagInfo.rightMargin = 0;
                    Logger.w("222222222 tagInfo.rightMargin:" + tagInfo.rightMargin);
                } else if ((tagInfo.rightMargin + viewWidth) > ScreenTools.getScreenWidth(PhotoAddTagActivity.this)) {
                    tagInfo.rightMargin = ScreenTools.getScreenWidth(PhotoAddTagActivity.this) - viewWidth;
                    Logger.w("333333333 tagInfo.rightMargin:" + tagInfo.rightMargin);
                }
                break;
        }
        //边界计算
        if (tagInfo.topMargin < 0) {
            tagInfo.topMargin = 0;
        } else if ((tagInfo.topMargin + v.getHeight()) > ScreenTools.getScreenWidth(PhotoAddTagActivity.this)) {
            tagInfo.topMargin = ScreenTools.getScreenWidth(PhotoAddTagActivity.this) - v.getHeight();
        }
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(tagInfo.leftMargin, tagInfo.topMargin, tagInfo.rightMargin, tagInfo.bottomMargin);
        v.setLayoutParams(params);
    }

    /*
     * TagView的点击事件
     * (non-Javadoc)
     * @see com.pmmq.pmmqproject.ui.tag.TagView.TagViewListener#onTagViewClicked(android.view.View, com.pmmq.pmmqproject.ui.tag.TagInfo)
     */
    @Override
    public void onTagViewClicked(final View view, final TagInfo info) {
        Logger.w("onTagViewClicked");
        new AlertDialog.Builder(this).setTitle(getResources().getString(R.string.at_want_delete)).setPositiveButton(getResources().getString(R.string.at_ok),
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {

                        mImageRootLayout.removeView(view);
                        tagViews.remove(view);
                        tagInfoList.remove(info);
                        Logger.w("----> tagViews.size():" + tagViews.size());
                        Logger.w("tagInfoList.size():" + tagInfoList.size());
                    }
                }).setNegativeButton(getResources().getString(R.string.at_cancel), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int whichButton) {


            }
        }).show();
    }

    /**
     * 获取TagView的宽度
     */
    private float getTagViewWidth(String str) {
        float viewWidth = NormalUtils.GetTextWidth(str, ScreenTools.dpToPx(TEXTSIZE));
        Logger.w("getDirection viewSize:" + viewWidth);
        viewWidth += (ScreenTools.dpToPx(46));            //46dp是TagView除去text部分的宽度，可以从布局中查看
        Logger.w("viewWidth:" + viewWidth);
        return viewWidth;
    }

    /**
     * 获取TagView的高度
     */
    private float getTagViewHeight(String str) {
        float viewHeight = (ScreenTools.dpToPx(30));    //30dp是TagView的高度，可以从布局中查看
        Logger.w("viewHeight:" + viewHeight);
        return viewHeight;
    }

    /**
     * 获取TagView的方向
     */
    private Direction getDirection(String str) {
        float showSize = NormalUtils.GetTextWidth(str, ScreenTools.dpToPx(TEXTSIZE));
        showSize += (ScreenTools.dpToPx(32));
        Logger.w("getDirection showSize:" + showSize);
        if ((mPointX + showSize) > ScreenTools.getScreenWidth(PhotoAddTagActivity.this)) {
            return Direction.Right;
        } else {
            return Direction.Left;
        }
    }

    private Type getRandomType() {
        Random random = new Random();
        int ran = random.nextInt(Type.size());
        if (0 == ran) {
            return Type.Undefined;
        } else if (1 == ran) {
            return Type.Exists;
        } else if (2 == ran) {
            return Type.CustomPoint;
        } else {
            return Type.OfficalPoint;
        }
    }

    /**
     * 显示与隐藏标签图标区域
     */
    private void showTagLinearLayout(float x, float y) {
        if (isTagLayShow) {
//            mImageView.setImageURI(mImageUri);
            input.setImage(mImagePath);
            //TODO
//            mTagLinearLayout.setVisibility(View.GONE);
            ObjectAnimator tago1 = ObjectAnimator.ofFloat(mTagNormal, "translationY", -(ScreenTools.getScreenWidth(PhotoAddTagActivity.this) / 3) * 2);
            tago1.setDuration(500);
            tago1.setInterpolator(Anticipate);
            tago1.setStartDelay(100);
            ObjectAnimator tago2 = ObjectAnimator.ofFloat(mTagGeo, "translationY", -(ScreenTools.getScreenWidth(PhotoAddTagActivity.this) / 3) * 2);
            tago2.setDuration(500);
            tago2.setInterpolator(Anticipate);
            tago2.setStartDelay(50);
            ObjectAnimator tago3 = ObjectAnimator.ofFloat(mTagUser, "translationY", -(ScreenTools.getScreenWidth(PhotoAddTagActivity.this) / 3) * 2);
            tago3.setDuration(500);
            tago3.setInterpolator(Anticipate);

            AnimatorSet set = new AnimatorSet();
            set.playTogether(tago1);
            set.playTogether(tago2);
            set.playTogether(tago3);
            set.start();

            isTagLayShow = false;
            //设置TagView的可以移动
            setTagViewEnable(true);
        } else {
            addPoint(x, y);
            mTagLinearLayout.setVisibility(View.VISIBLE);
//            TranslateAnimation anim = new TranslateAnimation(0, 0, -(ScreenTools.getScreenWidth(PhotoAddTagActivity.this) / 2), 0);
//            anim.setInterpolator(new OvershootInterpolator());
//            anim.setDuration(500);
//            mTagNormal.setAnimation(anim);
//            mTagGeo.setAnimation(anim);
//            mTagUser.setAnimation(anim);
//            anim.startNow();
            mTagNormal.setTranslationY(-(ScreenTools.getScreenWidth(PhotoAddTagActivity.this) / 3) * 2);
            mTagGeo.setTranslationY(-(ScreenTools.getScreenWidth(PhotoAddTagActivity.this) / 3) * 2);
            mTagUser.setTranslationY(-(ScreenTools.getScreenWidth(PhotoAddTagActivity.this) / 3) * 2);

            ObjectAnimator tago1 = ObjectAnimator.ofFloat(mTagNormal, "translationY", 0);
            tago1.setDuration(500);
            tago1.setInterpolator(OVERSHOOT);
            ObjectAnimator tago2 = ObjectAnimator.ofFloat(mTagGeo, "translationY", 0);
            tago2.setDuration(500);
            tago2.setInterpolator(OVERSHOOT);
            tago2.setStartDelay(50);
            ObjectAnimator tago3 = ObjectAnimator.ofFloat(mTagUser, "translationY", 0);
            tago3.setDuration(500);
            tago3.setInterpolator(OVERSHOOT);
            tago3.setStartDelay(100);

            AnimatorSet set = new AnimatorSet();
            set.playTogether(tago1);
            set.playTogether(tago2);
            set.playTogether(tago3);
            set.start();

            isTagLayShow = true;
            //取消TagView的移动
            setTagViewEnable(false);
        }
    }

    /**
     * 设置TagView是否可以点击
     *
     * @param enabled
     */
    private void setTagViewEnable(Boolean enabled) {
        for (TagView view : tagViews) {
            view.setEnabled(enabled);
        }
    }

    /**
     * 用于获取状态栏的高度。
     *
     * @return 返回状态栏高度的像素值。
     */
    private int getStatusBarHeight() {
        if (statusBarHeight == 0) {
            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object o = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = (Integer) field.get(o);
                statusBarHeight = getResources().getDimensionPixelSize(x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusBarHeight;
    }


    //水印相关start

    @OnClick(R.id.wm_mark4)
    void waterMaik4(View v) {
        addWaterMark_2(10);
    }

    @OnClick(R.id.wm_mark5)
    void waterMaik5(View v) {
        addWaterMark_2(0);
    }

    @OnClick(R.id.wm_mark6)
    void waterMaik6(View v) {
        addWaterMark_2(1);
    }

    //添加水印贴纸，可以移动、缩放、旋转
    private void addWaterMark_2(int i) {

        if (i > mMarkRes_2.length) {    //空  ，不添加水印
            if (mWaterMarkView != null) {
                mImageRootLayout.removeView(mWaterMarkView);
                mWaterMarkView = null;
            }
        } else {
            if (mWaterMarkView != null) {
                mImageRootLayout.removeView(mWaterMarkView);
            }
            //设置图片显示宽高为屏幕宽度
            LayoutParams params = new LayoutParams(ScreenTools.getScreenWidth(PhotoAddTagActivity.this), ScreenTools.getScreenWidth(PhotoAddTagActivity.this));

            Bitmap watermark = BitmapFactory.decodeResource(getResources(), mMarkRes_2[i]);

            int ww = watermark.getWidth();
            int wh = watermark.getHeight();
            Logger.w(TAG, "watermark ww:" + ww);
            Logger.w(TAG, "watermark wh:" + wh);

            //如果水印图片太大则压缩
            if (ww > ScreenTools.getScreenWidth(PhotoAddTagActivity.this) || wh > ScreenTools.getScreenWidth(PhotoAddTagActivity.this)) {
                // 缩放图片的尺寸
                float scaleWidth = (float) ScreenTools.getScreenWidth(PhotoAddTagActivity.this) / ww;
                float scaleHeight = (float) ScreenTools.getScreenWidth(PhotoAddTagActivity.this) / wh;
                float scale = Math.min(scaleWidth, scaleHeight) * (float) 0.8;    //屏幕宽度的80%
                Matrix matrix = new Matrix();
                matrix.postScale(scale, scale);
                // 产生缩放后的Bitmap对象
                watermark = Bitmap.createBitmap(watermark, 0, 0, ww, wh, matrix, false);
            }

            TouchImageView touchImageView = new TouchImageView(this, watermark);
            mImageRootLayout.addView(touchImageView, params);
            mWaterMarkView = touchImageView;
        }
    }

    //水印相关end


    // 滤镜相关start
    int curFilter = -1;

    @Override
    public void onItemClick(View view, int position) {
        pipeline.pauseRendering();

        if (curFilter != -1) {
            input.removeTarget(filters.get(curFilter));
            pipeline.addFilterToDestroy(filters.get(curFilter));
        }

        curFilter = position;

        input.addTarget(filters.get(curFilter));

        pipeline.startRendering();
        mImageView.requestRender();
    }

    // 滤镜相关end


}