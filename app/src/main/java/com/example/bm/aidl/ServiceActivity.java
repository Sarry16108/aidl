package com.example.bm.aidl;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by liuheng on 2015/6/21.
 */
public class ServiceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private ICallback   mBinder;
    private ServiceConnection   mNewConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinder = ICallback.Stub.asInterface(service);
            try {
                mBinder.setListener(new IListener.Stub() {
                    @Override
                    public void sendMsgtoClient(String msg) {
                        Log.d("ServiceActivity", "client: " + msg);
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public void img(View view) {
        Intent intent = new Intent();
        intent.setPackage(getPackageName());
        intent.setAction("photoview.MyNewService");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.bindService(intent, mNewConnection, BIND_AUTO_CREATE);
    }

    public void viewpager(View view) {
        try {
            mBinder.sendMSGtoService("哈哈哈哈啊");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void imgclick(View view) {
    }

    public void photobrowse(View view) {
    }

    public void imageview(View view) {
    }




    private IBookManager    mIBookManager;
    private ServiceConnection   mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIBookManager = IBookManager.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIBookManager = null;
        }
    };


    public void bindService(View view) {
        Intent intent = new Intent();
        intent.setAction("photoview.MyService");
        intent.setPackage(getPackageName());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
        Toast.makeText(this, "创建成功", Toast.LENGTH_SHORT).show();
    }

    public void addBook(View view) {
        try {
            mIBookManager.addBook(new Book(0, "上下五千年"));
            Toast.makeText(this, "添加成功：" + mIBookManager.getBookList().size(), Toast.LENGTH_SHORT).show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        this.unbindService(mServiceConnection);
        this.unbindService(mNewConnection);
        super.onDestroy();
    }
}
