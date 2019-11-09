
package com.displayfort.dftoken.ui.feedback;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Html;

import com.androidnetworking.error.ANError;
import com.displayfort.dftoken.BR;
import com.displayfort.dftoken.OnValueReturn;
import com.displayfort.dftoken.R;
import com.displayfort.dftoken.ViewModelProviderFactory;
import com.displayfort.dftoken.data.local.prefs.AppPreferencesHelper;
import com.displayfort.dftoken.data.model.api.ApiError;
import com.displayfort.dftoken.data.model.api.response.TokenResponse;
import com.displayfort.dftoken.databinding.ActivityTokenDetailBinding;
import com.displayfort.dftoken.ui.base.BaseActivity;
import com.displayfort.dftoken.ui.login.LoginActivity;
import com.displayfort.dftoken.utils.DialogUtils;
import com.displayfort.dftoken.utils.Utility;

import java.util.List;

import javax.inject.Inject;


public class TokenActivity extends BaseActivity<ActivityTokenDetailBinding, TokenViewModel> implements TokenNavigator {
    @Inject
    ViewModelProviderFactory factory;
    private TokenViewModel tokenViewModel;
    private ActivityTokenDetailBinding mActivityTokenDetailBinding;
    private int tokenId = 0;
    private String tokenDisplayName="";

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


    }

    private void setUp() {
        String header = getViewModel().getDataManager().getValue(AppPreferencesHelper.PREF_KEY_HEADER_TEXT).trim();
        String companyName = getViewModel().getDataManager().getCurrentUserName();
        mActivityTokenDetailBinding.hospital.setText(companyName);
        mActivityTokenDetailBinding.subheading.setText(header);
        mActivityTokenDetailBinding.swiperefreshlayout.setColorSchemeResources(R.color.colorPrimary);
        mActivityTokenDetailBinding.swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tokenViewModel.getTokenDetail("2", false);
            }
        });
        mActivityTokenDetailBinding.selectToken.setText(Html.fromHtml("<u>Select Token</u>"));

    }

    private void setToken(String value, boolean isStatus) {
        if (isStatus) {
            mActivityTokenDetailBinding.token.setText(value);
        } else {
            mActivityTokenDetailBinding.token.setText("");
            Utility.ShowToast("No record found", this);
        }
    }

    @Override
    public void onSelectToken() {
        DialogUtils.onGetNewTokenDialog(TokenActivity.this, new OnValueReturn() {
            @Override
            public void getskipTokenID(String tokenId) {
                tokenViewModel.onGetSkipToken(tokenId);
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
            tokenDisplayName="";
            setToken(tokenDisplayName, response.isStatus());
        }
    }
}
