package com.njk.app.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.myapplication.R;

public class AdminHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

    }


    public void onMarketEntryClick(View view) {

        Intent intent = new Intent(AdminHomeActivity.this, AdminMarketEntry.class);
        startActivity(intent);

    }

    public void onMessageEntryClick(View view) {
        Intent intent = new Intent(AdminHomeActivity.this, AdminMessageEntry.class);
        startActivity(intent);

    }

    public void onUsdEntryClick(View view) {
        Intent intent = new Intent(AdminHomeActivity.this, AdminUsdEntry.class);
        startActivity(intent);
    }

    public void onMiscClick(View view) {
        Intent intent = new Intent(AdminHomeActivity.this, AdminMiscActvity.class);
        startActivity(intent);
    }
}
