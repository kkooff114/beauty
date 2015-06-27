package beauty.beautydemo.tools;

/**
 * Created by LJW on 15/4/4.
 */

import java.lang.ref.SoftReference;
import java.util.Hashtable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader.TileMode;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;

public class ImageTools {

    private static final String TAG = "ImageUtil";
    /**
     * 缓存集合
     */
    private static Hashtable<Integer, SoftReference<Bitmap>> mImageCache //
            = new Hashtable<Integer, SoftReference<Bitmap>>();

    /**
     * 根据id返回一个处理后的图片
     *
     * @param res
     * @param resID
     * @return
     */
    public static Bitmap getImageBitmap(Resources res, int resID) {
        // 先去集合中取当前resID是否已经拿过图片，如果集合中有，说明已经拿过，直接使用集合中的图片返回
        SoftReference<Bitmap> reference = mImageCache.get(resID);
        if (reference != null) {
            Bitmap bitmap = reference.get();
            if (bitmap != null) {// 从内存中取
                Log.i(TAG, "从内存中取");
                return bitmap;
            }
        }
        // 如果集合中没有，就调用getInvertImage得到一个图片，需要向集合中保留一张，最后返回当前图片
        Log.i(TAG, "重新加载");
        Bitmap invertBitmap = getInvertBitmap(res, resID);
        // 在集合中保存一份，便于下次获取时直接在集合中获取
        mImageCache.put(resID, new SoftReference<Bitmap>(invertBitmap));
        return invertBitmap;
    }

    /**
     * 根据图片的id，获取到处理之后的图片
     *
     * @param resID
     * @return
     */
    public static Bitmap getInvertBitmap(Resources res, int resID) {
        // 1.获取原图
        Bitmap sourceBitmap = BitmapFactory.decodeResource(res, resID);

        // 2.生成倒影图片
        Matrix m = new Matrix(); // 图片矩阵
        m.setScale(1.0f, -1.0f); // 让图片按照矩阵进行反转
        Bitmap invertBitmap = Bitmap.createBitmap(sourceBitmap, 0,
                sourceBitmap.getHeight() / 2, sourceBitmap.getWidth(),
                sourceBitmap.getHeight() / 2, m, false);

        // 3.两张图片合成一张图片
        Bitmap resultBitmap = Bitmap.createBitmap(sourceBitmap.getWidth(),
                (int) (sourceBitmap.getHeight() * 1.5 + 5), Config.ARGB_8888);
        Canvas canvas = new Canvas(resultBitmap); // 为合成图片指定一个画板
        canvas.drawBitmap(sourceBitmap, 0f, 0f, null); // 将原图片画在画布的上方
        canvas.drawBitmap(invertBitmap, 0f, sourceBitmap.getHeight() + 5, null); // 将倒影图片画在画布的下方

        // 4.添加遮罩效果
        Paint paint = new Paint();
        // 设置遮罩的颜色，这里使用的是线性梯度
        LinearGradient shader = new LinearGradient(0,
                sourceBitmap.getHeight() + 5, 0, resultBitmap.getHeight(),
                0x70ffffff, 0x00ffffff, TileMode.CLAMP);
        paint.setShader(shader);
        // 设置模式为：遮罩，取交集
        paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        canvas.drawRect(0, sourceBitmap.getHeight() + 5,
                sourceBitmap.getWidth(), resultBitmap.getHeight(), paint);

        return resultBitmap;
    }
}