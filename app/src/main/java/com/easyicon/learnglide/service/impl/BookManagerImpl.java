package com.easyicon.learnglide.service.impl;

import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import com.easyicon.learnglide.IBookManager;
import com.easyicon.learnglide.listener.OnChangeListener;
import com.easyicon.learnglide.presenter.bean.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * ProjectName:    LearnGlide
 * Package:        com.easyicon.learnglide.service.impl
 * ClassName:      BookManagerImpl
 * Description:
 * Author:         61444
 * CreateDate:     2020/3/13 22:51
 */
public class BookManagerImpl extends IBookManager.Stub {

    private RemoteCallbackList<OnChangeListener> mListeners = new RemoteCallbackList<>();

    private List<Book> list = new ArrayList<>();

    public AtomicBoolean isServiceAlive = new AtomicBoolean(true);

    public BookManagerImpl() {
        list.add(new Book("Quibbler"));
        list.add(new Book("Quibbler1"));
        list.add(new Book("Quibbler2"));
        list.add(new Book("Quibbler3"));
        list.add(new Book("Quibbler4"));
        list.add(new Book("Quibbler1988"));
        new Thread(new WorkThread()).start();
    }

    @Override
    public List<Book> getBookList() throws RemoteException {
        return list;
    }

    @Override
    public void addBook(Book book) throws RemoteException {
        list.add(book);
    }

    /**
     * @param book
     * @throws RemoteException
     */
    private void onNewBookArrived(Book book) throws RemoteException {
        Log.e("QUIBBLER", "1服务端添加一本书onNewBookArrived");
        final int N = mListeners.beginBroadcast();
        for (int i = 0; i < N; ++i) {
            OnChangeListener onChangeListener = mListeners.getBroadcastItem(i);
            if (null != onChangeListener) {
                try {
                    onChangeListener.onNewBook(book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
        mListeners.finishBroadcast();
    }

    @Override
    public void registerListener(OnChangeListener listener) throws RemoteException {
        mListeners.register(listener);
    }

    @Override
    public void unregisterListener(OnChangeListener listener) throws RemoteException {
        mListeners.unregister(listener);
    }

    private class WorkThread implements Runnable {
        @Override
        public void run() {
            while (isServiceAlive.get()) {
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Book book = new Book(String.valueOf(System.currentTimeMillis()));
                try {
                    onNewBookArrived(book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
