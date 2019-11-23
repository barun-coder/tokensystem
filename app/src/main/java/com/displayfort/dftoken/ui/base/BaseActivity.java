
package com.displayfort.dftoken.ui.base;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.androidnetworking.error.ANError;
import com.displayfort.dftoken.R;
import com.displayfort.dftoken.ui.login.LoginActivity;
import com.displayfort.dftoken.utils.CommonUtils;
import com.displayfort.dftoken.utils.NetworkUtils;

import dagger.android.AndroidInjection;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends AppCompatActivity
        implements BaseFragment.Callback, BaseNavigator {
    public static String mPlayerID;
    // TODO
    // this can probably depend on isLoading variable of BaseViewModel,
    // since its going to be common for all the activities
    private ProgressDialog mProgressDialog;
    private T mViewDataBinding;
    private V mViewModel;

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    public abstract int getBindingVariable();

    /**
     * @return layout resource id
     */
    public abstract
    @LayoutRes
    int getLayoutId();

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    public abstract V getViewModel();

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState);
        performDataBinding();
    }


    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    public void openActivityOnTokenExpire() {
        startActivity(LoginActivity.newIntent(this));
        finish();
    }

    public void performDependencyInjection() {
        AndroidInjection.inject(this);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    private void performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }

    public void showSnackBar(View view, String text) {
        hideKeyboard();
        Snackbar snackbar = Snackbar
                .make(view, text, Snackbar.LENGTH_LONG);
//                .setAction("RETRY", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                    }
//                }                );
        snackbar.setActionTextColor(getResources().getColor(R.color.colorPrimary));
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);
    }

    protected View.OnClickListener onBackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onBackPressed();
        }
    };

    protected void setCommonHeader(Toolbar toolbar, int icon_back) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Drawable icon = getResources().getDrawable(icon_back);
        toolbar.setNavigationIcon(icon);
        toolbar.setNavigationOnClickListener(onBackClickListener);

    }

    protected void setCommonHeader(Toolbar toolbar, int icon_back, View.OnClickListener onBackClickListener) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Drawable icon = getResources().getDrawable(icon_back);
        toolbar.setNavigationIcon(icon);
        toolbar.setNavigationOnClickListener(onBackClickListener);

    }


    protected void setHeaderWithLogo(Toolbar toolbar, int icon_back) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Drawable icon = getResources().getDrawable(icon_back);
        toolbar.setNavigationIcon(icon);
        toolbar.setNavigationOnClickListener(onBackClickListener);
//        toolbar.setLogo(R.mipmap.logo_2x);

    }

    protected void setCommonHeader(Toolbar toolbar, int icon_back, String title) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Drawable icon = getResources().getDrawable(icon_back);
        toolbar.setNavigationIcon(icon);
        toolbar.setNavigationOnClickListener(onBackClickListener);

    }

    @Override
    public void handleError(Throwable throwable) {
        if ((((ANError) throwable).getErrorCode()) == 401) {
            onLogout();
            Intent intent = LoginActivity.newIntent(this);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }

    public void onLogout() {
        getViewModel().getDataManager().setUserAsLoggedOut();

    }
}

