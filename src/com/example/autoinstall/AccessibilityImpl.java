package com.example.autoinstall;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.RemoteViews.ActionException;

public class AccessibilityImpl extends AccessibilityService {
    private static final String TAG = "yxd";
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        get(event);
    }
    @Override
    public void onInterrupt() {}
    public void get(AccessibilityEvent event)
    {
        AccessibilityNodeInfo rowNode = getRootInActiveWindow();
        if (rowNode == null) {
            Log.i(TAG, "noteInfo is　null");
            return;
        } else {
            recycle(rowNode);
        }
        Log.i(TAG, "==============================================");

    }
    public void recycle(AccessibilityNodeInfo info) {
        if (info.getChildCount() == 0) {
            Log.i(TAG, "child widget----------------------------" + info.getClassName());
            Log.i(TAG, "Text：" + info.getText());
            if((info.getText()+"").equals("安装"))
            {
                info.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
            Log.i(TAG, "windowId:" + info.getWindowId());
        } else {
            for (int i = 0; i < info.getChildCount(); i++) {
                if(info.getChild(i)!=null){
                    recycle(info.getChild(i));
                }
            }
        }
    }
}