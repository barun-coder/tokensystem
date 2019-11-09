package com.displayfort.dftoken;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.displayfort.dftoken.data.DataManager;
import com.displayfort.dftoken.ui.feedback.TokenViewModel;
import com.displayfort.dftoken.ui.login.LoginViewModel;
import com.displayfort.dftoken.ui.splash.SplashViewModel;
import com.displayfort.dftoken.ui.thankyou.ThankyouViewModel;
import com.displayfort.dftoken.utils.rx.SchedulerProvider;

import javax.inject.Inject;

public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

    private final DataManager dataManager;
    private final SchedulerProvider schedulerProvider;

    @Inject
    public ViewModelProviderFactory(DataManager dataManager,
                                    SchedulerProvider schedulerProvider) {
        this.dataManager = dataManager;
        this.schedulerProvider = schedulerProvider;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            //noinspection unchecked
            return (T) new LoginViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(SplashViewModel.class)) {
            //noinspection unchecked
            return (T) new SplashViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(TokenViewModel.class)) {
            //noinspection unchecked
            return (T) new TokenViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ThankyouViewModel.class)) {
            //noinspection unchecked
            return (T) new ThankyouViewModel(dataManager, schedulerProvider);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
