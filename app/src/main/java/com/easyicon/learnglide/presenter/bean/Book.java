package com.easyicon.learnglide.presenter.bean;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

/**
 * ProjectName:    LearnGlide
 * Package:        com.easyicon.learnglide.presenter.bean
 * ClassName:      Book
 * Description:
 * Author:         61444
 * CreateDate:     2020/3/10 23:53
 */
public class Book implements Parcelable {
    private String bookName;

    public Book(String bookName) {
        this.bookName = bookName;
    }

    protected Book(Parcel in) {
        bookName = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bookName);
    }

    @NonNull
    @Override
    public String toString() {
        return bookName;
    }
}
