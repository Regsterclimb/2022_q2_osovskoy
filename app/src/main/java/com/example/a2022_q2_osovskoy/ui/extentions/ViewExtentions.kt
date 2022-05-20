package com.example.a2022_q2_osovskoy.ui.extentions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.res.Resources
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.core.view.isVisible


fun View.shake(right: Float, left: Float, default: Float): ObjectAnimator =
    ObjectAnimator.ofFloat(this, View.TRANSLATION_X, left, right, left, right, default).apply {
        duration = 1000L
        interpolator = OvershootInterpolator()
    }

fun View.fadeOut(duration: Long, startValue: Float): ObjectAnimator =
    ObjectAnimator.ofFloat(this, View.ALPHA, startValue).setDuration(duration)

fun View.fadeIn(duration: Long, startValue: Float, endValue: Float): ObjectAnimator {
    val fadeInAnimator =
        ObjectAnimator.ofFloat(this, View.ALPHA, startValue, endValue).setDuration(duration)

    fadeInAnimator.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animation: Animator?) {
            this@fadeIn.isVisible = true
            this@fadeIn.alpha = startValue
        }
    })
    return fadeInAnimator
}

fun Float.toDp() : Float = this / Resources.getSystem().displayMetrics.density
