package com.imgod1.commlib.common;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import org.jetbrains.annotations.NotNull;

/**
 * RvItemTouchHelpCallback.java
 *
 * @author gaokang
 * @version 1.0 2019/7/2 16:34
 * @update gaokang 2019/7/2 16:34
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
public class RvItemTouchHelpCallback extends ItemTouchHelper.Callback {

    private OnDragListener itemTouchAdapter;
    public RvItemTouchHelpCallback(OnDragListener itemTouchAdapter){
        this.itemTouchAdapter = itemTouchAdapter;
    }



    @Override
    public int getMovementFlags(@NotNull RecyclerView recyclerView, @NotNull RecyclerView.ViewHolder viewHolder) {
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            final int swipeFlags = 0;
            return makeMovementFlags(dragFlags, swipeFlags);
        } else {
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            //final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
            final int swipeFlags = 0;
            return makeMovementFlags(dragFlags, swipeFlags);
        }
    }

    @Override
    public boolean onMove(@NotNull RecyclerView recyclerView, @NotNull RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        //得到目标ViewHolder的position
        int fromPosition = viewHolder.getAdapterPosition();
        //得到目标ViewHolder的position
        int toPosition = target.getAdapterPosition();
        itemTouchAdapter.onMove(fromPosition,toPosition);
        XLog.e("onMove--->" ,"onMove1");
        return true;
    }

    @Override
    public void onSwiped(@NotNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        itemTouchAdapter.onSwiped(position);
    }


//    @Override
//    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
//            //滑动时改变Item的透明度
//            final float alpha = 1 - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
//            viewHolder.itemView.setAlpha(alpha);
//            viewHolder.itemView.setTranslationX(dX);
//        } else {
//            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//        }
//    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if (background == null ) {
                background = viewHolder.itemView.getBackground();
            }
            viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
        }
        super.onSelectedChanged(viewHolder, actionState);
    }


    @Override
    public void clearView(@NotNull RecyclerView recyclerView, @NotNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);

        viewHolder.itemView.setAlpha(1.0f);
        if (background != null) {
            viewHolder.itemView.setBackground(background);
        }else {
            viewHolder.itemView.setBackgroundColor(0);
        }

        if (onDragListener!=null){
            onDragListener.onFinishDrag();
        }
    }

    private Drawable background = null;
    private int bkcolor = -1;

    private OnDragListener onDragListener;
    public RvItemTouchHelpCallback setOnDragListener(OnDragListener onDragListener) {
        this.onDragListener = onDragListener;
        return this;
    }
    public interface OnDragListener{
        void onFinishDrag();
        void onMove(int fromPosition, int toPosition);
        void onSwiped(int position);
    }
}
