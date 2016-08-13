package com.mgl.test.hugetest.utils.animations;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;

public class GeneralAnimations {

    public static void rotate360(final View view, int duration) {
        Animation rotate = new RotateAnimation(0f, 360, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotate.setFillAfter(true);
        rotate.setInterpolator(new DecelerateInterpolator());
        rotate.setDuration(duration);
        view.setAnimation(rotate);
    }

    public static void animateFadeIn(final View view, int duration, int startDelay,final AnimatorListenerAdapter animationListener) {

        ViewCompat.setAlpha(view, 0);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, 1);
        objectAnimator.setDuration(duration);
        objectAnimator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                view.setVisibility(View.VISIBLE);
                if (animationListener != null) {
                    animationListener.onAnimationStart(null);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (animationListener != null) {
                    animationListener.onAnimationEnd(null);
                }
            }
        });
        objectAnimator.setInterpolator(new DecelerateInterpolator());
        objectAnimator.setStartDelay(startDelay);
        objectAnimator.start();

    }
}
