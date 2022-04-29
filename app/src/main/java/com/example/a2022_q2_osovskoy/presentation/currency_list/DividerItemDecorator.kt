package com.example.a2022_q2_osovskoy.presentation.currency_list

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView

class DividerItemDecorator(private val mDivider: Drawable) :
    RecyclerView.ItemDecoration() {

    private companion object {
        const val EXCEPTION_LAST_DIVIDER = 2
        const val START_INDEX_DIVIDER = 0
    }

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val dividerLeft = parent.paddingLeft
        val dividerRight = parent.width - parent.paddingRight
        val childCount = parent.childCount

        for (i in START_INDEX_DIVIDER..childCount - EXCEPTION_LAST_DIVIDER) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val dividerTop = child.bottom + params.bottomMargin
            val dividerBottom = dividerTop + mDivider.intrinsicHeight
            mDivider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
            mDivider.draw(canvas)
        }
    }
}