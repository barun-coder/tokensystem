
package com.displayfort.dftoken.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.displayfort.dftoken.BuildConfig;
import com.displayfort.dftoken.R;
import com.displayfort.dftoken.data.AppDataManager;
import com.displayfort.dftoken.data.DataManager;
import com.displayfort.dftoken.data.local.db.AppDatabase;
import com.displayfort.dftoken.data.local.db.AppDbHelper;
import com.displayfort.dftoken.data.local.db.DbHelper;
import com.displayfort.dftoken.data.local.prefs.AppPreferencesHelper;
import com.displayfort.dftoken.data.local.prefs.PreferencesHelper;
import com.displayfort.dftoken.data.remote.ApiHeader;
import com.displayfort.dftoken.data.remote.ApiHelper;
import com.displayfort.dftoken.data.remote.AppApiHelper;
import com.displayfort.dftoken.di.ApiInfo;
import com.displayfort.dftoken.di.DatabaseInfo;
import com.displayfort.dftoken.di.PreferenceInfo;
import com.displayfort.dftoken.utils.AppConstants;
import com.displayfort.dftoken.utils.rx.AppSchedulerProvider;
import com.displayfort.dftoken.utils.rx.SchedulerProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Yogesh  on 07/07/17.
 */
@Module
public class AppModule {

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.token_code;
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName, Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName).fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(@ApiInfo String apiKey,
                                                           PreferencesHelper preferencesHelper) {
        return new ApiHeader.ProtectedApiHeader(
                apiKey,
                preferencesHelper.getCurrentUserId(),
                preferencesHelper.getAccessToken());
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }
}
