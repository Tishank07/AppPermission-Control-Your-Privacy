package com.example.dummy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AppListAdapter1 extends BaseAdapter {

    private Context context;
    private ArrayList<AppInfo1> mList;

    public AppListAdapter1(Context context, ArrayList<AppInfo1> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = LayoutInflater.from(context).inflate(R.layout.simple_list_item_single_choice, viewGroup, false);

        TextView tvAppName = view.findViewById(R.id.tv_application_name);
        TextView tvAppPackageName = view.findViewById(R.id.tv_application_package);
        TextView tvAppPermissionCount = view.findViewById(R.id.tv_application_num_permissions);
        ImageView ivAppIcon = view.findViewById(R.id.iv_app_icon);

        tvAppName.setText(mList.get(i).getAppName());
        tvAppPackageName.setText(mList.get(i).getPackageName());
        tvAppPermissionCount.setText("Total Permissions:" + mList.get(i).getNumPermissions());
        ivAppIcon.setImageDrawable(mList.get(i).getAppIcon());

        return view;
    }
}
