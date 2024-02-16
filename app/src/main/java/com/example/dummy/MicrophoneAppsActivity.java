package com.example.dummy;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MicrophoneAppsActivity extends AppCompatActivity {

    private List<AppInfo> microphoneAppsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.microphone_apps_list);

        microphoneAppsList = new ArrayList<>();
        PackageManager pm = getPackageManager();
        String permission = "android.permission.RECORD_AUDIO";

        // Get a list of apps with microphone permission
        ApplicationInfo[] apps = pm.getInstalledApplications(PackageManager.GET_META_DATA).toArray(new ApplicationInfo[0]);
        for (ApplicationInfo appInfo : apps) {
            try {
                PermissionInfo info = pm.getPermissionInfo(permission, 0);
                if (info != null && pm.checkPermission(permission, appInfo.packageName) == PackageManager.PERMISSION_GRANTED) {
                    // This app has microphone permission
                    // Get app icon, name, and package name
                    Drawable appIcon = pm.getApplicationIcon(appInfo);
                    CharSequence appName = pm.getApplicationLabel(appInfo);
                    String packageName = appInfo.packageName;

                    // Add app info to the list
                    microphoneAppsList.add(new AppInfo(appIcon, appName.toString(), packageName));
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }

        // Display the list of apps with microphone permission using a custom adapter
        AppListAdapter adapter = new AppListAdapter(this, microphoneAppsList);
        ListView listView = findViewById(R.id.listViewMicrophoneApps);
        listView.setAdapter(adapter);
    }
}


