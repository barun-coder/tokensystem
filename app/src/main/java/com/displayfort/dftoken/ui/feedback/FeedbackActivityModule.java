
package com.displayfort.dftoken.ui.feedback;

import com.displayfort.dftoken.data.DataManager;
import com.displayfort.dftoken.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;


@Module
public class FeedbackActivityModule {

    @Provides
    TokenViewModel addProfessionalExpViewModel(DataManager dataManager,
                                               SchedulerProvider schedulerProvider) {
        return new TokenViewModel(dataManager, schedulerProvider);
    }




}
