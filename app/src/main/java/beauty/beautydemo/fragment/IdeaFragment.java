package beauty.beautydemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import beauty.beautydemo.R;
import beauty.beautydemo.adapter.NewsFragmentPagerAdapter;
import beauty.beautydemo.application.BeautyApplication;
import beauty.beautydemo.bean.ChannelItem;
import beauty.beautydemo.bean.ChannelManage;
import beauty.beautydemo.custview.ColumnHorizontalScrollView;
import beauty.beautydemo.screens.ChannelActivity;
import beauty.beautydemo.tools.BaseTools;

/**
 * Created by LJW on 15/3/17.
 */
public class IdeaFragment extends Fragment {

    /** 自定义HorizontalScrollView */
    private ColumnHorizontalScrollView mColumnHorizontalScrollView;
    LinearLayout mRadioGroup_content;
    LinearLayout ll_more_columns;
    RelativeLayout rl_column;
    private ViewPager mViewPager;
    private ImageView button_more_columns;
    /** 用户选择的新闻分类列表*/
    private ArrayList<ChannelItem> userChannelList=new ArrayList<ChannelItem>();
    /** 当前选中的栏目*/
    private int columnSelectIndex = 0;
    /** 左阴影部分*/
    public ImageView shade_left;
    /** 右阴影部分 */
    public ImageView shade_right;
    /** 屏幕宽度 */
    private int mScreenWidth = 0;
    /** Item宽度 */
    private int mItemWidth = 0;
    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();

    /** 请求CODE */
    public final static int CHANNELREQUEST = 1;
    /** 调整返回的RESULTCODE */
    public final static int CHANNELRESULT = 10;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu_idea, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mScreenWidth = BaseTools.getWindowsWidth(getActivity());
        mItemWidth = mScreenWidth / 7;// 一个Item宽度为屏幕的1/7


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        mColumnHorizontalScrollView =  (ColumnHorizontalScrollView)getActivity().findViewById(R.id.mColumnHorizontalScrollView);
        mRadioGroup_content = (LinearLayout) getActivity().findViewById(R.id.mRadioGroup_content);
        ll_more_columns = (LinearLayout) getActivity().findViewById(R.id.ll_more_columns);
        rl_column = (RelativeLayout) getActivity().findViewById(R.id.rl_column);
        button_more_columns = (ImageView) getActivity().findViewById(R.id.button_more_columns);
        mViewPager = (ViewPager) getActivity().findViewById(R.id.mViewPager);
        shade_left = (ImageView) getActivity().findViewById(R.id.shade_left);
        shade_right = (ImageView) getActivity().findViewById(R.id.shade_right);

        button_more_columns.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent_channel = new  Intent(getActivity(), ChannelActivity.class);
                startActivityForResult(intent_channel, CHANNELREQUEST);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        setChangelView();
    }

    /**
     *  当栏目项发生变化时候调用
     * */
    private void setChangelView() {
        initColumnData();
        initTabColumn();
        initFragment();
    }
    /** 获取Column栏目 数据*/
    private void initColumnData() {
        userChannelList = ((ArrayList<ChannelItem>) ChannelManage.getManage(BeautyApplication.getApp().getSQLHelper()).getUserChannel());
    }

    /**
     *  初始化Column栏目项
     * */
    private void initTabColumn() {
        mRadioGroup_content.removeAllViews();
        int count =  userChannelList.size();
        mColumnHorizontalScrollView.setParam(getActivity(), mScreenWidth, mRadioGroup_content, shade_left, shade_right, ll_more_columns, rl_column);
        for(int i = 0; i< count; i++){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mItemWidth , LinearLayout.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 5;
            params.rightMargin = 5;
//			TextView localTextView = (TextView) mInflater.inflate(R.layout.column_radio_item, null);
            TextView columnTextView = new TextView(getActivity());
            columnTextView.setTextAppearance(getActivity(), R.style.top_category_scroll_view_item_text);
//			localTextView.setBackground(getResources().getDrawable(R.drawable.top_category_scroll_text_view_bg));
            columnTextView.setBackgroundResource(R.drawable.radio_buttong_bg);
            columnTextView.setGravity(Gravity.CENTER);
            columnTextView.setPadding(5, 5, 5, 5);
            columnTextView.setId(i);
            columnTextView.setText(userChannelList.get(i).getName());
            columnTextView.setTextColor(getResources().getColorStateList(R.color.top_category_scroll_text_color_day));
            if(columnSelectIndex == i){
                columnTextView.setSelected(true);
            }
            columnTextView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    for(int i = 0;i < mRadioGroup_content.getChildCount();i++){
                        View localView = mRadioGroup_content.getChildAt(i);
                        if (localView != v)
                            localView.setSelected(false);
                        else{
                            localView.setSelected(true);
                            mViewPager.setCurrentItem(i);
                        }
                    }
                    Toast.makeText(getActivity(), userChannelList.get(v.getId()).getName(), Toast.LENGTH_SHORT).show();
                }
            });
            mRadioGroup_content.addView(columnTextView, i ,params);
        }
    }
    /**
     *  选择的Column里面的Tab
     * */
    private void selectTab(int tab_postion) {
        columnSelectIndex = tab_postion;
        for (int i = 0; i < mRadioGroup_content.getChildCount(); i++) {
            View checkView = mRadioGroup_content.getChildAt(tab_postion);
            int k = checkView.getMeasuredWidth();
            int l = checkView.getLeft();
            int i2 = l + k / 2 - mScreenWidth / 2;
            // rg_nav_content.getParent()).smoothScrollTo(i2, 0);
            mColumnHorizontalScrollView.smoothScrollTo(i2, 0);
            // mColumnHorizontalScrollView.smoothScrollTo((position - 2) *
            // mItemWidth , 0);
        }
        //判断是否选中
        for (int j = 0; j <  mRadioGroup_content.getChildCount(); j++) {
            View checkView = mRadioGroup_content.getChildAt(j);
            boolean ischeck;
            if (j == tab_postion) {
                ischeck = true;
            } else {
                ischeck = false;
            }
            checkView.setSelected(ischeck);
        }
    }
    /**
     *  初始化Fragment
     * */
    private void initFragment() {
        fragments.clear();//清空
        int count =  userChannelList.size();
        for(int i = 0; i< count;i++){
            Bundle data = new Bundle();
            data.putString("text", userChannelList.get(i).getName());
            data.putInt("id", userChannelList.get(i).getId());
            NewsFragment newfragment = new NewsFragment();
            newfragment.setArguments(data);
            fragments.add(newfragment);
        }
        NewsFragmentPagerAdapter mAdapetr = new NewsFragmentPagerAdapter(getActivity().getSupportFragmentManager(), fragments);
//		mViewPager.setOffscreenPageLimit(0);
        mViewPager.setAdapter(mAdapetr);
        mViewPager.setOnPageChangeListener(pageListener);
    }
    /**
     *  ViewPager切换监听方法
     * */
    public ViewPager.OnPageChangeListener pageListener= new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int position) {
            // TODO Auto-generated method stub
            mViewPager.setCurrentItem(position);
            selectTab(position);
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        switch (requestCode) {
            case CHANNELREQUEST:
                if(resultCode == CHANNELRESULT){
                    setChangelView();
                }
                break;

            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
