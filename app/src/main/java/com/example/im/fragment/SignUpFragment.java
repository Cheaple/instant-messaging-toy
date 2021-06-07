package com.example.im.fragment;

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

public class SignUpFragment extends Fragment  {
    private EditText idEditView;
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
        idEditView = (EditText)getView().findViewById(R.id.edit_new_id);
        passwordEditView = (EditText)getView().findViewById(R.id.edit_new_password);
        pwConfirmEditView = (EditText)getView().findViewById(R.id.edit_confirm_password);
        signUpButton = (Button)getView().findViewById(R.id.button_sign_up);

        // 限制输入格式
        String digits = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        idEditView.setFilters(new InputFilter[]{new InputFilter.LengthFilter(15)});
        idEditView.setKeyListener(DigitsKeyListener.getInstance(digits));

        // 设置点击事件
        signUp();
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

    // 点击事件：注册
    private void signUp() {
        this.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = idEditView.getText().toString();
                String password = passwordEditView.getText().toString();
                String pw = pwConfirmEditView.getText().toString();
                if (!pw.equals(password))
                    Toast.makeText(getActivity(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                else {
                    // TODO: 注册
                }
            }
        });
    }
}

