package com.imgod1.commlib.preview;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * ImgInfo.java
 * 图片信息
 * @author gaokang
 * @version 1.0 2019/7/2 16:42
 * @update gaokang 2019/7/2 16:42
 * @updateDes
 * @include {@link }
 * @used {@link }
 */

public class ImgInfo implements Parcelable{

    private String url;
    private Rect bounds;

    public ImgInfo() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Rect getBounds() {
        return bounds;
    }

    public void setBounds(Rect bounds) {
        this.bounds = bounds;
    }

    public static Creator<ImgInfo> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeParcelable(this.bounds, flags);
    }

    private ImgInfo(Parcel in) {
        this.url = in.readString();
        this.bounds = in.readParcelable(Rect.class.getClassLoader());
    }

    public static final Creator<ImgInfo> CREATOR = new Creator<ImgInfo>() {
        @Override
        public ImgInfo createFromParcel(Parcel source) {
            return new ImgInfo(source);
        }

        @Override
        public ImgInfo[] newArray(int size) {
            return new ImgInfo[size];
        }
    };
}
