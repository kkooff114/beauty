package beauty.beautydemo.tools;

import android.app.Activity;
import android.util.DisplayMetrics;
/**
 * Created by LJW on 15/3/17.
 */

public class BaseTools {

    /** 获取屏幕的宽度 */
    public final static int getWindowsWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }
}
