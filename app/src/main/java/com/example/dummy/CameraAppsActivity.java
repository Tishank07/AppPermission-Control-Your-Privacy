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

public class CameraAppsActivity extends AppCompatActivity {

    private List<AppInfo> cameraAppsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_apps_list);

        cameraAppsList = new ArrayList<>();
        PackageManager pm = getPackageManager();
        String permission = "android.permission.CAMERA";

        // Get a list of apps with camera permission
        ApplicationInfo[] apps = pm.getInstalledApplications(PackageManager.GET_META_DATA).toArray(new ApplicationInfo[0]);
        for (ApplicationInfo appInfo : apps) {
            try {
                PermissionInfo info = pm.getPermissionInfo(permission, 0);
                if (info != null && pm.checkPermission(permission, appInfo.packageName) == PackageManager.PERMISSION_GRANTED) {
                    // This app has camera permission
                    // Get app icon, name, and package name
                    Drawable appIcon = pm.getApplicationIcon(appInfo);
                    CharSequence appName = pm.getApplicationLabel(appInfo);
                    String packageName = appInfo.packageName;

                    // Add app info to the list
                    cameraAppsList.add(new AppInfo(appIcon, appName.toString(), packageName));
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }

        // Display the list of apps with camera permission using a custom adapter
        AppListAdapter adapter = new AppListAdapter(this, cameraAppsList);
        ListView listView = findViewById(R.id.listViewCameraApps);
        listView.setAdapter(adapter);
    }
}
