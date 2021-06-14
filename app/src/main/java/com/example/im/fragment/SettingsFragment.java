package com.example.im.fragment;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.method.DigitsKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.im.R;
import com.example.im.activity.base.LoginActivity;
import com.example.im.mvp.contract.settings.ISettingsContract;
import com.example.im.mvp.presenter.settings.SettingsPresenter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment implements ISettingsContract.View, View.OnClickListener {
    private Activity context;
    private ISettingsContract.Presenter mPresenter;

    private ImageView avatarImageView;
    private TextView nicknameTextView;
    private LinearLayout usernameLayout;
    private TextView usernameTextView;
    private LinearLayout regionLayout;
    private TextView regionTextView;
    private LinearLayout passwordLayout;
    private LinearLayout logoutLayout;


    public SettingsFragment() {
        // Required empty public constructor
    }

    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.context = getActivity();
        this.mPresenter = new SettingsPresenter(this, context);

        this.avatarImageView = getView().findViewById(R.id.img_my_avatar);
        this.nicknameTextView = getView().findViewById(R.id.text_my_nickname);
        this.usernameLayout = getView().findViewById(R.id.layout_my_username);
        this.usernameTextView = getView().findViewById(R.id.text_my_id);
        this.regionLayout = getView().findViewById(R.id.layout_my_region);
        this.regionTextView = getView().findViewById(R.id.text_my_region);
        this.passwordLayout = getView().findViewById(R.id.layout_my_password);
        this.logoutLayout = getView().findViewById(R.id.layout_logout);

        avatarImageView.setOnClickListener(this);
        nicknameTextView.setOnClickListener(this);
        usernameLayout.setOnClickListener(this);
        regionLayout.setOnClickListener(this);
        passwordLayout.setOnClickListener(this);
        logoutLayout.setOnClickListener(this);

        mPresenter.showInfo();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_my_avatar:
                changeAvatar();
                break;
            case R.id.text_my_nickname:
                changeNickname();
                break;
            case R.id.layout_my_username:
                changeUsername();
                break;
            case R.id.layout_my_region:
                changeRegion();
                break;
            case R.id.layout_my_password:
                changePassword();
                break;
            case R.id.layout_logout:
                logout();
                break;
        }
    }

    // 点击事件：修改头像
    private void changeAvatar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Change your avatar?");
        builder.setPositiveButton("Change", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Change", Toast.LENGTH_SHORT).show();
                //TODO: 更新头像
                mPresenter.changeAvatar();
            }
        });
        builder.show();
    }

    // 点击事件：修改昵称
    private void changeNickname() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_input, null);
        builder.setView(v);
        builder.setMessage("Change your name?");
        final EditText editText = (EditText)v.findViewById(R.id.edit_dialog);
        editText.setHint("Enter your new name");
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
        builder.setPositiveButton("Change", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String new_name = editText.getText().toString().trim();
                if (!"".equals(new_name)) {
                    nicknameTextView.setText(new_name);
                    mPresenter.changeNickname(new_name);
                }
            }
        });
        builder.show();
    }

    // 点击事件：修改 Username
    private void changeUsername() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_input, null);
        builder.setView(v);
        builder.setMessage("Change your ID?");
        final EditText editText = (EditText)v.findViewById(R.id.edit_dialog);
        editText.setHint("Enter your new ID");
        String digits = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(15)});
        editText.setKeyListener(DigitsKeyListener.getInstance(digits));
        builder.setPositiveButton("Change", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String new_id = editText.getText().toString().trim();
                if (!"".equals(new_id)) {
                    usernameTextView.setText("ID: " + new_id);
                    mPresenter.changeUsername(new_id);
                }
            }
        });
        builder.show();
    }

    // 点击事件：修改地区
    private void changeRegion() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_input, null);
        builder.setView(v);
        builder.setMessage("Change your region?");
        final EditText editText = (EditText)v.findViewById(R.id.edit_dialog);
        editText.setHint("Enter your new region");
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(15)});
        builder.setPositiveButton("Change", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String new_region = editText.getText().toString();
                if (!"".equals(new_region)) {
                    regionTextView.setText("Region: " + new_region);
                    mPresenter.changeRegion(new_region);
                }
            }
        });
        builder.show();
    }

    // 点击事件：修改密码
    private void changePassword() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_input3, null);
        builder.setView(v);
        builder.setMessage("Change your password?");
        final EditText oldPasswordEditText = (EditText)v.findViewById(R.id.edit_dialog_3_1);
        final EditText newPasswordEditText = (EditText)v.findViewById(R.id.edit_dialog_3_2);
        final EditText confirmPasswordEditText = (EditText)v.findViewById(R.id.edit_dialog_3_3);

        builder.setPositiveButton("Change", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String old_pw = oldPasswordEditText.getText().toString();
                String new_pw = newPasswordEditText.getText().toString();
                String confirm_pw = confirmPasswordEditText.getText().toString();
                mPresenter.changePassword(old_pw, new_pw, confirm_pw);
            }
        });
        builder.show();
    }

    // 点击事件：退出登陆
    private void logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("Log Out", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPresenter.logout();
            }
        });
        builder.show();
    }

    @Override
    public void setUsername(String username) {
        usernameTextView.setText("ID: " + username);
    }

    @Override
    public void setNickname(String nickname) { nicknameTextView.setText(nickname); }


    @Override
    public void gotoLoginActivity() {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);  // 清除栈中的所有activity
        startActivity(intent);
    }

    @Override
    public void showText(String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }
}