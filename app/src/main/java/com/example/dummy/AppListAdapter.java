package com.example.dummy;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AppListAdapter extends ArrayAdapter<AppInfo> {
    private Context context;
    private List<AppInfo> appInfoList;

    public AppListAdapter(@NonNull Context context, List<AppInfo> appInfoList) {
        super(context, R.layout.app_info, appInfoList);
        this.context = context;
        this.appInfoList = appInfoList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.app_info, parent, false);
        }

        AppInfo appInfo = appInfoList.get(position);

        ImageView appIcon = convertView.findViewById(R.id.appIcon);
        TextView appName = convertView.findViewById(R.id.appName);
        TextView packageName = convertView.findViewById(R.id.packageName);

        appIcon.setImageDrawable(appInfo.getAppIcon());
        appName.setText(appInfo.getAppName());
        packageName.setText(appInfo.getPackageName());

        return convertView;
    }
}
