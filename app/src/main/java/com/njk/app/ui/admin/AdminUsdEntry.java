package com.njk.app.ui.admin;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.myapplication.R;
import com.njk.app.firebase.Firebase;

public class AdminUsdEntry extends AppCompatActivity {

    private static String TAG = "AdminUsdActivity-123456";
    private TextInputLayout textInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_usd_entry);

        textInputLayout = (TextInputLayout) findViewById(R.id.usd_entry);


    }


    public void onSubmit(View view) {


        String value = textInputLayout.getEditText().getText().toString();
        Double dValue = Double.parseDouble(value);

        Firebase.getInstance().getReference("GlobalMarket").child("data").child("usd").setValue(dValue);

    }
}
