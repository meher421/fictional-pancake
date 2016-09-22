package com.njk.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import com.myapplication.BuildConfig;
import com.myapplication.R;
import com.njk.app.ui.admin.AdminHomeActivity;


public class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {

    private Preference mVersion;
    private int i = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().setTitle("Settings");

        PreferenceManager manager = getPreferenceManager();
        manager.setSharedPreferencesName("settings");

        addPreferencesFromResource(R.xml.app_settings);

        mVersion = findPreference(getString(R.string.key_version));
        mVersion.setSummary(BuildConfig.VERSION_NAME);

        mVersion.setOnPreferenceClickListener(this);

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
        }
        return false;
    }
}
