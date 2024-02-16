package com.example.dummy;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class AppInfo1 implements Parcelable{
    private String appName;
    private String packageName;
    private int numPermissions;

    private Drawable appIcon;

    public AppInfo1(String appName, String packageName, int numPermissions, Drawable appIcon) {
        this.appName = appName;
        this.packageName = packageName;
        this.numPermissions = numPermissions;
        this.appIcon = appIcon;
    }

    public AppInfo1() {
    }


    protected AppInfo1(Parcel in) {
        appName = in.readString();
        packageName = in.readString();
        numPermissions = in.readInt();
    }

    public static final Creator<AppInfo1> CREATOR = new Creator<AppInfo1>() {
        @Override
        public AppInfo1 createFromParcel(Parcel in) {
            return new AppInfo1(in);
        }

        @Override
        public AppInfo1[] newArray(int size) {
            return new AppInfo1[size];
        }
    };

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getNumPermissions() {
        return numPermissions;
    }

    public void setNumPermissions(int numPermissions) {
        this.numPermissions = numPermissions;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(appName);
        parcel.writeString(packageName);
        parcel.writeInt(numPermissions);
    }
}

