package com.njk.app.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import com.myapplication.BuildConfig;
import com.myapplication.R;
import com.njk.app.ui.admin.AdminHomeActivity;


public class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {

    private Preference mVersion,mRateUs;
    private int i = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().setTitle("Settings");

        PreferenceManager manager = getPreferenceManager();
        manager.setSharedPreferencesName("settings");

        addPreferencesFromResource(R.xml.app_settings);

        mVersion = findPreference(getString(R.string.key_version));
        mRateUs = findPreference(getString(R.string.key_rate_us));
        mVersion.setSummary(BuildConfig.VERSION_NAME);

        mVersion.setOnPreferenceClickListener(this);
        mRateUs.setOnPreferenceClickListener(this);

    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        if (preference == mVersion) {
            i = i + 1;
            if (i == 5) {
                i = -1;
                startActivity(new Intent(getActivity(), AdminHomeActivity.class));
            }
            return true;
        }else if(mRateUs == preference){
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=com.ttjj.app"));
            startActivity(intent);
        }
        return false;
    }
}
