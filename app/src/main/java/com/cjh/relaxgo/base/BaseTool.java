package com.cjh.relaxgo.base;

import android.content.Context;
import android.util.Log;

public class BaseTool {

    private Context context;
    private String className;

    public BaseTool(Context context,String className) {
        this.context = context;
        this.className = className;

    }

    public void setLog(String msg){
        Log.e(className,msg);
    }
}
