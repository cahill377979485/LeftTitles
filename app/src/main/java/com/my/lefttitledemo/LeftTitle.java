package com.my.lefttitledemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author 文琳
 * @time 2020/9/7 11:04
 * @desc
 */
public class LeftTitle implements Parcelable {
    private int position;
    private String str;
    private boolean checked;

    public LeftTitle(int position, String str, boolean checked) {
        this.position = position;
        this.str = str;
        this.checked = checked;
    }

    public LeftTitle(int position, String str) {
        this.position = position;
        this.str = str;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.position);
        dest.writeString(this.str);
        dest.writeByte(this.checked ? (byte) 1 : (byte) 0);
    }

    protected LeftTitle(Parcel in) {
        this.position = in.readInt();
        this.str = in.readString();
        this.checked = in.readByte() != 0;
    }

    public static final Parcelable.Creator<LeftTitle> CREATOR = new Parcelable.Creator<LeftTitle>() {
        @Override
        public LeftTitle createFromParcel(Parcel source) {
            return new LeftTitle(source);
        }

        @Override
        public LeftTitle[] newArray(int size) {
            return new LeftTitle[size];
        }
    };
}
