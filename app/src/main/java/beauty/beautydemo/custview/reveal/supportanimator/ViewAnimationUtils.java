package beauty.beautydemo.custview.reveal.supportanimator;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

import beauty.beautydemo.custview.reveal.revealanimator.RevealAnimator;

import static android.os.Build.VERSION.SDK_INT;

public class ViewAnimationUtils {

//    private final static boolean LOLLIPOP_PLUS = SDK_INT >= LOLLIPOP;

    public static final int SCALE_UP_DURATION = 500;

    /**
     * Returns an Animator which can animate a clipping circle.
     * <p/>
     * Any shadow cast by the View will respect the circular clip from this animator.
     * <p/>
     * Only a single non-rectangular clip can be applied on a View at any time.
     * Views clipped by a circular reveal animation take priority over
     * {@link View#//setClipToOutline(boolean) View Outline clipping}.
     * <p/>
     * Note that the animation returned here is a one-shot animation. It cannot
     * be re-used, and once started it cannot be paused or resumed.
     *
     * @param view        The View will be clipped to the animating circle.
     * @param centerX     The x coordinate of the center of the animating circle.
     * @param centerY     The y coordinate of the center of the animating circle.
     * @param startRadius The starting radius of the animating circle.
     * @param endRadius   The ending radius of the animating circle.
     */

    public static SupportAnimator createCircularReveal(View view,
                                                       float centerX, float centerY,
                                                       float startRadius, float endRadius) {

        if (!(view instanceof RevealAnimator)) {
            throw new IllegalArgumentException("View must be inside RevealFrameLayout or RevealLinearLayout.");
        } else {
            RevealAnimator revealLayout = (RevealAnimator) view;
            revealLayout.setCenter(centerX, centerY);
            revealLayout.setClipOutlines(true);
            Rect bounds = new Rect();
            view.getHitRect(bounds);
            ObjectAnimator reveal = ObjectAnimator.ofFloat(revealLayout, "revealRadius", new float[]{startRadius, endRadius});
            reveal.addListener(getRevealFinishListener(revealLayout, bounds));
            reveal.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {

                    float currentValue = Float.valueOf(animation.getAnimatedValue().toString());
                    Log.d("TAG", "cuurent value is " + currentValue);
                }
            });
            return new SupportAnimatorPreL(reveal);
        }
    }


    static Animator.AnimatorListener getRevealFinishListener(RevealAnimator target, Rect bounds) {
        if (SDK_INT >= 18) {
            return new RevealAnimator.RevealFinishedJellyBeanMr2(target, bounds);
        } else if (SDK_INT >= 14) {
            return new RevealAnimator.RevealFinishedIceCreamSandwich(target, bounds);
        } else {
            return new RevealAnimator.RevealFinishedGingerbread(target, bounds);
        }
    }


    /**
     * Lifting view
     *
     * @param view         The animation target
     * @param baseRotation initial Rotation X in 3D space
     * @param fromY        initial Y position of view
     * @param duration     aniamtion duration
     * @param startDelay   start delay before animation begin
     */
    public static void liftingFromBottom(View view, float baseRotation, float fromY, int duration, int startDelay) {
        ViewHelper.setRotationX(view, baseRotation);
        ViewHelper.setTranslationY(view, fromY);

        ViewPropertyAnimator
                .animate(view)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setDuration(duration)
                .setStartDelay(startDelay)
                .rotationX(0)
                .translationY(0)
                .start();

    }

    /**
     * Lifting view
     *
     * @param view         The animation target
     * @param baseRotation initial Rotation X in 3D space
     * @param duration     aniamtion duration
     * @param startDelay   start delay before animation begin
     */
    public static void liftingFromBottom(View view, float baseRotation, int duration, int startDelay) {
        ViewHelper.setRotationX(view, baseRotation);
        ViewHelper.setTranslationY(view, view.getHeight() / 3);

        ViewPropertyAnimator
                .animate(view)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setDuration(duration)
                .setStartDelay(startDelay)
                .rotationX(0)
                .translationY(0)
                .start();

    }

    /**
     * Lifting view
     *
     * @param view         The animation target
     * @param baseRotation initial Rotation X in 3D space
     * @param duration     aniamtion duration
     */
    public static void liftingFromBottom(View view, float baseRotation, int duration) {
        ViewHelper.setRotationX(view, baseRotation);
        ViewHelper.setTranslationY(view, view.getHeight() / 3);

        ViewPropertyAnimator
                .animate(view)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setDuration(duration)
                .rotationX(0)
                .translationY(0)
                .start();

    }

    public static class SimpleAnimationListener implements Animator.AnimatorListener {

        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {

        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    }

}
