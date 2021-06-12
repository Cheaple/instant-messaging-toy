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
import com.example.im.mvp.contract.base.ISignUpContract;
import com.example.im.mvp.presenter.base.SignUpPresenter;

public class SignUpFragment extends Fragment implements ISignUpContract.View, View.OnClickListener {
    private Activity context;
    private SignUpPresenter mPresenter;

    private EditText usernameEditView;
    private EditText passwordEditView;
    private EditText pwConfirmEditView;
    private Button signUpButton;

    public SignUpFragment() {
        // Required empty public constructor
    }

    public static SignUpFragment newInstance() {
        SignUpFragment fragment = new SignUpFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        mPresenter = new SignUpPresenter(this, context);

        usernameEditView = (EditText)getView().findViewById(R.id.edit_new_username);
        passwordEditView = (EditText)getView().findViewById(R.id.edit_new_password);
        pwConfirmEditView = (EditText)getView().findViewById(R.id.edit_confirm_password);
        signUpButton = (Button)getView().findViewById(R.id.button_sign_up);

        String digits = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        usernameEditView.setFilters(new InputFilter[]{new InputFilter.LengthFilter(15)});
        usernameEditView.setKeyListener(DigitsKeyListener.getInstance(digits));
        signUpButton.setOnClickListener(this);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }


    @Override
    public void onClick(View view) {
        // 点击事件：注册
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
    public String getConfirmPassword() {
        return pwConfirmEditView.getText().toString();
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
    public void clearConfirmPassword() {
        pwConfirmEditView.setText("");
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

