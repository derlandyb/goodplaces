package br.com.derlandybelchior.goodplaces.presentation.home

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacesItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = 2 * space
        val pos = parent.getChildAdapterPosition(view)
        outRect.left = space
        outRect.right = space
        if (pos < 2){
            outRect.top = 2 * space
        }
    }
}