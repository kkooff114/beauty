package beauty.beautydemo.custview.reveal.revealanimator;

import android.annotation.TargetApi;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;

import com.nineoldandroids.animation.Animator;

import java.lang.ref.WeakReference;

import beauty.beautydemo.custview.reveal.supportanimator.ViewAnimationUtils;


/**
 * @hide
 */
public interface RevealAnimator {

    public void setClipOutlines(boolean clip);

    public void setCenter(float cx, float cy);

    public void setRevealRadius(float value);

    public float getRevealRadius();

    public void invalidate(Rect bounds);

    static class RevealFinishedGingerbread extends ViewAnimationUtils.SimpleAnimationListener {
        WeakReference<RevealAnimator> mReference;
        volatile Rect mInvalidateBounds;

        public RevealFinishedGingerbread(RevealAnimator target, Rect bounds) {
            mReference = new WeakReference<RevealAnimator>(target);
            mInvalidateBounds = bounds;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);

            RevealAnimator target = mReference.get();

            if(target == null){
                return;
            }

            target.setClipOutlines(false);
            target.invalidate(mInvalidateBounds);

            RevealMiddleware middleware = RevealMiddleware.getInstance();
            middleware.clean();
        }
    }

    static class RevealFinishedIceCreamSandwich extends ViewAnimationUtils.SimpleAnimationListener {
        WeakReference<RevealAnimator> mReference;
        volatile Rect mInvalidateBounds;

        int mLayerType;

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        public RevealFinishedIceCreamSandwich(RevealAnimator target, Rect bounds) {
            mReference = new WeakReference<RevealAnimator>(target);
            mInvalidateBounds = bounds;

            mLayerType = ((View) target).getLayerType();
        }

        @Override
        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        public void onAnimationStart(Animator animation) {
            super.onAnimationStart(animation);
            ((View) mReference.get()).setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        @Override
        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            ((View) mReference.get()).setLayerType(mLayerType, null);

            RevealAnimator target = mReference.get();

            if(target == null){
                return;
            }

            target.setClipOutlines(false);
            target.invalidate(mInvalidateBounds);

            RevealMiddleware middleware = RevealMiddleware.getInstance();
            middleware.clean();
        }
    }

    static class RevealFinishedJellyBeanMr2 extends ViewAnimationUtils.SimpleAnimationListener {
        WeakReference<RevealAnimator> mReference;
        volatile Rect mInvalidateBounds;

        int mLayerType;

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        public RevealFinishedJellyBeanMr2(RevealAnimator target, Rect bounds) {
            mReference = new WeakReference<RevealAnimator>(target);
            mInvalidateBounds = bounds;

            mLayerType = ((View) target).getLayerType();
        }

        @Override
        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        public void onAnimationStart(Animator animation) {
            super.onAnimationStart(animation);
            ((View) mReference.get()).setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }

        @Override
        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            ((View) mReference.get()).setLayerType(mLayerType, null);

            RevealAnimator target = mReference.get();

            if(target == null){
                return;
            }

            target.setClipOutlines(false);
            target.invalidate(mInvalidateBounds);

            RevealMiddleware middleware = RevealMiddleware.getInstance();
            middleware.clean();
        }
    }
}