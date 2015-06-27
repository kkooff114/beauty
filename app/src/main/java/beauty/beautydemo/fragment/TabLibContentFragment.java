package beauty.beautydemo.fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Highlight;
import com.github.mikephil.charting.utils.LargeValueFormatter;

import java.util.ArrayList;

import beauty.beautydemo.R;
import beauty.beautydemo.custview.chart.MyMarkerView;
import beauty.beautydemo.tools.Constants;

/**
 * Created by LJW on 15/4/10.
 */
public class TabLibContentFragment extends Fragment implements OnChartValueSelectedListener {

    public static final String TITLE = "title";
    private String mTitle = "Defaut Value";
    private GridView gv_lib_try, gv_lib_color_board;
    private BarChart mChart;
    private Typeface tf;

    private TextView tv_one_rate_single;
    private TextView tv_one_rate_all;
    private TextView tv_comment_color;
    private TextView tv_comment_place;


    public static TabLibContentFragment newInstance(String title) {
        TabLibContentFragment tabFragment = new TabLibContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_lib_content, container, false);
        gv_lib_try = (GridView) view.findViewById(R.id.gv_lib_try);
        gv_lib_color_board = (GridView) view.findViewById(R.id.gv_lib_color_board);

        ArrayList<String> libTryList = Constants.getLipTryImageList();
        gv_lib_try.setAdapter(new GridViewAdapter(getActivity(),libTryList));

        ArrayList<String> libColorBoard = Constants.getLibColorBoard();
        gv_lib_color_board.setAdapter(new GridViewAdapter(getActivity(), libColorBoard));

        tv_one_rate_single = (TextView) view.findViewById(R.id.tv_one_rate_single);
        tv_one_rate_all = (TextView) view.findViewById(R.id.tv_one_rate_all);
        tv_comment_color = (TextView) view.findViewById(R.id.tv_comment_color);
        tv_comment_place = (TextView) view.findViewById(R.id.tv_comment_place);

        initBarChar(view);
        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        tv_one_rate_single.setText("正红里面讨巧提亮一点点，好像明星的好莱坞设计感极强的专用色！");
        tv_one_rate_all.setText("明星款烈焰蓝金，颜色饱和没话讲，整体气场爆棚；掉妆是个小问题");
        tv_comment_color.setText("偏一点浆果色的正红，非常优雅，如一件设计师作品。饱满较润，持久程度一般过关。");
        tv_comment_place.setText("你就是明星，这是一只面对任何需要关注的场合的核武器");

    }

    private void initBarChar(View view){
        //初始化表格
        mChart = (BarChart) view.findViewById(R.id.chart1);
        mChart.setOnChartValueSelectedListener(this);
        mChart.setDescription("");

//        mChart.setDrawBorders(true);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawBarShadow(false);

        mChart.setDrawGridBackground(false);

//        MyMarkerView mv = new MyMarkerView(getActivity(), R.layout.custom_marker_view);
//        mChart.setMarkerView(mv);

        tf = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Regular.ttf");

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART_INSIDE);
        l.setTypeface(tf);

        XAxis xl = mChart.getXAxis();
        xl.setTypeface(tf);
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTypeface(tf);
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setDrawGridLines(false);
        leftAxis.setSpaceTop(25f);

        mChart.getAxisRight().setEnabled(false);



        ArrayList<String> xVals = new ArrayList<String>();

        xVals.add("综合评分");
        xVals.add("外包装");
        xVals.add("滋润度");
        xVals.add("颜色饱和度");
        xVals.add("延展度");
        xVals.add("颜色持久度");


        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();



        yVals1.add(new BarEntry(8.4f, 0));
        yVals1.add(new BarEntry(10f, 1));
        yVals1.add(new BarEntry(7f, 2));
        yVals1.add(new BarEntry(10f, 3));
        yVals1.add(new BarEntry(7f, 4));
        yVals1.add(new BarEntry(8f, 5));


        yVals2.add(new BarEntry(8.7f, 0));
        yVals2.add(new BarEntry(10f, 1));
        yVals2.add(new BarEntry(7f, 2));
        yVals2.add(new BarEntry(10f, 3));
        yVals2.add(new BarEntry(7f, 4));
        yVals2.add(new BarEntry(8f, 5));

        // create 3 datasets with different types
        BarDataSet set1 = new BarDataSet(yVals1, "综合评分");
        // set1.setColors(ColorTemplate.createColors(getApplicationContext(),
        // ColorTemplate.FRESH_COLORS));
        set1.setColor(Color.rgb(104, 241, 175));
        BarDataSet set2 = new BarDataSet(yVals2, "姐妹团评分");
        set2.setColor(Color.rgb(164, 228, 251));


        ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
        dataSets.add(set1);
        dataSets.add(set2);

        BarData data = new BarData(xVals, dataSets);
//        data.setValueFormatter(new LargeValueFormatter());

        // add space between the dataset groups in percent of bar-width
        data.setGroupSpace(80f);
        data.setValueTypeface(tf);

        mChart.setData(data);
        mChart.invalidate();
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    class GridViewAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private ArrayList<String> list;

        public GridViewAdapter(Context context, ArrayList<String> list) {
            inflater = LayoutInflater.from(context);
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            ViewHolder holder = null;

            if (view == null) {

                view = inflater.inflate(R.layout.item_gridview_imageview, viewGroup, false);
                holder = new ViewHolder();
                holder.iv = (ImageView) view.findViewById(R.id.iv_item_gridview);

                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            holder.iv.setImageDrawable(getActivity().getResources().getDrawable(Integer.valueOf(list.get(i))));

            return view;
        }

        class ViewHolder {
            private ImageView iv;
        }
    }
}
