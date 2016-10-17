package com.njk.app.ui.admin;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.myapplication.R;
import com.njk.app.firebase.Firebase;
import com.njk.app.utils.Util;

import java.util.HashMap;
import java.util.Map;

public class AdminMessageEntry extends AppCompatActivity {

    TextInputLayout mMessageTittle, mMessageBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_message_entry);

        mMessageTittle = (TextInputLayout) findViewById(R.id.message_title);
        mMessageBody = (TextInputLayout) findViewById(R.id.message_body);

    }


    public void onMessageSubmit(View view) {

        String title = mMessageTittle.getEditText().getText().toString();
        String message = mMessageBody.getEditText().getText().toString();

        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(message)) {
            return;
        }

        long millisec = System.currentTimeMillis();

        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("title", title);
        stringMap.put("body", message);
        stringMap.put("time", "" + millisec);

        DatabaseReference messageRef = Firebase.getInstance().getReference("Maple").child("data").child("messages");
        messageRef.child("" + millisec).setValue(stringMap);

        Util.displayToast("Success");


    }
}
