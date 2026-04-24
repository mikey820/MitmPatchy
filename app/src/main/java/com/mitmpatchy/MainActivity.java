package com.mitmpatchy;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AppListAdapter adapter;
    private List<AppInfo> appList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadInstalledApps();

        adapter = new AppListAdapter(appList, appInfo -> {
            new AlertDialog.Builder(MainActivity.this)
                .setTitle("Patch App")
                .setMessage("Do you want to MITM patch this app?\n\n" + appInfo.name)
                .setPositiveButton("Yes", (dialog, which) -> {
                    PatchActivity.start(MainActivity.this, appInfo.packageName, appInfo.name);
                })
                .setNegativeButton("No", null)
                .show();
        });
        recyclerView.setAdapter(adapter);
    }

    private void loadInstalledApps() {
        PackageManager pm = getPackageManager();
        List<ApplicationInfo> apps = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        appList.clear();
        for (ApplicationInfo app : apps) {
            String name = pm.getApplicationLabel(app).toString();
            Drawable icon = pm.getApplicationIcon(app);
            appList.add(new AppInfo(name, app.packageName, icon));
        }
        Collections.sort(appList, (a, b) -> a.name.compareToIgnoreCase(b.name));
    }
}
