
package com.displayfort.dftoken.ui.thankyou;

import com.displayfort.dftoken.data.DataManager;
import com.displayfort.dftoken.ui.base.BaseViewModel;
import com.displayfort.dftoken.utils.rx.SchedulerProvider;


public class ThankyouViewModel extends BaseViewModel<ThankyouNavigator> {

    public ThankyouViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onGoBack() {
        getNavigator().onGoBack();
    }


}

