
package com.displayfort.dftoken.di.builder;


import com.displayfort.dftoken.ui.adminLogin.AdminLoginActivity;
import com.displayfort.dftoken.ui.feedback.TokenActivity;
import com.displayfort.dftoken.ui.feedback.FeedbackActivityModule;
import com.displayfort.dftoken.ui.login.LoginActivity;
import com.displayfort.dftoken.ui.splash.SplashActivity;
import com.displayfort.dftoken.ui.thankyou.ThankyouActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Husain  on 14/09/17.
 */
@Module
public abstract class ActivityBuilder {


    @ContributesAndroidInjector
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector
    abstract AdminLoginActivity bindAdminLoginActivity();

    @ContributesAndroidInjector
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector
    abstract ThankyouActivity bindThankyouActivity();

    @ContributesAndroidInjector(modules = {FeedbackActivityModule.class})
    abstract TokenActivity bindFeedBackActivity();


}
