package com.my.lefttitledemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author 文琳
 * @time 2020/9/7 13:50
 * @desc
 */
public class LeftTitleBig implements Parcelable {
    private LeftTitle last;
    private LeftTitle current;
    private LeftTitle next;

    public LeftTitleBig() {
    }

    public LeftTitleBig(LeftTitle last, LeftTitle current, LeftTitle next) {
        this.last = last;
        this.current = current;
        this.next = next;
    }

    public LeftTitle getLast() {
        return last;
    }

    public void setLast(LeftTitle last) {
        this.last = last;
    }

    public LeftTitle getCurrent() {
        return current;
    }

    public void setCurrent(LeftTitle current) {
        this.current = current;
    }

    public LeftTitle getNext() {
        return next;
    }

    public void setNext(LeftTitle next) {
        this.next = next;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.last, flags);
        dest.writeParcelable(this.current, flags);
        dest.writeParcelable(this.next, flags);
    }

    protected LeftTitleBig(Parcel in) {
        this.last = in.readParcelable(LeftTitle.class.getClassLoader());
        this.current = in.readParcelable(LeftTitle.class.getClassLoader());
        this.next = in.readParcelable(LeftTitle.class.getClassLoader());
    }

    public static final Parcelable.Creator<LeftTitleBig> CREATOR = new Parcelable.Creator<LeftTitleBig>() {
        @Override
        public LeftTitleBig createFromParcel(Parcel source) {
            return new LeftTitleBig(source);
        }

        @Override
        public LeftTitleBig[] newArray(int size) {
            return new LeftTitleBig[size];
        }
    };
}
