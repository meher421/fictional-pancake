package com.njk.app.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.myapplication.R;

public class AdminHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

//        Button market = (Button) findViewById(R.id.market_entry);
//
//        Button message = (Button) findViewById(R.id.message_entry);
//
//        Button usd = (Button) findViewById(R.id.usd_entry);


    }


    public void onMarketEntryClick(View view) {

        Intent intent = new Intent(AdminHomeActivity.this,AdminMarketEntry.class);
        startActivity(intent);

    }

    public void onMessageEntryClick(View view) {
        Intent intent = new Intent(AdminHomeActivity.this,AdminMessageEntry.class);
        startActivity(intent);

    }

    public void onUsdEntryClick(View view) {
        Intent intent = new Intent(AdminHomeActivity.this,AdminUsdEntry.class);
        startActivity(intent);
    }
}
