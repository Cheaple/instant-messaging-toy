package com.example.im.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.method.DigitsKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.im.R;
import com.example.im.activity.base.MainActivity;
import com.example.im.mvp.contract.base.ISignInContract;
import com.example.im.mvp.presenter.base.SignInPresenter;

public class SignInFragment extends Fragment implements ISignInContract.View, View.OnClickListener {
    private Activity context;
    private SignInPresenter mPresenter;

    private EditText usernameEditView;
    private EditText passwordEditView;
    private Button signInButton;

    public SignInFragment() {
        // Required empty public constructor
    }

    public static SignInFragment newInstance() {
        SignInFragment fragment = new SignInFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        mPresenter = new SignInPresenter(this, context);

        usernameEditView = (EditText)getView().findViewById(R.id.edit_username);
        passwordEditView = (EditText)getView().findViewById(R.id.edit_password);
        signInButton = (Button)getView().findViewById(R.id.button_sign_in);

        String digits = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        usernameEditView.setFilters(new InputFilter[]{new InputFilter.LengthFilter(15)});
        usernameEditView.setKeyListener(DigitsKeyListener.getInstance(digits));
        signInButton.setOnClickListener(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

    @Override
    public void onClick(View view) {
        // 点击事件：登录
        mPresenter.login();
    }

    @Override
    public String getUsername() {
        return usernameEditView.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordEditView.getText().toString();
    }

    @Override
    public void clearUsername() {
        usernameEditView.setText("");
    }

    @Override
    public void clearPassword() {
        passwordEditView.setText("");
    }

    @Override
    public void gotoMainActivity() {
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
        context.finish();
    }

    @Override
    public void showText(String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }
}
