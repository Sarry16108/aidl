package com.example.bm.aidl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by liuheng on 2015/6/21.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void img(View view) {
    }

    public void viewpager(View view) {
    }

    public void imgclick(View view) {
    }

    public void photobrowse(View view) {
    }

    public void imageview(View view) {
    }



    public void bindService(View view) {
        startActivity(new Intent(this, ServiceActivity.class));
    }

}
