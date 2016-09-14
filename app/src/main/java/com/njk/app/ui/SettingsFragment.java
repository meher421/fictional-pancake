package com.njk.app.ui;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import com.myapplication.BuildConfig;
import com.myapplication.R;


public class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {

    private Preference mVersion;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().setTitle("Settings");

        PreferenceManager manager = getPreferenceManager();
        manager.setSharedPreferencesName("settings");

        addPreferencesFromResource(R.xml.app_settings);

        mVersion = findPreference(getString(R.string.key_version));
        mVersion.setSummary(BuildConfig.VERSION_NAME);


    }

    @Override
    public boolean onPreferenceClick(Preference preference) {

        return false;
    }
}
