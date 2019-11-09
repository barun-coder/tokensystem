
package com.displayfort.dftoken.ui.thankyou;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.displayfort.dftoken.BR;
import com.displayfort.dftoken.R;
import com.displayfort.dftoken.ViewModelProviderFactory;
import com.displayfort.dftoken.databinding.ActivityThankyouBinding;
import com.displayfort.dftoken.ui.base.BaseActivity;
import com.displayfort.dftoken.ui.base.BaseAnimation;
import com.displayfort.dftoken.ui.feedback.TokenActivity;
import com.displayfort.dftoken.ui.login.LoginActivity;

import javax.inject.Inject;


public class ThankyouActivity extends BaseActivity<ActivityThankyouBinding, ThankyouViewModel> implements ThankyouNavigator {

    @Inject
    ViewModelProviderFactory factory;
    ActivityThankyouBinding ActivityThankyouBinding;
    private ThankyouViewModel mSplashViewModel;
    private boolean isAlreadyChanged = false;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_thankyou;
    }

    @Override
    public ThankyouViewModel getViewModel() {
        mSplashViewModel = ViewModelProviders.of(this, factory).get(ThankyouViewModel.class);
        return mSplashViewModel;
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, ThankyouActivity.class);
    }

    @Override
    public void openLoginActivity() {
        Intent intent = LoginActivity.newIntent(ThankyouActivity.this);
        startActivity(intent);
        BaseAnimation.setAnimationTransition(this, BaseAnimation.EFFECT_TYPE.TAB_ZOOM);
        finish();
    }

    @Override
    public void openMainActivity() {
        Intent intent = TokenActivity.newIntent(ThankyouActivity.this);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        BaseAnimation.setAnimationTransition(this, BaseAnimation.EFFECT_TYPE.TAB_ZOOM);
        finish();
    }

    @Override
    public void onGoBack() {
        isAlreadyChanged = true;
        openMainActivity();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSplashViewModel.setNavigator(this);
        ActivityThankyouBinding = getViewDataBinding();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isAlreadyChanged) {
                    openMainActivity();
                }
            }
        }, 6000);


    }

}
