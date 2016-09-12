package com.njk.app.ui.admin;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.FirebaseDatabase;
import com.myapplication.R;

public class AdminUsdEntry extends AppCompatActivity {

    private static String TAG ="AdminUsdActivity-123456";
    private TextInputLayout textInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_usd_entry);

        textInputLayout =(TextInputLayout) findViewById(R.id.usd_entry);


    }


    public void onSubmit(View view){


        String value = textInputLayout.getEditText().getText().toString();
        Double dValue = Double.parseDouble(value);

        FirebaseDatabase.getInstance().getReference("GlobalMarket").child("data").child("usd").setValue(dValue);

    }
}
