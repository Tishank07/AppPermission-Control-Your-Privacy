package com.example.dummy;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AppListActivity extends AppCompatActivity {

    private ListView listView;
    private boolean isSystemApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);

        listView = findViewById(R.id.appListView);
        isSystemApp = getIntent().getBooleanExtra("isSystemApp", true);

        // Get a list of installed apps
        ArrayList<AppInfo1> appList = getInstalledApps();

        // Create an ArrayAdapter to display the app names
        AppListAdapter1 adapter = new AppListAdapter1(this, appList);
        listView.setAdapter(adapter);

        // Set a click listener to show app permissions when an item is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AppInfo1 selectedApp = appList.get(position);

                // Launch the AppPermissionsActivity and pass the selectedApp data
                Intent intent = new Intent(AppListActivity.this, AppPermissionsActivity.class);
                intent.putExtra("selectedApp", selectedApp);
                startActivity(intent);
            }
        });
    }

    // Get a list of installed apps based on whether they are system apps or third-party apps
    private ArrayList<AppInfo1> getInstalledApps() {
        ArrayList<AppInfo1> appList = new ArrayList<>();
        PackageManager packageManager = getPackageManager();
        List<PackageInfo> packages = packageManager.getInstalledPackages(0);

        for (PackageInfo packageInfo : packages) {
            boolean isSystem = (packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
            if ((isSystemApp && isSystem) || (!isSystemApp && !isSystem)) {
                String appName = packageInfo.applicationInfo.loadLabel(packageManager).toString();
                String packageName = packageInfo.packageName;
                Drawable appIcon = packageInfo.applicationInfo.loadIcon(packageManager);
                /*int numPermissions = packageInfo.requestedPermissions != null ?
                        packageInfo.requestedPermissions.length : 0;*/

                int numPermissions = 0;

                try {
                    String [] requestedPermissions = packageManager.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS).requestedPermissions;
                    if (requestedPermissions != null) {
                        numPermissions = requestedPermissions.length;
/*                        for (String p : requestedPermissions)
                        {
                            Log.d("PermissionTest", "Package: " + packageName + ", Permission: " + p);
                        }*/
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    throw new RuntimeException(e);
                }


                AppInfo1 appInfo = new AppInfo1(appName, packageName, numPermissions, appIcon);
                appList.add(appInfo);
            }
        }

        return appList;
    }
}