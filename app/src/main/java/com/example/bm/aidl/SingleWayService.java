package com.example.bm.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * 单向通信
 */
public class SingleWayService extends Service {
    private List<Book>  mBookList = new ArrayList<>();

    private IBookManager.Stub mbinder = new IBookManager.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBookList.add(book);
        }

        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookList;
        }

    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService", "onCreate");
    }

    /**
     * 客户端连接该服务就会调用此方法，并返回mbinder。
     * @param intent
     * @return
     */
    @Override
    public IBinder onBind(Intent intent) {
        return mbinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService", "onDestroy");
    }
}
