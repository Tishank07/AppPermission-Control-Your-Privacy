package com.example.dummy;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AppPermissionsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_permissions);

        TextView appNameTextView = findViewById(R.id.appNameTextView);
        TextView packageNameTextView = findViewById(R.id.packageNameTextView);
        TextView permissionsTextView = findViewById(R.id.permissionsTextView);
        ImageView ivIcon = findViewById(R.id.appIconIV);

        // Get the selected AppInfo object passed from the previous activity
        AppInfo1 selectedApp = getIntent().getParcelableExtra("selectedApp");


        try {
            ivIcon.setImageDrawable(getPackageManager().getApplicationInfo(selectedApp.getPackageName(), 0).loadIcon(getPackageManager()));
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (selectedApp != null) {
            // Display app information in the TextViews
            appNameTextView.setText("App Name: " + selectedApp.getAppName());
            packageNameTextView.setText("Package Name: " + selectedApp.getPackageName());

            // Get and display app permissions
            String[] permissions = getAppPermissions(selectedApp.getPackageName());
            if (permissions != null) {
/*                StringBuilder permissionText = new StringBuilder("Permissions:\n");
                for (String permission : permissions) {
                    permissionText.append(permission).append("\n");
                }
                permissionsTextView.setText(permissionText.toString());*/
                permissionsTextView.setText("Total Permissions " + permissions.length);
                ListView listView = findViewById(R.id.app_permissions_list_view);
                ArrayAdapter<String> permissionAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, permissions);
                listView.setAdapter(permissionAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(AppPermissionsActivity.this, permissions[i], Toast.LENGTH_SHORT).show();
                    }
                });


            } else {
                permissionsTextView.setText("No Permission Found");
            }




        }
    }

    // Helper method to retrieve app permissions based on the package name
    private String[] getAppPermissions(String packageName) {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);
            return packageInfo.requestedPermissions;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
