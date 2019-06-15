package com.golong.commlib.view;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.golong.commlib.R;
import com.golong.commlib.util.ScreenUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Andy
 * @date   2018/9/19 9:31
 * @link   {http://blog.csdn.net/andy_l1}
 * Desc:    仿ios底部弹窗
 */
public class IosActionSheet extends Dialog implements View.OnClickListener{

    private IosActionSheet(Context context, int themeResId) {
        super(context, themeResId);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onClick(View v) {
        dismiss();
        if (onItemClickListener != null){
            SheetMenu sheetMenu = (SheetMenu) v.getTag();
            onItemClickListener.onItemClick(sheetMenu.position, sheetMenu.text, v);
        }
    }

    public static class Params {
        private final List<SheetMenu> menuList = new ArrayList<>();
        private View.OnClickListener cancelListener;
        private String sheetTitle;
        private String cancelText;
        private Context context;
    }

    public static class Builder {
        private boolean canCancel = true;
        private boolean shadow = false;
        private final Params p;
        private int spacing;

        private LayoutParams defaultLayoutParam;
        private LayoutParams dividerLayoutParam;

        public Builder(Context context) {
            p = new Params();
            p.context = context;
            spacing = ScreenUtil.dip2px(context, 12);
            defaultLayoutParam = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            dividerLayoutParam =  new LayoutParams(LayoutParams.MATCH_PARENT, 1);
        }

        public Builder setCanCancel(boolean canCancel) {
            this.canCancel = canCancel;
            return this;
        }

        public Builder setShadow(boolean shadow) {
            this.shadow = shadow;
            return this;
        }

        public Builder setTitle(String title) {
            this.p.sheetTitle = title;
            return this;
        }

        public Builder addSheet(String text) {
            SheetMenu bm = new SheetMenu(text);
            this.p.menuList.add(bm);
            return this;
        }

        public Builder addSheet(int textId) {
            return addSheet(p.context.getString(textId));
        }

        public Builder setCancelListener(View.OnClickListener cancelListener) {
            p.cancelListener = cancelListener;
            return this;
        }

        public Builder setCancelText(int resId) {
            p.cancelText = p.context.getString(resId);
            return this;
        }

        public Builder setCancelText(String text) {
            p.cancelText = text;
            return this;
        }

        public IosActionSheet create() {
            final IosActionSheet dialog = new IosActionSheet(p.context,
                    shadow ? R.style.Theme_Light_NoTitle_Dialog : R.style.Theme_Light_NoTitle_NoShadow_Dialog);
            Window window = dialog.getWindow();
            window.setWindowAnimations(R.style.bottom_rising);
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);
            window.setGravity(Gravity.BOTTOM);

            View view = LayoutInflater.from(p.context).inflate(R.layout.ios_actionsheet, null);
            TextView btnCancel = (TextView) view.findViewById(R.id.btn_cancel);
            ViewGroup layContainer = (ViewGroup) view.findViewById(R.id.lay_container);

            boolean hasTitle = !TextUtils.isEmpty(p.sheetTitle);
            if (hasTitle) {
                layContainer.addView(getTitleView());
                layContainer.addView(getDividerView());
            }

            for (int i = 0; i < p.menuList.size(); i++) {
                SheetMenu sheetMenu = p.menuList.get(i);
                sheetMenu.position = i;
                layContainer.addView(getSheetView(i, sheetMenu, dialog));
                if (i != p.menuList.size() - 1) {
                    layContainer.addView(getDividerView());
                }
            }

            if (!TextUtils.isEmpty(p.cancelText)) {
                btnCancel.setText(p.cancelText);
            }

            if (p.cancelListener != null) {
                btnCancel.setOnClickListener(p.cancelListener);
            } else {
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }

            dialog.setContentView(view);
            dialog.setCanceledOnTouchOutside(canCancel);
            dialog.setCancelable(canCancel);
            return dialog;
        }

        private TextView getSheetView(int position, SheetMenu sheetMenu, View.OnClickListener onClickListener){
            TextView sheetView = new TextView(p.context);
            sheetView.setLayoutParams(defaultLayoutParam);
            int backgroundResId = R.drawable.ios_actionsheet_center;
            if (p.menuList.size() > 1) {
                if (position == 0) {
                    if (TextUtils.isEmpty(p.sheetTitle)) {
                        backgroundResId = R.drawable.ios_actionsheet_top;
                    } else {
                        backgroundResId = R.drawable.ios_actionsheet_center;
                    }
                } else if (position == p.menuList.size() - 1) {
                    backgroundResId = R.drawable.ios_actionsheet_bottom;
                }
            } else if (p.menuList.size() == 1) {
                backgroundResId = R.drawable.ios_actionsheet_singleton;
            }
            sheetView.setBackgroundResource(backgroundResId);
            sheetView.setPadding(0, spacing, 0, spacing);
            sheetView.setGravity(Gravity.CENTER);
            sheetView.setText(sheetMenu.text);
            sheetView.setTextColor(0xFF007AFF);
            sheetView.setTextSize(19);
            sheetView.setOnClickListener(onClickListener);
            sheetView.setTag(sheetMenu);
            return sheetView;
        }


        private TextView getTitleView(){
            TextView tTitle = new TextView(p.context);
            tTitle.setLayoutParams(defaultLayoutParam);
            tTitle.setGravity(Gravity.CENTER);
            tTitle.setTextColor(0xFF8F8F8F);
            tTitle.setText(p.sheetTitle);
            tTitle.setTextSize(17);
            tTitle.setPadding(0, spacing, 0, spacing);
            tTitle.setBackgroundResource(R.drawable.ios_actionsheet_top);
            return tTitle;
        }


        private View getDividerView(){
            View viewDivider = new View(p.context);
            viewDivider.setLayoutParams(dividerLayoutParam);
            viewDivider.setBackgroundColor(0xFFCED2D6);
            return viewDivider;
        }
    }

    private static class SheetMenu {
        public String text;
        public int position;

        public SheetMenu(String text) {
            this.text = text;
        }
    }

    public interface OnItemClickListener{
        /**
         * On item click
         * @param position 从上到下的位置，从0开始
         * @param text 每一项的文字
         * @param view 每一项的view
         */
        void onItemClick(int position, String text, View view);
    }
}
