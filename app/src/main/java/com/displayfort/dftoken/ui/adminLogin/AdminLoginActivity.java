
package com.displayfort.dftoken.ui.adminLogin;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.displayfort.dftoken.BR;
import com.displayfort.dftoken.R;
import com.displayfort.dftoken.ViewModelProviderFactory;
import com.displayfort.dftoken.data.model.api.ApiError;
import com.displayfort.dftoken.data.model.api.response.LoginResponse;
import com.displayfort.dftoken.databinding.ActivityLoginBinding;
import com.displayfort.dftoken.ui.base.BaseActivity;
import com.displayfort.dftoken.ui.base.BaseAnimation;
import com.displayfort.dftoken.ui.feedback.TokenActivity;
import com.displayfort.dftoken.ui.login.LoginNavigator;
import com.displayfort.dftoken.ui.login.LoginViewModel;
import com.displayfort.dftoken.utils.DialogUtils;

import javax.inject.Inject;


public class AdminLoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> implements LoginNavigator {

    @Inject
    ViewModelProviderFactory factory;
    private LoginViewModel mLoginViewModel;
    private ActivityLoginBinding mActivityLoginBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, AdminLoginActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginViewModel getViewModel() {
        mLoginViewModel = ViewModelProviders.of(this, factory).get(LoginViewModel.class);
        return mLoginViewModel;
    }

    @Override
    public void handleError(Throwable throwable) {
        ApiError apiError = new ApiError(((ANError) throwable).getErrorBody(), ((ANError) throwable).getErrorCode());
        DialogUtils.showAlertDialog(this, apiError.getMessage());
    }

    @Override
    public void login() {
        String email = mActivityLoginBinding.username.getText().toString();
        String password = mActivityLoginBinding.password.getText().toString();
        int validation = mLoginViewModel.isEmailAndPasswordValid(email, password,"");
        if (validation == 0) {
            hideKeyboard();
            mLoginViewModel.login(email, password);
        } else {
            setError(validation);
        }
    }

    @Override
    public void adminlogin() {

    }

    private void setError(int validation) {
        switch (validation) {
            case 1:
                mActivityLoginBinding.username.setError("Please Enter Username");
                break;
            case 2:
                mActivityLoginBinding.password.setError("Please Enter Password");
                break;

        }
    }

    @Override
    public void OpenForgotPasswordActivity() {


    }

    @Override
    public void openMainActivity(LoginResponse.UserDao data) {
        Intent intent = TokenActivity.newIntent(AdminLoginActivity.this);
        startActivity(intent);
        BaseAnimation.setAnimationTransition(this, BaseAnimation.EFFECT_TYPE.TAB_ZOOM);
        finish();
    }


    @Override
    public void OpenVirificationActivity(String message) {
        DialogUtils.showAlertDialog(this, message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityLoginBinding = getViewDataBinding();
        mLoginViewModel.setNavigator(this);
        getUniqueNumberPermision();
        mActivityLoginBinding.password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_ACTION_DONE) {
                    login();
                    return true;
                }
                return false;
            }
        });
    }

    private void getUniqueNumberPermision() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            if (!checkIfAlreadyhavePermission()) {
                RequestPermission(Manifest.permission.READ_PHONE_STATE, 102);
            } else {
                getUniqueNumber();
            }
        }

    }

    private boolean checkIfAlreadyhavePermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void RequestPermission(String permision, int code) {
        ActivityCompat.requestPermissions(this,
                new String[]{permision},
                code);
        mayRequestContacts(permision, code);
    }

    private boolean mayRequestContacts(final String permision, final int mRequestCode) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (this.checkSelfPermission(permision) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(permision)) {
            requestPermissions(new String[]{permision}, mRequestCode);
        } else {
            requestPermissions(new String[]{permision}, mRequestCode);
        }

        return false;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 102: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getUniqueNumber();
                } else {
                    Toast.makeText(this, "Permission denied to read Phone state", Toast.LENGTH_SHORT).show();
                }
                return;
            }


        }
    }

    private void getUniqueNumber() {
        try {
            String android_id = Settings.Secure.getString(getContentResolver(),
                    Settings.Secure.ANDROID_ID);
            Log.d("UNIQUE", android_id);
//            mIdentifierId = android_id;
//            mUniqueIdTV.setText(android_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
