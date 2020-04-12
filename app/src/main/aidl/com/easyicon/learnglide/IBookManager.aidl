// IBookManager.aidl
package com.easyicon.learnglide;

import com.easyicon.learnglide.presenter.bean.Book;
import com.easyicon.learnglide.listener.OnChangeListener;

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
    void registerListener(OnChangeListener listener);
    void unregisterListener(OnChangeListener listener);
}
