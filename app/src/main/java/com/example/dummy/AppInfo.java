package com.example.dummy;

import android.graphics.drawable.Drawable;

public class AppInfo {
    private Drawable appIcon;
    private String appName;
    private String packageName;



    public AppInfo(Drawable appIcon, String appName, String packageName) {
        this.appIcon = appIcon;
        this.appName = appName;
        this.packageName = packageName;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public String getAppName() {
        return appName;
    }

    public String getPackageName() {
        return packageName;
    }
}

