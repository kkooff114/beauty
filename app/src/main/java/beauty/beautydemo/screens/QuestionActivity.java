package beauty.beautydemo.screens;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nineoldandroids.animation.ObjectAnimator;

import java.util.ArrayList;

import beauty.beautydemo.R;
import beauty.beautydemo.base.BaseActivity;
import beauty.beautydemo.custview.gallery.QuestionGallery;
import beauty.beautydemo.dataprovider.DataProvider;
import beauty.beautydemo.tools.CustBounceInterpolator;
import beauty.beautydemo.tools.CustovershootInterpolator;
import beauty.beautydemo.tools.ImageTools;
import beauty.beautydemo.tools.ScreenTools;

/**
 * Created by LJW on 15/4/4.
 */
public class QuestionActivity extends BaseActivity implements View.OnClickListener{

    private String TAG = "QuestionActivity";

    private static final CustovershootInterpolator overshootInterpolator = new CustovershootInterpolator(4f);
    private static final int duration1 = 1 * 1000;

    private TextView tv_question_title;
    private LinearLayout ll_question;
    private ImageView imageView1, imageView2, imageView3, imageView4, imageView5;
    private TextView tv_question_over;

    private QuestionGallery customgallery;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Point p = new Point();
        getWindowManager().getDefaultDisplay().getRealSize(p);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        DataProvider.setWindowWidth(p.x);
        DataProvider.setWindowHeight(p.y);
        DataProvider.setStatusHeight(ScreenTools.getStatusBarHeight(getApplicationContext()));
        Log.i(TAG, "x:" + p.x + ", y:" + p.y + ". x:" + p.x / dm.xdpi + ", y:" + p.y / dm.ydpi);

        setContentView(R.layout.activity_quest);
        ll_question = (LinearLayout) findViewById(R.id.ll_question);
        tv_question_title = (TextView) findViewById(R.id.tv_question_title);
        customgallery = (QuestionGallery) findViewById(R.id.customgallery);
        tv_question_over = (TextView) findViewById(R.id.tv_question_over);
        tv_question_over.setOnClickListener(this);

