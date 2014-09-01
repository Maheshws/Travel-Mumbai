package ws.mahesh.travelmumbai;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.widget.Toast;

import com.parse.PushService;

import ws.mahesh.travelmumbai.misc.MegaBlockInfoActivity;
import ws.mahesh.travelmumbai.misc.WebViewActivity;

/**
 * Created by Mahesh on 7/17/2014.
 */
public class PreferencesActivity extends PreferenceActivity {
    SharedPreferences.Editor editor;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preference_holder);
        addPreferencesFromResource(R.xml.main_preferences);

        SharedPreferences pref = getSharedPreferences("user_settings", 0); // 0 - for private mode
        editor = pref.edit();
        final CheckBoxPreference Notification = (CheckBoxPreference) getPreferenceManager().findPreference("MegaBlock");
        Notification.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Log.d("MyApp", "Pref " + preference.getKey() + " changed to " + newValue.toString());
                editor.putBoolean("MegaBlock", (Boolean) newValue);
                editor.apply();
                if ((Boolean) newValue) {
                    PushService.setDefaultPushCallback(PreferencesActivity.this, null);
                    Notification.setSummary("Notifications are off for MegaBlock.");
                } else {
                    PushService.setDefaultPushCallback(PreferencesActivity.this, MegaBlockInfoActivity.class);
                    Notification.setSummary("Notifications are on for MegaBlock.");
                }

                return true;
            }
        });

        Preference CurrentVersion = findPreference("CurrentVersion");
        try {
            String versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            CurrentVersion.setSummary("v" + versionName);
            CurrentVersion.setTitle("Application Version");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        CurrentVersion.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Toast.makeText(PreferencesActivity.this, "Mahesh Liked IT!", Toast.LENGTH_LONG).show();
                return false;
            }
        });


        Preference AboutApp = findPreference("AboutApp");
        AboutApp.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                // startActivity(new Intent(PreferencesActivity.this, AboutVITACMActivity.class));
                return false;
            }
        });

        Preference MegaBlockInfo = findPreference("MegaBlockInfo");
        MegaBlockInfo.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startActivity(new Intent(PreferencesActivity.this, MegaBlockInfoActivity.class));
                return false;
            }
        });

        Preference RailMap = findPreference("RailMap");
        RailMap.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent i = new Intent(getApplicationContext(), WebViewActivity.class);
                i.putExtra("asset", true);
                i.putExtra("location", "railmap/railmap.html");
                startActivity(i);
                return false;
            }
        });

        editor.commit();
    }
}
