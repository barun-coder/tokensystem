

package com.displayfort.dftoken;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.displayfort.dftoken.BuildConfig;
import com.displayfort.dftoken.di.component.DaggerAppComponent;
import com.displayfort.dftoken.ui.feedback.TokenActivity;
import com.displayfort.dftoken.utils.AppLogger;
import com.onesignal.OSNotification;
import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class DfTokenApplication extends Application implements HasActivityInjector {
    private static Context context;
    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Inject
    CalligraphyConfig mCalligraphyConfig;

    public static Context getContext() {
        return DfTokenApplication.context;
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        Mint.initAndStartSession(this, "3ec84e46");
        DfTokenApplication.context = getApplicationContext();
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);

        AppLogger.init();

        AndroidNetworking.initialize(getApplicationContext());
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
        }
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(false)
                .setNotificationReceivedHandler(new ExampleNotificationReceivedHandler())
                .setNotificationOpenedHandler(new ExampleNotificationOpenedHandler())
                .init();
        CalligraphyConfig.initDefault(mCalligraphyConfig);
    }

    private class ExampleNotificationReceivedHandler implements OneSignal.NotificationReceivedHandler {
        @Override
        public void notificationReceived(OSNotification notification) {
            Log.d("OneSignal", "notificationReceived");
            JSONObject jsonObject = notification.payload.toJSONObject();
            try {
                jsonObject.put("time", System.currentTimeMillis());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    private class ExampleNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
        private Intent intent;

        @Override
        public void notificationOpened(OSNotificationOpenResult result) {

            Log.d("OneSignal", "notificationOpened");
            OSNotificationAction.ActionType actionType = result.action.type;
            JSONObject data = result.notification.payload.additionalData;
            String customKey;
//            if (data != null && data.has("cartID")) {
//                intent = new Intent(context, CartActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);
//            } else {
            intent = new Intent(context, TokenActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
//            }
            if (data != null) {
                customKey = data.optString("customkey", null);
                if (customKey != null)
                    Log.e("OneSignalExample", "customkey set with value: " + customKey);
            }

            if (actionType == OSNotificationAction.ActionType.ActionTaken) {
                Log.i("OneSignalExample", "Button pressed with id: " + result.action.actionID);


            }
        }
    }
}
