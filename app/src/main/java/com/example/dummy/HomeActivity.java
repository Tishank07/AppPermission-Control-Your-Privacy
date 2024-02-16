package com.example.dummy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ImageButton btnCamera = findViewById(R.id.btnCamera);
        ImageButton btnMicrophone = findViewById(R.id.btnMicrophone);
        ImageButton btnLocation = findViewById(R.id.btnLocation);
        ImageButton btnSystemApps = findViewById(R.id.btnSystemApps);
        ImageButton btnThirdPartyApps = findViewById(R.id.btnThirdPartyApps);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the CameraAppsActivity
                Intent intent = new Intent(HomeActivity.this, CameraAppsActivity.class);
                startActivity(intent);
            }
        });

        btnMicrophone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the MicrophoneAppsActivity
                Intent intent = new Intent(HomeActivity.this, MicrophoneAppsActivity.class);
                startActivity(intent);
            }
        });

        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the LocationAppsActivity
                Intent intent = new Intent(HomeActivity.this, LocationAppsActivity.class);
                startActivity(intent);
            }
        });

        btnSystemApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch the AppListActivity for system apps
                Intent intent = new Intent(HomeActivity.this, AppListActivity.class);
                intent.putExtra("isSystemApp", true);
                startActivity(intent);
            }
        });

        btnThirdPartyApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch the AppListActivity for third-party apps
                Intent intent = new Intent(HomeActivity.this, AppListActivity.class);
                intent.putExtra("isSystemApp", false);
                startActivity(intent);
            }
        });

    }
}