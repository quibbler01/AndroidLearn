// OnChangeListener.aidl
package com.easyicon.learnglide.listener;

import com.easyicon.learnglide.presenter.bean.Book;

interface OnChangeListener {
    void onNewBook(in Book book);
}