        question1();
    }

    private void question1() {
        ArrayList<Integer> list = new ArrayList<>();

        list.add(R.drawable.question0101);
        list.add(R.drawable.question0102);
        list.add(R.drawable.question0103);

        customgallery.setAdapter(new ImageAdapter(list));

        customgallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                customgallery.setVisibility(View.GONE);

                question2();

            }
        });

    }

    private void question2() {
        tv_question_title.setText(getResources().getString(R.string.question2));
        customgallery.setVisibility(View.VISIBLE);

        ArrayList<Integer> list = new ArrayList<>();

        list.add(R.drawable.question0201);
        list.add(R.drawable.question0202);
        list.add(R.drawable.question0203);
        list.add(R.drawable.question0204);

        customgallery.setAdapter(new ImageAdapter(list));
        customgallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                customgallery.setVisibility(View.GONE);
                question3();

            }
        });

    }

    private void question3() {
        tv_question_title.setText(getResources().getString(R.string.question3));

        String[] s = {"A. 偏蓝紫色", "B. 偏绿色"};

        showTextSelection(ll_question, s, QuestionActivity.this, new TextSelected() {
            @Override
            public void onTextSelected(int index) {

                ll_question.removeAllViews();
                question4();
            }
        });

    }

    private void question4() {
        tv_question_title.setText(getResources().getString(R.string.question4));

        String[] s = {"A. 很可怕--那让我看起来像生病了一样蜡黄", "B. 很漂亮--那给了我很好的光彩。"};

        showTextSelection(ll_question, s, QuestionActivity.this, new TextSelected() {
            @Override
            public void onTextSelected(int index) {

                ll_question.removeAllViews();
                question5();
            }
        });

    }

    private void question5() {
        tv_question_title.setText(getResources().getString(R.string.question5));

        String[] s = {"A. 很容易发红，皮肤像要烧起来一样", "B. 比较容易被晒成黄褐色"};

        showTextSelection(ll_question, s, QuestionActivity.this, new TextSelected() {
            @Override
            public void onTextSelected(int index) {

                ll_question.removeAllViews();
                question6();
            }
        });
    }

    private void question6() {
        tv_question_title.setText(getResources().getString(R.string.question6));
        ll_question.addView(customgallery);
        customgallery.setVisibility(View.VISIBLE);

        ArrayList<Integer> list = new ArrayList<>();

        list.add(R.drawable.question0601);
        list.add(R.drawable.question0602);
        list.add(R.drawable.question0603);
        list.add(R.drawable.question0604);

        customgallery.setAdapter(new ImageAdapter(list));
        customgallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                customgallery.setVisibility(View.GONE);

                question7();
            }
        });

    }

    private void question7() {
        tv_question_title.setText(getResources().getString(R.string.question7));

        String[] s = {"口红", "底妆", "眼瘾", "其他"};

        showTextSelection(ll_question, s, QuestionActivity.this, new TextSelected() {
            @Override
            public void onTextSelected(int index) {

                ll_question.removeAllViews();
                question8();
            }
        });
    }

    private void question8() {
        tv_question_title.setText(getResources().getString(R.string.question8));

        String[] s = {"设计一下眉型", "挑一下适合的色彩", "改变一成不变的妆容", "教教化妆什么技巧都好。。。", "其他"};

        showTextSelection(ll_question, s, QuestionActivity.this, new TextSelected() {
            @Override
            public void onTextSelected(int index) {

                ll_question.removeAllViews();
                startNextActivity();
            }
        });
    }

    private void startNextActivity() {
        Intent intent = new Intent(QuestionActivity.this, MainActivity.class);

        startActivity(intent);
    }


    /**
     * @param v 容器
     * @param s 选项
     * @param c 上下文
     */
    private void showTextSelection(final ViewGroup v, final String[] s, final Context c, final TextSelected selected) {

        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DataProvider.getWindowWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);
        params.leftMargin = DataProvider.getWindowWidth() * 3 / 4 + 50;
        params.topMargin = ScreenTools.dp2px(QuestionActivity.this, 15);

        Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(final Message message) {
                TextView tv = new TextView(c);
                tv.setText(s[message.what]);
                tv.setLayoutParams(params);
                tv.setSingleLine();
                tv.setTextSize(16);
                tv.setTextColor(getResources().getColor(R.color.theme_color));
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selected.onTextSelected(message.what);
                    }
                });
                v.addView(tv);

                ObjectAnimator oa = ObjectAnimator.ofFloat(tv, "translationX", -DataProvider.getWindowWidth() * 3 / 4);
                oa.setInterpolator(overshootInterpolator);
                oa.setDuration(duration1);
                oa.start();

                return false;
            }
        });
        for (int i = 0; i < s.length; i++) {
            handler.sendEmptyMessageDelayed(i, i * 1000 / 2);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.tv_question_over://跳过
                startNextActivity();
                break;
        }
    }


    public class ImageAdapter extends BaseAdapter {

        private ArrayList<Integer> imagesResIDs;

        public ImageAdapter(ArrayList<Integer> image) {
            this.imagesResIDs = image;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return imagesResIDs.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return imagesResIDs.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            ImageView imageView;
            if (convertView != null) {
                imageView = (ImageView) convertView;
            } else {
                imageView = new ImageView(QuestionActivity.this);
            }
            Bitmap bitmap = ImageTools.getImageBitmap(getResources(),
                    imagesResIDs.get(position));
            BitmapDrawable drawable = new BitmapDrawable(bitmap);
            drawable.setAntiAlias(true); // 消除锯齿
            imageView.setImageDrawable(drawable);
            Gallery.LayoutParams params = new Gallery.LayoutParams(ScreenTools.dp2px(QuestionActivity.this,180), ScreenTools.dp2px(QuestionActivity.this,240));
            imageView.setLayoutParams(params);
            return imageView;
        }
    }


    /**
     * 文字内容选择接口
     */
    interface TextSelected {
        public void onTextSelected(int index);
    }
}
