package beauty.beautydemo.tools;

import android.app.Activity;

import beauty.beautydemo.R;


/**
 * Created by lgp on 2015/6/7.
 */
public class ThemeUtils {

    public static void changTheme(Activity activity, Theme theme) {
        if (activity == null)
            return;
        int style = R.style.RedTheme;
        switch (theme) {
            case BROWN:
                style = R.style.BrownTheme;
                break;
            case BLUE:
                style = R.style.BlueTheme;
                break;
            case BLUE_GREY:
                style = R.style.BlueGreyTheme;
                break;
            case YELLOW:
                style = R.style.YellowTheme;
                break;
            case DEEP_PURPLE:
                style = R.style.DeepPurpleTheme;
                break;
            case PINK:
                style = R.style.PinkTheme;
                break;
            case GREEN:
                style = R.style.GreenTheme;
                break;
            default:
                break;
        }
        activity.setTheme(style);
    }

    public enum Theme {
        RED(0x00),
        BROWN(0x01),
        BLUE(0x02),
        BLUE_GREY(0x03),
        YELLOW(0x04),
        DEEP_PURPLE(0x05),
        PINK(0x06),
        GREEN(0x07);

        private int mValue;

        Theme(int value) {
            this.mValue = value;
        }

        public static Theme mapValueToTheme(final int value) {
            for (Theme theme : Theme.values()) {
                if (value == theme.getIntValue()) {
                    return theme;
                }
            }
            // If run here, return default
            return RED;
        }

        static Theme getDefault() {
            return RED;
        }

        public int getIntValue() {
            return mValue;
        }
    }

    public static int getThemeColor(final int value) {
        switch (value) {
            case 0x00:
                return R.color.theme_color;

            case 0x01:
                return R.color.brown;
            case 0x02:
                return R.color.blue;
            case 0x03:
                return R.color.blue_grey;
            case 0x04:
                return R.color.yellow;
            case 0x05:
                return R.color.deep_purple;
            case 0x06:
                return R.color.pink;
            case 0x07:
                return R.color.green;

        }

        return R.color.theme_color;

    }
}
