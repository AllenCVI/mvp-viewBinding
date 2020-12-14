package com.newblink.blink.android.login.activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.newblink.blink.android.MainActivity;
import com.newblink.blink.android.R;
import com.newblink.blink.android.common.LocalRepository;
import com.newblink.blink.android.common.utils.ToastUtil;
import com.newblink.blink.android.databinding.ActivityLoginBinding;
import com.newblink.blink.android.login.realizationmvp.LoginContract;
import com.newblink.blink.android.base.mvpbase.BaseMvpActivity;
import com.newblink.blink.android.login.realizationmvp.LoginPresenter;
import com.newblink.blink.android.model.remote.request.LoginBean;

public class LoginActivity extends BaseMvpActivity<ActivityLoginBinding, LoginContract.Presenter> implements LoginContract.View, View.OnClickListener {

    private EditText et_accountNumber;
    private EditText et_password;


    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        vBinding.ivBackUp.setOnClickListener(this);
        vBinding.btLogin.setOnClickListener(this);
        initAccountET();
        initPasswordET();

    }

    private void initPasswordET() {
        et_password = vBinding.etPassword;
        String password = LocalRepository.getInstance().getPassword();
        if(!TextUtils.isEmpty(password)){
            et_password.setText(password);
        }
    }

    private void initAccountET() {
        et_accountNumber = vBinding.etAccountNumber;
        String account = LocalRepository.getInstance().getAccount();
        if(!TextUtils.isEmpty(account)){
            et_accountNumber.setText(account);
        }
    }


    @Override
    protected void initData() {
        super.initData();
        new LoginPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_backUp:
                backUpClick();
                break;

            case R.id.bt_login:
                login();
                break;
            default:
                break;
        }

    }

    private void login() {
      String account = et_accountNumber.getText().toString().trim();
      if(TextUtils.isEmpty(account)){
          ToastUtil.showToast(getString(R.string.please_enter_account));
          return;
      }
      String password = et_password.getText().toString().trim();
      if(TextUtils.isEmpty(password)){
          ToastUtil.showToast(getString(R.string.please_enter_password));
          return;
      }
      showLoading();
      mPresenter.login(new LoginBean(account,password));

    }

    private void backUpClick() {
        finish();
    }

    @Override
    public void loginSuccess() {
        hideLoadingDialog();
        gotoMain();
    }

    private void gotoMain() {
        MainActivity.start(this);
        finish();
    }
    @Override
    public void showLoading() {
       showLoadingDialog();
    }

    @Override
    public void hideLoading() {
       hideLoadingDialog();
    }
}
