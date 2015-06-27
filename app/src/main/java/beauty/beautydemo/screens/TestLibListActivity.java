package beauty.beautydemo.screens;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.github.alexkolpa.fabtoolbar.FabToolbar;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.github.ksoichiro.android.observablescrollview.Scrollable;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import beauty.beautydemo.R;
import beauty.beautydemo.base.ObservableBaseActivity;
import beauty.beautydemo.bean.KeyValue;
import beauty.beautydemo.bean.LibListItem;
import beauty.beautydemo.custview.expandtabview.ExpandTabView;
import beauty.beautydemo.custview.expandtabview.SubView;
import beauty.beautydemo.custview.expandtabview.SubView2Level;
import beauty.beautydemo.custview.reveal.revealanimator.RevealMiddleware;
import beauty.beautydemo.tools.Constants;
import beauty.beautydemo.tools.Options;

//import android.support.v7.widget.DefaultItemAnimator;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;

/**
 * Created by LJW on 15/4/8.
 */
public class TestLibListActivity extends ObservableBaseActivity implements View.OnClickListener {

    private ExpandTabView expandtab_view;
    private ArrayList<View> mViewArray = new ArrayList<View>();
    private SubView2Level brandSubView;
    private SubView redRateSubView, userRateSubView, priceSubView;
    private ObservableRecyclerView list;
    private TextView back;
    private LinearLayout ll_lib_list_scroll_head;
    private FabToolbar fabToolbar;

    protected ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        options = Options.getListOptions();

        initView();
        initDate();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_text_lib_list;
    }

    @Override
    protected LinearLayout getHeaderLayout() {
        return ll_lib_list_scroll_head;
    }

    @Override
    protected Scrollable getScrollableLayout() {
        return list;
    }


    private void initView() {
        expandtab_view = (ExpandTabView) findViewById(R.id.expandtab_view);
        list = (ObservableRecyclerView) findViewById(R.id.list);
        back = (TextView) findViewById(R.id.back);
        back.setOnClickListener(this);

        ll_lib_list_scroll_head = (LinearLayout) findViewById(R.id.ll_lib_list_scroll_head);

        fabToolbar = ((FabToolbar) findViewById(R.id.fab_toolbar));
        fabToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fabToolbar.hide();
            }
        });


        brandSubView = new SubView2Level(TestLibListActivity.this);
        redRateSubView = new SubView(TestLibListActivity.this);
        userRateSubView = new SubView(TestLibListActivity.this);
        priceSubView = new SubView(TestLibListActivity.this);

//        brandSubView.setDate(getFiltTypeData());
        redRateSubView.setDate(getFiltDeviceData());
        userRateSubView.setDate(getFiltStatusData());
        priceSubView.setDate(getTimeData());

        mViewArray.add(brandSubView);
        mViewArray.add(redRateSubView);
        mViewArray.add(userRateSubView);
        mViewArray.add(priceSubView);

        ArrayList<String> mTextArray = new ArrayList<String>();
        mTextArray.add("品牌");
        mTextArray.add("红总分");
        mTextArray.add("用户总分");
        mTextArray.add("价格");
        expandtab_view.setValue(mTextArray, mViewArray);

        brandSubView.setOnSelectListener(new SubView2Level.OnSelectListener() {

            @Override
            public void getValue(String showText) {
                onRefresh(brandSubView, showText);
            }


        });
    }

    private void onRefresh(View view, String showText) {

        expandtab_view.onPressBack();
        int position = getPositon(view);
        if (position >= 0 && !expandtab_view.getTitle(position).equals(showText)) {
            expandtab_view.setTitle(showText, position);
        }
        Toast.makeText(TestLibListActivity.this, showText, Toast.LENGTH_SHORT).show();

    }

    private int getPositon(View tView) {
        for (int i = 0; i < mViewArray.size(); i++) {
            if (mViewArray.get(i) == tView) {
                return i;
            }
        }
        return -1;
    }

    private void initDate() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // 设置LinearLayoutManager
        list.setLayoutManager(layoutManager);
        // 设置ItemAnimator
        list.setItemAnimator(new DefaultItemAnimator());
        // 设置固定大小
        list.setHasFixedSize(true);
