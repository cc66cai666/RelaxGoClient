package com.cjh.relaxgo.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    private Context context;
    protected static Toast toast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
    }

    /**
     * 设置Toast
     */
    public void setToast(String msg){
        try {

            if (toast == null){
                toast = Toast.makeText(context,msg,Toast.LENGTH_SHORT);
            }else {
                toast.setText(msg);
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    toast.show();
                }
            });

        }catch (Exception e){

            e.printStackTrace();
            //解决在子线程中调用Toast的异常情况处理
            Looper.prepare();
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            Looper.loop();

        }
    }

    public void setLog(String msg){

        Log.e(context.getClass().getSimpleName(), msg);

    }
}
