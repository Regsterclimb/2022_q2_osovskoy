package com.example.a2022_q2_osovskoy.ui.extentions

import android.animation.ObjectAnimator
import android.content.res.Resources
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.core.animation.doOnStart
import androidx.core.view.isVisible


fun View.shake(duration: Long, right: Float, left: Float, default: Float): ObjectAnimator =
    ObjectAnimator.ofFloat(this, View.TRANSLATION_X, left, right, left, right, default).apply {
        setDuration(duration)
        interpolator = OvershootInterpolator()
    }

fun View.fadeOut(duration: Long, startValue: Float): ObjectAnimator =
    ObjectAnimator.ofFloat(this, View.ALPHA, startValue).setDuration(duration)

fun View.fadeIn(duration: Long, startValue: Float, endValue: Float): ObjectAnimator =
    ObjectAnimator.ofFloat(this, View.ALPHA, startValue, endValue).apply {
        setDuration(duration)
        doOnStart {
            this@fadeIn.isVisible = true
            this@fadeIn.alpha = startValue
        }
    }


fun Float.toDp(): Float = this / Resources.getSystem().displayMetrics.density