//        list.addItemDecoration(new DividerItemDecoration(this, layoutManager
//                .getOrientation()));


        new initTask().execute();

    }


    class initTask extends AsyncTask<String, String, ArrayList<LibListItem>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected ArrayList<LibListItem> doInBackground(String... strings) {

            return Constants.getTestLibList();
        }

        @Override
        protected void onPostExecute(ArrayList<LibListItem> s) {
            super.onPostExecute(s);

            list.setAdapter(new ListAdapter(TestLibListActivity.this, s));

            fabToolbar.setColor(getResources().getColor(R.color.theme_color));
            fabToolbar.attachToRecyclerView(list);

        }
    }

    class ListAdapter extends ObservableRecyclerView.Adapter<ListAdapter.ViewHolder> {

        private LayoutInflater inflater;
        private ArrayList<LibListItem> items;

        public ListAdapter(Context context, ArrayList<LibListItem> items) {
            this.inflater = LayoutInflater.from(context);
            this.items = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(inflater.inflate(R.layout.item_test_lib_card, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
//            imageLoader.displayImage(items.get(i).getProductImage(), holder.pImageView, options);
//            imageLoader.displayImage(items.get(i).getTryImage(), holder.tryImageView, options);
            holder.pImageView.setImageDrawable(getResources().getDrawable(Integer.valueOf(items.get(position).getProductImage())));
            holder.tryImageView.setImageDrawable(getResources().getDrawable(Integer.valueOf(items.get(position).getTryImage())));
            holder.pNameCN.setText(items.get(position).getProductNameCN());
            holder.pNameEN.setText(items.get(position).getProductNameEN());
            holder.pModel.setText(items.get(position).getProductModel());
            holder.tv_one_rate.setText(items.get(position).getOneRate());

            holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);

        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            public ImageView pImageView;
            public ImageView tryImageView;
            public TextView pNameCN;
            public TextView pNameEN;
            public TextView pModel;
            public TextView rate;
            public SwipeLayout swipeLayout;
            public ImageView magnifier;
            public ImageView star;
            public android.support.v7.widget.CardView card_view;
            public TextView tv_one_rate;

            public ViewHolder(View view) {
                super(view);

                pImageView = (ImageView) view.findViewById(R.id.iv_a);
                tryImageView = (ImageView) view.findViewById(R.id.iv_b1);
                pNameCN = (TextView) view.findViewById(R.id.tv_product_name_cn);
                pNameEN = (TextView) view.findViewById(R.id.tv_product_name_en);
                pModel = (TextView) view.findViewById(R.id.tv_product_model);
                swipeLayout = (SwipeLayout) view.findViewById(R.id.swipe);
                magnifier = (ImageView) view.findViewById(R.id.magnifier);
                star = (ImageView) view.findViewById(R.id.star);
                card_view = (android.support.v7.widget.CardView) view.findViewById(R.id.card_view);
                tv_one_rate = (TextView) view.findViewById(R.id.tv_one_rate);

                card_view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        RevealMiddleware middleware = RevealMiddleware.getInstance();
                        middleware.prepare(TestLibListActivity.this, centerX, centerY);
                        Intent intent = new Intent(TestLibListActivity.this, TestLibProductDetailMaterialTab2Activity.class);

                        startActivity(intent);
                        overridePendingTransition(
                                0, 0);
                    }
                });

                /**
                 * 商品比较
                 */
                magnifier.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(TestLibListActivity.this, "manifier", Toast.LENGTH_SHORT).show();
                    }
                });

                /**
                 * 商品收藏
                 */
                star.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(TestLibListActivity.this, "star", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    public float centerX = 0, centerY = 0;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {


        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                centerX = ev.getX();
                centerY = ev.getY();
                break;

        }

        return super.dispatchTouchEvent(ev);
    }


    private ArrayList<KeyValue> getFiltTypeData() {
        ArrayList<KeyValue> keyValues = new ArrayList<KeyValue>(3);
        KeyValue kv1 = new KeyValue("", "圣罗兰");
        KeyValue kv2 = new KeyValue("", "迪奥");
        KeyValue kv3 = new KeyValue("", "娇兰");
        keyValues.add(kv1);
        keyValues.add(kv2);
        keyValues.add(kv3);
        return keyValues;
    }

    private ArrayList<KeyValue> getFiltDeviceData() {
        ArrayList<KeyValue> keyValues = new ArrayList<KeyValue>(3);
        KeyValue kv1 = new KeyValue("", "橘调");
        KeyValue kv2 = new KeyValue("", "正红");
        keyValues.add(kv1);
        keyValues.add(kv2);
        return keyValues;
    }

    private ArrayList<KeyValue> getFiltStatusData() {
        ArrayList<KeyValue> keyValues = new ArrayList<KeyValue>(3);
        KeyValue kv1 = new KeyValue("", "主管审批中");
        KeyValue kv2 = new KeyValue("", "主管拒绝");
        KeyValue kv3 = new KeyValue("", "主管驳回");
        KeyValue kv4 = new KeyValue("", "OA审批中");
        KeyValue kv5 = new KeyValue("", "OA通过");
        KeyValue kv6 = new KeyValue("", "OA驳回");
        KeyValue kv7 = new KeyValue("", "OA作废");
        KeyValue kv8 = new KeyValue("", "已完成");
        keyValues.add(kv1);
        keyValues.add(kv2);
        keyValues.add(kv3);
        keyValues.add(kv4);
        keyValues.add(kv5);
        keyValues.add(kv6);
        keyValues.add(kv7);
        keyValues.add(kv8);

        return keyValues;
    }

    private ArrayList<KeyValue> getTimeData() {
        ArrayList<KeyValue> keyValues = new ArrayList<KeyValue>(3);
        KeyValue kv1 = new KeyValue("", "按时间升序");
        KeyValue kv2 = new KeyValue("", "按时间降序");
        keyValues.add(kv1);
        keyValues.add(kv2);
        return keyValues;
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.back://返回
                finish();
                break;
        }

    }
}
