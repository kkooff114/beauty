package beauty.beautydemo.tools;

import android.os.Environment;

/**
 * Created by LJW on 15/6/27.
 */
public class AppParms {

    /**
     * 设备屏幕宽度高度，MainActivity里面设置
     */
//    public static float scale = 0;					//屏幕密度

    /**
     * 指定发布图片的宽度,单位px
     */
    public static int IMAGEWIDTH = 720;

    /**
     * App缓存文件路径
     */
    public static String CACHEPATH = Environment.getExternalStorageDirectory() + "/beauty/";


    public final static int NOTE_CREATE_OPR = 0x00;
    public final static int NOTE_EDIT_OPR = 0x01;

    public final static int NOTE_STUDY_TYPE = 0x00;
    public final static int NOTE_WORK_TYPE = 0x01;
    public final static int NOTE_OTHER_TYPE = 0x02;
    public final static int NOTE_TRASH_TYPE = 0x03;

    public final static int NOTE_UPDATE_EVENT = 0x00;
    public final static int NOTE_TYPE_UPDATE_EVENT = 0x01;
    public final static int CHANGE_THEME_EVENT = 0x02;

}
