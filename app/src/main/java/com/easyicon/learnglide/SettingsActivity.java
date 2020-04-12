package com.easyicon.learnglide;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends AppCompatActivity {

    PreferenceFragmentCompat mSettingsFragment = new SettingsFragment();
    @BindView(R.id.add)
    Button mAddButton;
    @BindView(R.id.delete)
    Button mDeleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        ButterKnife.bind(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, mSettingsFragment)
                .commit();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @OnClick({R.id.add, R.id.delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add:
                add();
                break;
            case R.id.delete:
                delete();
                break;
        }
    }

    private void delete() {
        PreferenceManager preferenceManager = mSettingsFragment.getPreferenceManager();
        Preference preference = preferenceManager.findPreference("muti_select");
        if (null != preference) {
            PreferenceScreen preferenceScreen = mSettingsFragment.getPreferenceScreen();
            if (null != preference) {
                preferenceScreen.removePreference(preference);
            }

            PreferenceCategory preferenceCategory = preferenceManager.findPreference("key_category");
            if (null != preferenceCategory) {
                preferenceCategory.removePreference(preferenceScreen);
            }
        }
    }

    private void add() {
        Preference preference = new Preference(this);
        preference.setTitle("添加新的设置");
        preference.setKey("pref_new_key");

        PreferenceManager preferenceManager = mSettingsFragment.getPreferenceManager();
        PreferenceCategory preferenceCategory = preferenceManager.findPreference("key_category");

        preferenceCategory.addPreference(preference);

//        PreferenceScreen preferenceScreen = mSettingsFragment.getPreferenceScreen();
//        preferenceScreen.addPreference(preference);
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            Preference preference = getPreferenceManager().findPreference("jump");
            preference.setOnPreferenceClickListener(preferenceClickListener);
            preference.setOnPreferenceChangeListener(null);

            findPreference("change").setOnPreferenceChangeListener(preferenceChangeListener);
        }

        private Preference.OnPreferenceChangeListener preferenceChangeListener = new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                //做业务逻辑处理
                boolean result = (boolean) newValue;
                if (result) {
                    // 返回true  本次值变化生效
                    return true;
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("选择")
                            .setMessage("是否确定关闭？")
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //用户选择取消，不关闭。直接关闭Dialog即可，因为后面已经返回了false，本次修改不造成变化
                                    dialog.dismiss();
                                }
                            })
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //用户选择确定，那么本次值虽然后面已经返回false，不再接受，但是这里可以继续根据用户的选择更改。
                                }
                            })
                            .create()
                            .show();
                    // 返回false 本次变化失效。在Dialog中通过选择控制值。
                    return false;
                }
            }
        };

        private Preference.OnPreferenceClickListener preferenceClickListener = new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                String key = preference.getKey();
                if ("jump".equals(key)) {
                    Toast.makeText(getActivity(), "点击jump跳转", Toast.LENGTH_SHORT).show();

//                    Intent intent = new Intent();
//                    intent.setAction("com.quibbler.jump");
//                    getActivity().startActivity(intent);
                }
                return false;
            }
        };
    }
}