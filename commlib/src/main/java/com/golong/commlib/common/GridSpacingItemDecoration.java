package com.golong.commlib.common;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author Andy
 * @date 2018/10/16 15:33
 * Desc:
 */
public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {


        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            // item column
            int position = parent.getChildAdapterPosition(view);
            // item column
            int column = position % spanCount;

            if (includeEdge) {
                // spacing - column * ((1f / spanCount) * spacing)
                outRect.left = spacing - column * spacing / spanCount;
                // (column + 1) * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount;
                // item column
                if (position < spanCount) {
                    outRect.top = spacing;
                }
                // item bottom
                outRect.bottom = spacing;
            } else {
                // column * ((1f / spanCount) * spacing)
                outRect.left = column * spacing / spanCount;
                // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount;
                // item top
                if (position >= spanCount) {
                    outRect.top = spacing;
                }
            }
        }

}
