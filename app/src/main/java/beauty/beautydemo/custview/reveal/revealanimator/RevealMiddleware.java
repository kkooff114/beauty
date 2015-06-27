package beauty.beautydemo.custview.reveal.revealanimator;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;


import java.lang.ref.WeakReference;

import beauty.beautydemo.custview.reveal.supportanimator.SupportAnimator;
import beauty.beautydemo.custview.reveal.supportanimator.ViewAnimationUtils;


/**
 * Created by LJW on 15/5/10.
 */
public class RevealMiddleware {

    private static final int Duration = 800;
    public Bitmap mBitmap = null;
    private RevealImageView mTopImage;
    private WeakReference<Activity> destActivity;


    private int width = 0, height = 0;
    private float centerX = 0, centerY = 0;

    static private RevealMiddleware mEffect;


    public static RevealMiddleware getInstance() {
        if (mEffect == null) {
            mEffect = new RevealMiddleware();
        }

        return mEffect;
    }

    public void prepare(Activity currActivity, float centerX, float centerY) {
        // Get the content of the activity and put in a bitmap
        this.centerX = centerX;
        this.centerY = centerY;
        View root;
        root = currActivity.getWindow().getDecorView().findViewById(android.R.id.content);
        root.setDrawingCacheEnabled(true);
        mBitmap = root.getDrawingCache();

    }

    public void animate() {
        int finalRadius = Math.max(mBitmap.getWidth(), mBitmap.getHeight());
        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(mTopImage, this.centerX, this.centerY, 0, finalRadius);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(Duration);

        animator.start();
    }


    public void prepareAnimation(final Activity destActivity) {
        this.destActivity = new WeakReference<Activity>(destActivity);
        mTopImage = createImageView(destActivity, mBitmap);
    }

    private RevealImageView createImageView(Activity destActivity, Bitmap bmp) {
        mTopImage = new RevealImageView(destActivity);
        mTopImage.setImageBitmap(bmp);

        height = bmp.getHeight();
        width = bmp.getWidth();

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width, height);
        ((FrameLayout) destActivity.getWindow().getDecorView().findViewById(android.R.id.content)).addView(mTopImage, params);


        return mTopImage;
    }


    public void clean() {

        if (mTopImage != null) {
            mTopImage.setLayerType(View.LAYER_TYPE_NONE, null);
            try {
                ((FrameLayout) destActivity.get().getWindow().getDecorView().findViewById(android.R.id.content)).removeView(mTopImage);
            } catch (Exception ignored) {
            }
        }

        mBitmap = null;
    }




}
