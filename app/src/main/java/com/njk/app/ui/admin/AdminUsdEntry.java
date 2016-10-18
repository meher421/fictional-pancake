package com.njk.app.ui.admin;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.myapplication.R;
import com.njk.app.firebase.Firebase;
import com.njk.app.utils.HttpThread;
import com.njk.app.utils.Util;

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
        if (TextUtils.isEmpty(value)) {
            Util.displayToast("enter the value");
            return;
        }
        Double dValue = Double.parseDouble(value);

        Firebase.getInstance().getDatabase().getReference("GlobalMarket").child("data").child("usd").setValue(dValue);

        DatabaseReference dateRef = Firebase.getInstance().getDatabase().getReference("Market").child(Util.getTodayDateInMills());

        dateRef.child("dollar").setValue(dValue);

        textInputLayout.getEditText().setText("");
        Util.displayToast("Success");

        new HttpThread().start();


    }
}
