package com.easyicon.learnglide.presenter.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * ProjectName:    LearnGlide
 * Package:        com.easyicon.learnglide.presenter.bean
 * ClassName:      InfoParcelable
 * Description:
 * Author:         61444
 * CreateDate:     2020/3/10 23:46
 */
public class InfoParcelable implements Parcelable {

    private int userId;
    private String userName;
    private boolean isMale;

    private Book book;

    public InfoParcelable() {
    }

    public InfoParcelable(int userId, String userName, boolean isMale) {
        this.userId = userId;
        this.userName = userName;
        this.isMale = isMale;
    }

    protected InfoParcelable(Parcel in) {
        userId = in.readInt();
        userName = in.readString();
        isMale = in.readInt() == 1;
        book = in.readParcelable(Thread.currentThread().getContextClassLoader());
    }

    public static final Creator<InfoParcelable> CREATOR = new Creator<InfoParcelable>() {
        @Override
        public InfoParcelable createFromParcel(Parcel in) {
            return new InfoParcelable(in);
        }

        @Override
        public InfoParcelable[] newArray(int size) {
            return new InfoParcelable[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeString(userName);
        dest.writeInt(isMale ? 1 : 0);
        dest.writeParcelable(book, 0);
    }
}
