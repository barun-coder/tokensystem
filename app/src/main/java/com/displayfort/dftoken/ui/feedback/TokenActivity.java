
package com.displayfort.dftoken.ui.feedback;

import android.animation.Animator;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Html;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.androidnetworking.error.ANError;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.displayfort.dftoken.BR;
import com.displayfort.dftoken.NrFtPrefrence;
import com.displayfort.dftoken.OnValueReturn;
import com.displayfort.dftoken.R;
import com.displayfort.dftoken.ViewModelProviderFactory;
import com.displayfort.dftoken.data.local.prefs.AppPreferencesHelper;
import com.displayfort.dftoken.data.model.api.ApiError;
import com.displayfort.dftoken.data.model.api.response.TokenResponse;
import com.displayfort.dftoken.databinding.ActivityTokenDetailBinding;
import com.displayfort.dftoken.ui.base.BaseActivity;
import com.displayfort.dftoken.ui.login.LoginActivity;
import com.displayfort.dftoken.utils.AppConstants;
import com.displayfort.dftoken.utils.DialogUtils;
import com.displayfort.dftoken.utils.Utility;

import java.util.List;

import javax.inject.Inject;


public class TokenActivity extends BaseActivity<ActivityTokenDetailBinding, TokenViewModel> implements TokenNavigator {
    private static final long TIME_ELAPSE = 800;
    @Inject
    ViewModelProviderFactory factory;
    private TokenViewModel tokenViewModel;
    private ActivityTokenDetailBinding mActivityTokenDetailBinding;
    private int tokenId = 0;
    private String tokenDisplayName = "";
    private int call = 0;

    public static Intent newIntent(Context context) {
        return new Intent(context, TokenActivity.class);
    }

    //6A361A42EE6C5A83
    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_token_detail;
    }

    @Override
    public TokenViewModel getViewModel() {
        tokenViewModel = ViewModelProviders.of(this, factory).get(TokenViewModel.class);
        return tokenViewModel;
    }

    @Override
    public void handleError(Throwable throwable) {
        super.handleError(throwable);
        ApiError apiError = new ApiError(((ANError) throwable).getErrorBody(), ((ANError) throwable).getErrorCode());
        DialogUtils.showAlertDialog(this, apiError.getMessage());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityTokenDetailBinding = getViewDataBinding();
        tokenViewModel.setNavigator(this);
        setUp();
        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pInfo.versionName;
            mActivityTokenDetailBinding.adminLogin.setText("Version: " + version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void setUp() {
        String header = getViewModel().getDataManager().getValue(AppPreferencesHelper.PREF_KEY_HEADER_TEXT).trim();
        String companyName = getViewModel().getDataManager().getCurrentUserName();
        String logoPath = getViewModel().getDataManager().getCurrentUserProfilePicUrl();

        mActivityTokenDetailBinding.hospital.setText(companyName.trim());
        mActivityTokenDetailBinding.subheading.setText(header.trim());
        mActivityTokenDetailBinding.swiperefreshlayout.setEnabled(false);
        mActivityTokenDetailBinding.swiperefreshlayout.setColorSchemeResources(R.color.colorPrimary);
        mActivityTokenDetailBinding.swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tokenViewModel.getTokenDetail("2", false);
            }
        });
        mActivityTokenDetailBinding.selectToken.setText(Html.fromHtml("<u>Select Token</u>"));
        com.displayfort.dftoken.utils.BindingUtils.setImageUrl(mActivityTokenDetailBinding.logo, new NrFtPrefrence(TokenActivity.this).getImageIP_ADDRESS() + logoPath);
    }

    private void setToken(String value, boolean isStatus) {
        if (isStatus) {
            startAnimation(Techniques.ZoomOutDown, value);
            mActivityTokenDetailBinding.nBtn.setEnabled(true);
            mActivityTokenDetailBinding.pBtn.setEnabled(true);
        } else {
            EndAnimation(Techniques.Shake, value);
            mActivityTokenDetailBinding.nBtn.setEnabled(false);
            mActivityTokenDetailBinding.pBtn.setEnabled(false);
            mActivityTokenDetailBinding.token.setText("");
            Utility.ShowToast("No record found", this);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!mActivityTokenDetailBinding.nBtn.isEnabled()) {
                        tokenViewModel.getTokenDetail("1", false);
                    }
                }
            }, 1000 * 60 * 1);
        }
    }

    private void startAnimation(Techniques zoomOutDown, final String value) {
        YoYo.with(zoomOutDown)
                .duration(TIME_ELAPSE)
                .pivot(YoYo.CENTER_PIVOT, YoYo.CENTER_PIVOT)
                .interpolate(new AccelerateDecelerateInterpolator())
                .withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mActivityTokenDetailBinding.token.setText(value);
                        EndAnimation(Techniques.ZoomInUp, value);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .playOn(mActivityTokenDetailBinding.token);
    }

    private void EndAnimation(Techniques zoomOutDown, String value) {
        YoYo.with(zoomOutDown)
                .duration(TIME_ELAPSE)
                .pivot(YoYo.CENTER_PIVOT, YoYo.CENTER_PIVOT)
                .interpolate(new AccelerateDecelerateInterpolator())
                .withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .playOn(mActivityTokenDetailBinding.token);
    }

    @Override
    public void onSelectToken() {
        DialogUtils.onGetNewTokenDialog(TokenActivity.this, new OnValueReturn() {
            @Override
            public void getskipTokenID(String tokenId) {
                tokenViewModel.onGetSelectedToken(tokenId);
            }
        });

    }

    @Override
    public void onSkip() {
        if (tokenId != 0) {
            tokenViewModel.onTokenSkip(tokenId + "");
        }
    }


    @Override
    public void onNext() {
        tokenViewModel.getTokenDetail("1", true);
    }

    @Override
    public void onCancel() {

    }

    @Override
    public void openLoginActivity() {
        Intent intent = LoginActivity.newIntent(this);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void getTokenDetail(TokenResponse response) {
        if (mActivityTokenDetailBinding.swiperefreshlayout.isRefreshing()) {
            mActivityTokenDetailBinding.swiperefreshlayout.setRefreshing(false);
        }
        if (response.isStatus()) {
            List<TokenResponse.TokenDao> tokenList = response.getData();
            if (tokenList != null && tokenList.size() > 0) {
                mActivityTokenDetailBinding.counterNameTv.setText(tokenList.get(0).getSubcounter_name());
                tokenId = tokenList.get(0).getToken_id();
                tokenDisplayName = tokenList.get(0).getToken_display_name();
                setToken(tokenDisplayName, response.isStatus());

            }

        } else {
            tokenId = 0;
            tokenDisplayName = "";
            setToken(tokenDisplayName, response.isStatus());
        }
    }

    int doubleBackToExitPressedOnce = 0;

    @Override
    public void onLogoutClick() {


        if (doubleBackToExitPressedOnce == 2) {
            Log.d("DOUBLETP", "NEW T VALUE:" + doubleBackToExitPressedOnce + "");
            tokenViewModel.getLogout();
            return;
        } else {
            Log.d("DOUBLETP", "NEW F VALUE:" + doubleBackToExitPressedOnce + "");
        }

        ++this.doubleBackToExitPressedOnce;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = 0;
            }
        }, 2000);
    }
}
