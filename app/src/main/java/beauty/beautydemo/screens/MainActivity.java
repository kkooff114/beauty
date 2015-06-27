package beauty.beautydemo.screens;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import beauty.beautydemo.R;
import beauty.beautydemo.dataprovider.DataProvider;
import beauty.beautydemo.fragment.ColorFragment;
import beauty.beautydemo.fragment.IdeaFragment;
import beauty.beautydemo.fragment.PropertyFragment;
import beauty.beautydemo.fragment.PropertyMaterialFragment;
import beauty.beautydemo.fragment.TestLibFragment;
import beauty.beautydemo.tools.ScreenTools;

/**
 * Created by LJW on 15/3/17.
 * 主界面
 */
public class MainActivity extends FragmentActivity {

    private Fragment mContent;
    private RadioGroup main_tab_group;
    private final static String TAG = "MainActivity";
    private FrameLayout tabcontent;


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.tab_host);

        Point p = new Point();
        getWindowManager().getDefaultDisplay().getRealSize(p);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        DataProvider.setWindowWidth(p.x);
        DataProvider.setWindowHeight(p.y);
        DataProvider.setStatusHeight(ScreenTools.getStatusBarHeight(getApplicationContext()));
        Log.i(TAG, "x:" + p.x + ", y:" + p.y + ". x:" + p.x / dm.xdpi + ", y:" + p.y / dm.ydpi);

        tabcontent = (FrameLayout) findViewById(android.R.id.tabcontent);
        main_tab_group = (RadioGroup) findViewById(R.id.main_tab_group);
        switchContent(new ColorFragment());

        main_tab_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                switch (i) {
                    case R.id.main_tab_color:

                        params.setMargins(0, 0, 0, (int) getResources().getDimension(R.dimen.tab_menu_height));
                        tabcontent.setLayoutParams(params);

                        switchContent(new ColorFragment());
                        break;

                    case R.id.main_tab_idea:

                        params.setMargins(0, 0, 0, (int) getResources().getDimension(R.dimen.tab_menu_height));
                        tabcontent.setLayoutParams(params);

                        switchContent(new IdeaFragment());
                        break;

                    case R.id.main_tab_testlib:

                        params.setMargins(0, 0, 0, (int) getResources().getDimension(R.dimen.tab_menu_height));
                        tabcontent.setLayoutParams(params);

                        switchContent(new TestLibFragment());
                        break;

                    case R.id.main_tab_property:

                        params.setMargins(0, 0, 0, (int) getResources().getDimension(R.dimen.tab_menu_height));
                        tabcontent.setLayoutParams(params);

                        switchContent(PropertyMaterialFragment.getInstance("个人档案"));
                        break;
                }
            }
        });
    }


    /**
     * 底部菜单更新界面
     *
     * @param fragment
     */
    private void switchContent(Fragment fragment) {

        mContent = fragment;

        getSupportFragmentManager().beginTransaction().replace(android.R.id.tabcontent, fragment).commit();

    }

    public float centerX=0,centerY=0;
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
}
