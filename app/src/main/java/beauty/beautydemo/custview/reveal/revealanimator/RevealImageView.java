package beauty.beautydemo.custview.reveal.revealanimator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by LJW on 15/5/8.
 */
public class RevealImageView extends ImageView implements RevealAnimator {

    private Path mRevealPath;
    private boolean mClipOutlines;
    private float mCenterX;
    private float mCenterY;
    private float mRadius;

    public RevealImageView(Context context) {
        super(context);
        this.mRevealPath = new Path();
    }

    public RevealImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mRevealPath = new Path();
    }

    public RevealImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mRevealPath = new Path();
    }


    @Override
    public void setClipOutlines(boolean b) {
        this.mClipOutlines = b;
    }

    @Override
    public void setCenter(float v, float v1) {
        Log.i("imageView", "centerX: " + v + " centerY:" + v1);
        this.mCenterX = v;
        this.mCenterY = v1;
    }


    @Override
    public void setRevealRadius(float v) {
        this.mRadius = v;
        this.invalidate();
    }

    @Override
    public float getRevealRadius() {
        return this.mRadius;
    }


    @Override
    protected void onDraw(Canvas canvas) {

        if (!this.mClipOutlines) {
            super.onDraw(canvas);
        } else {

            int state = canvas.save();
            this.mRevealPath.reset();
            this.mRevealPath.addCircle(mCenterX, mCenterY, mRadius, Path.Direction.CW);
            Log.i("onDraw", "x:" + this.mCenterX + " y:" + this.mCenterY + " radius:" + this.mRadius);
            canvas.clipPath(this.mRevealPath, Region.Op.XOR);
            super.onDraw(canvas);
            canvas.restoreToCount(state);

        }

    }

}
