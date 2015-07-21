package beauty.beautydemo.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;

import beauty.beautydemo.R;
import beauty.beautydemo.tools.PreferenceUtils;
import beauty.beautydemo.tools.ThemeUtils;
import butterknife.ButterKnife;

/**
 * Created by LJW on 15/4/8.
 */
public class BeautyBaseActivity extends AppCompatActivity {

    public static final String TITLE = "title";
    public static final String ARG_REVEAL_START_LOCATION = "reveal_start_location";
    public static final String ACTION_SHOW_LOADING_ITEM = "action_show_loading_item";
    public static final String CROP_IMAGE_URI = "crop_image_url";//传递裁剪后的照片地址
    public static final String PUBLISH_IMAGE_PATH = "PUBLISH_IMAGE_PATH";//待发布的照片地址
    public static final String TOOL_BAR_TITLE = "tool_bar_title";

    public static final Interpolator INTERPOLATOR = new DecelerateInterpolator();
    public static final Interpolator OVERSHOOT = new OvershootInterpolator(1.f);
    public static final Interpolator Anticipate = new AnticipateInterpolator();

    public static final String MENUID = "menuid";

    public static final int REQUEST_CODE_SELECT_TAGS = 0;// 选择tags
    public static final int REQUEST_CODE_EDIT_PHOTO = 1;// 编辑照片

    public static final String FLAG_SELECT_TAGS = "select_tags";// 选择tags


    protected PreferenceUtils preferenceUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferenceUtils = PreferenceUtils.getInstance(this);
        initTheme();

        super.onCreate(savedInstanceState);
    }

    private void initTheme() {
        ThemeUtils.Theme theme = getCurrentTheme();
        ThemeUtils.changTheme(this, theme);
    }

    protected ThemeUtils.Theme getCurrentTheme() {
        int value = preferenceUtils.getIntParam(getString(R.string.change_theme_key), 0);
        return ThemeUtils.Theme.mapValueToTheme(value);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    /**
     * 启动activity
     *
     * @param v
     * @param startingActivity
     * @param destinationActivity
     */
    public static void startActivityRelLocation(final View v, final Activity startingActivity, final Class destinationActivity) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int[] startingLocation = new int[2];
                v.getLocationOnScreen(startingLocation);
                startingLocation[0] += v.getWidth() / 2;

                Intent intent = new Intent(startingActivity, destinationActivity);
                intent.putExtra(ARG_REVEAL_START_LOCATION, startingLocation);
                startingActivity.startActivity(intent);

                startingActivity.overridePendingTransition(0, 0);
            }
        }, 450);
    }


    public void exitSuper() {
        finish();
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
    }
}
