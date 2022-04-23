package com.example.a2022_q2_osovskoy.presentation.view

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView

class DividerItemDecorator(private val mDivider: Drawable) :
    RecyclerView.ItemDecoration() {
    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val dividerLeft = parent.paddingLeft + calcDividerLeft(parent.width)
        val dividerRight = parent.width - parent.paddingRight
        val childCount = parent.childCount
        for (i in 0..childCount - 2) {
            if (i == 1) continue
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val dividerTop = child.bottom + params.bottomMargin
            val dividerBottom = dividerTop + mDivider.intrinsicHeight
            mDivider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
            mDivider.draw(canvas)
        }
    }

    //72dp is needed
    private fun calcDividerLeft(width: Int): Int {
        val toDp = (width / Resources.getSystem().displayMetrics.density) - 72
        return width - (toDp * Resources.getSystem().displayMetrics.density).toInt()
    }


}