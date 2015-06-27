package beauty.beautydemo.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import beauty.beautydemo.R;

/**
 * Created by LJW on 15/4/8.
 */
public class BeautyBaseActivity extends AppCompatActivity {

    public static final String ARG_REVEAL_START_LOCATION = "reveal_start_location";
    public static final String ACTION_SHOW_LOADING_ITEM = "action_show_loading_item";

    public static final String MENUID = "menuid";




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
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
}
