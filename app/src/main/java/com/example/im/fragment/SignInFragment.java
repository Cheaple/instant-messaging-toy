package com.example.im.fragment;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.method.DigitsKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.im.R;

public class SignInFragment extends Fragment  {
    private EditText idEditView;
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
        idEditView = (EditText)getView().findViewById(R.id.edit_id);
        passwordEditView = (EditText)getView().findViewById(R.id.edit_password);
        signInButton = (Button)getView().findViewById(R.id.button_sign_in);

        // 限制输入格式
        String digits = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        idEditView.setFilters(new InputFilter[]{new InputFilter.LengthFilter(15)});
        idEditView.setKeyListener(DigitsKeyListener.getInstance(digits));

        // 设置点击事件
        signIn();
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

    // 点击事件：登录
    private void signIn() {
        this.signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 登录
                String id = idEditView.getText().toString();
                String password = passwordEditView.getText().toString();
            }
        });
    }
}
