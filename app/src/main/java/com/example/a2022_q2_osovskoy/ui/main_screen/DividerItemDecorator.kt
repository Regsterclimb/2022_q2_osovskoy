package com.example.a2022_q2_osovskoy.ui.main_screen

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView

class DividerItemDecorator(private val mDivider: Drawable) :
    RecyclerView.ItemDecoration() {

    companion object {
        const val FIRST_DIVIDER_CHILD = 0
        const val LAST_DIVIDER_CHILD = 2
        const val REMOVED_DIVIDER_CHILD = 1
        const val INDENT = 72
    }

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val dividerLeft = parent.paddingLeft + calcDividerLeft(parent.width)
        val dividerRight = parent.width - parent.paddingRight
        val childCount = parent.childCount

        for (i in FIRST_DIVIDER_CHILD..childCount - LAST_DIVIDER_CHILD) {
            if (i == REMOVED_DIVIDER_CHILD) continue
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val dividerTop = child.bottom + params.bottomMargin
            val dividerBottom = dividerTop + mDivider.intrinsicHeight
            mDivider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
            mDivider.draw(canvas)
        }
    }

    private fun calcDividerLeft(width: Int): Int {
        val density  = Resources.getSystem().displayMetrics.density
        val valueInDp = (width / density) - INDENT
        return width - (valueInDp * density).toInt()
    }
}