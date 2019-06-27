package com.example.bm.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


/**
 * 双向通信
 */
public class BothwayService extends Service {
    private List<Book>  mBookList = new ArrayList<>();
    private IListener   mIListener;


    private ICallback.Stub mbinder = new ICallback.Stub() {


        @Override
        public void setListener(IListener lst) throws RemoteException {
            mIListener = lst;
        }

        @Override
        public void sendMSGtoService(String msg) throws RemoteException {
            Log.d("MyNewService", msg);
            mIListener.sendMsgtoClient(msg);
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
