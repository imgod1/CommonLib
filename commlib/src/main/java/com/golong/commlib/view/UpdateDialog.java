package com.golong.commlib.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.golong.commlib.R;
import com.golong.commlib.common.NumberProgressBar;
import com.golong.commlib.util.SPUtil;


import java.text.MessageFormat;

/**
 * @author Andy
 * @date 2018/9/20 14:40
 * Desc: 应用更新弹窗
 */
public class UpdateDialog extends Dialog {


    private static OnUpdateClickListener mListener;
    private static NumberProgressBar loading;

    UpdateDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public void setOnUpdateClickListener(OnUpdateClickListener listener) {
        mListener = listener;
    }

    public void setDownLoadProcess(float progress) {
        try {
            loading.setProgress((int) progress);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class Builder {

        private final Context mContext;
        private boolean isAudit = false;
        private String content;
        private String verisonCode;
        private String title;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder setAudit(boolean audit) {
            this.isAudit = audit;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setVersionCode(String code) {
            this.verisonCode = code;
            return this;
        }

        public Builder setTitle(String t) {
            this.title = t;
            return this;
        }

        public UpdateDialog creat() {
            final UpdateDialog dialog = new UpdateDialog(mContext,
                    R.style.Theme_Light_NoTitle_Dialog);
            Window window = dialog.getWindow();
            window.setDimAmount(0.7f);
            window.setWindowAnimations(R.style.scale_rising);
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(lp);
            window.setGravity(Gravity.CENTER);
            View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_update_view, null);
            dialog.setContentView(view);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
            ImageView ivExit = view.findViewById(R.id.iv_exit);
            TextView tvContent = view.findViewById(R.id.tv_content);
            TextView tvUpdate = view.findViewById(R.id.tv_update);
            TextView tvVersion = view.findViewById(R.id.tv_version);
            loading = view.findViewById(R.id.loading);
            tvContent.setText(content);
            if (isAudit) {
                ivExit.setVisibility(View.INVISIBLE);
            } else {
                ivExit.setVisibility(View.VISIBLE);
            }

            ivExit.setOnClickListener(v -> {
                dialog.dismiss();
                SPUtil.put(mContext, "VersionName", verisonCode);
            });
            tvVersion.setText(title);
            tvUpdate.setOnClickListener(v -> {
                if (!isAudit) {
                    dialog.dismiss();
                } else {
                    tvUpdate.setVisibility(View.INVISIBLE);
                    loading.setVisibility(View.VISIBLE);
                    // TODO: 2019/5/7  注意到时候去掉
                    dialog.dismiss();
                }
                if (mListener != null) {
                    mListener.onUpdateClick(mContext, v, loading);
                }
            });
            dialog.setOnDismissListener(dialog1 -> mListener = null);
            return dialog;

        }
    }

    public interface OnUpdateClickListener {

        void onUpdateClick(Context context, View view, NumberProgressBar loading);
    }
}
