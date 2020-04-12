package com.easyicon.learnglide.activity;

import android.app.backup.BackupManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.LocaleList;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.easyicon.learnglide.R;
import com.easyicon.learnglide.SettingsActivity;
import com.easyicon.learnglide.presenter.UserManager;
import com.easyicon.learnglide.service.MessengerService;
import com.easyicon.learnglide.util.Utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//import com.android.internal.app.LocalePicker;

public class PercentLayoutActivity extends AppCompatActivity {

    @BindView(R.id.text)
    TextView mTextView;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.change_system_language)
    Button changeSystemLanguage;
    @BindView(R.id.connect_to_messenger_service)
    Button connectToMessengerService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percent_layout);
        ButterKnife.bind(this);
        UserManager.sUerId = 2;
        mTextView.setText(String.valueOf(UserManager.sUerId));
        Log.d("QUIBBLER", "客户端：" + Utils.getProcessName(this, Process.myPid()));

        getLanguageAndCountry();

        /*
            给ImageView设置自定义Shape 一条线line
         */
        ImageView imageView = findViewById(R.id.image_view);
        imageView.setImageResource(R.drawable.shape_line);

//        toastTouchSlop();
    }

    /**
     * 查看源码：https://www.androidos.net.cn/android/10.0.0_r6/xref/frameworks/base/core/res/res/values-watch/config.xml
     * Base "touch slop" value used by ViewConfiguration as a movement threshold where scrolling should begin.
     * <dimen name="config_viewConfigurationTouchSlop">4dp</dimen>
     */
    private void toastTouchSlop() {
        Toast.makeText(this, "最小滑动距离TouchSlop：" + ViewConfiguration.get(this).getScaledTouchSlop() + "dp", Toast.LENGTH_SHORT).show();
    }


    @SuppressWarnings("deprecation")
    private void getLanguageAndCountry() {
        StringBuilder stringBuilder = new StringBuilder();

        Configuration configuration = getResources().getConfiguration();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LocaleList locales = configuration.getLocales();
            stringBuilder.append(locales.toString()).append("\n");

            String language = locales.get(0).getLanguage();
            stringBuilder.append("语言：").append(language).append("\n");

            String country = locales.get(0).getCountry();
            stringBuilder.append("地区：").append(country).append("\n");
        } else {
            Locale locale = configuration.locale;

            String language = locale.getLanguage();
            stringBuilder.append("语言：").append(language).append("\n");

            String country = locale.getCountry();
            stringBuilder.append("地区：").append(country).append("\n");
        }
//        configuration.locale = Locale.ENGLISH;
//        getResources().updateConfiguration(configuration, null);

        mTextView.setText(stringBuilder.toString());
    }


    @SuppressWarnings("deprecation")
    @OnClick({R.id.text, R.id.text1, R.id.change_system_language})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.text1:
                Resources resources = getResources();
                DisplayMetrics dm = resources.getDisplayMetrics();
                Configuration config = resources.getConfiguration();
                config.setLocale(Locale.ENGLISH);
                resources.updateConfiguration(config, dm);
                Intent intent1 = new Intent(this, PercentLayoutActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
                break;
            case R.id.change_system_language:
                changeSystemLanguage();
//                changeSystemLanguageSetting();
                break;
        }
    }

    private void changeSystemLanguageSetting() {
        Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
        startActivity(intent);
    }

    private void changeSystemLanguage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Log.d("QUIBBLER", "changeSystemLanguage(localeList)");
            LocaleList localeList = LocaleList.forLanguageTags("ja");
            changeSystemLanguage(localeList);
        } else {
            Log.d("QUIBBLER", "changeSystemLanguage(locale)");
            Locale locale = Locale.ENGLISH;
            changeSystemLanguage(locale);
        }
        sendBroadcast(new Intent("android.search.action.SETTINGS_CHANGED"));
//        LocalePicker.updateLocale(Locale.ENGLISH);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressWarnings("unchecked")
    protected void changeSystemLanguage(LocaleList locale) {
        if (locale != null) {
            try {
                Class classActivityManagerNative = Class.forName("android.app.ActivityManagerNative");
                Method getDefault = classActivityManagerNative.getDeclaredMethod("getDefault");
                Object objIActivityManager = getDefault.invoke(classActivityManagerNative);
                Class classIActivityManager = Class.forName("android.app.IActivityManager");
                Method getConfiguration = classIActivityManager.getDeclaredMethod("getConfiguration");
                Configuration config = (Configuration) getConfiguration.invoke(objIActivityManager);
                config.setLocales(locale);
                Class[] clzParams = {Configuration.class};
                Method updateConfiguration = classIActivityManager.getDeclaredMethod("updatePersistentConfiguration", clzParams);
                updateConfiguration.invoke(objIActivityManager, config);
            } catch (Exception e) {
                Log.d("QUIBBLER", "changeSystemLanguage LocaleList: " + e.toString());
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void changeSystemLanguage(Locale locale) {
        if (locale != null) {
            try {
                Class classActivityManagerNative = Class.forName("android.app.ActivityManagerNative");
                Method getDefault = classActivityManagerNative.getDeclaredMethod("getDefault");
                Object objIActivityManager = getDefault.invoke(classActivityManagerNative);
                Class classIActivityManager = Class.forName("android.app.IActivityManager");
                Method getConfiguration = classIActivityManager.getDeclaredMethod("getConfiguration");
                Configuration config = (Configuration) getConfiguration.invoke(objIActivityManager);
                config.setLocale(locale);
                //config.userSetLocale = true;
                Class clzConfig = Class
                        .forName("android.content.res.Configuration");
                Field userSetLocale = clzConfig
                        .getField("userSetLocale");
                userSetLocale.set(config, true);
                Class[] clzParams = {Configuration.class};
                Method updateConfiguration = classIActivityManager.getDeclaredMethod("updateConfiguration", clzParams);
                updateConfiguration.setAccessible(true);
                updateConfiguration.invoke(objIActivityManager, config);
                BackupManager.dataChanged("com.android.providers.settings");
            } catch (Exception e) {
                Log.d("QUIBBLER", "changeSystemLanguage Locale: " + e.toString());
            }
        }
    }


//    @OnClick(R.id.text)
//    public void onViewClicked() {
//
//    }

    private void testIntentFilter() {
        Intent intent = new Intent();
        intent.setAction("com.quibbler.action");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        PackageManager packageManager = getPackageManager();
        ResolveInfo resolveInfo = packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
//        List<ResolveInfo> resolveInfoList = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (null != resolveInfo) {
            startActivity(intent);
        }
    }

    private Messenger mMessenger;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMessenger = new Messenger(service);
            Message message = Message.obtain(null, 1);
            Bundle data = new Bundle();
            data.putString("msg", "hello,this is client");
            message.setData(data);
            try {
                mMessenger.send(message);
            } catch (RemoteException e) {
                Log.e("QUIBBLER", e.toString());
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @OnClick(R.id.connect_to_messenger_service)
    public void onViewClicked() {
        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }
}
