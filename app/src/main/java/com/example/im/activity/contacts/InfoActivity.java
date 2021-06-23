package com.example.im.activity.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.im.R;
import com.example.im.activity.base.LoginActivity;
import com.example.im.activity.base.MainActivity;
import com.example.im.activity.chats.ChattingActivity;
import com.example.im.bean.AccountInfo;
import com.example.im.bean.chats.Chat;
import com.example.im.bean.contacts.Contact;
import com.example.im.mvp.contract.contacts.IInfoContract;
import com.example.im.mvp.presenter.contacts.InfoPresenter;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity implements IInfoContract.View, View.OnClickListener {
    private Context context;
    private InfoPresenter mPresenter;

    private ImageView avatarImageView;
    private TextView nameTextView;
    private TextView idTextView;
    private TextView regionTextView;
    private LinearLayout chattingLayout;
    private LinearLayout clearLayout;
    private LinearLayout addLayout;
    private LinearLayout deleteLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);
        Intent intent = getIntent();
        int type = intent.getIntExtra("Type", Contact.CONTACT_TYPE_LIST);
        Contact contact = (Contact) intent.getParcelableExtra("Contact");  // 获取所查看联系人的信息

        this.context = getApplicationContext();
        this.mPresenter = new InfoPresenter(this, contact);

        this.avatarImageView = this.findViewById(R.id.img_contact_info_avatar);
        this.nameTextView = this.findViewById(R.id.text_contact_info_nickname);
        this.idTextView = this.findViewById(R.id.text_contact_info_id);
        this.regionTextView = this.findViewById(R.id.text_contact_info_region);
        this.chattingLayout = this.findViewById(R.id.layout_go_chatting);
        this.clearLayout = this.findViewById(R.id.layout_clear_history);
        this.addLayout = this.findViewById(R.id.layout_contact_add);
        this.deleteLayout = this.findViewById(R.id.layout_contact_delete);

        // 隐藏Add栏或Delete栏
        if (type == Contact.CONTACT_TYPE_LIST) {
            this.deleteLayout.setVisibility(View.VISIBLE);
            this.chattingLayout.setVisibility(View.VISIBLE);
            this.clearLayout.setVisibility(View.VISIBLE);
            this.addLayout.setVisibility(View.GONE);
        }
        else if (type == Contact.CONTACT_TYPE_SEARCH) {
            this.addLayout.setVisibility(View.VISIBLE);
            this.deleteLayout.setVisibility(View.GONE);
            this.chattingLayout.setVisibility(View.GONE);
            this.clearLayout.setVisibility(View.GONE);
        }

        chattingLayout.setOnClickListener(this);
        clearLayout.setOnClickListener(this);
        deleteLayout.setOnClickListener(this);
        addLayout.setOnClickListener(this);

        mPresenter.showInfo();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_go_chatting:  // 点击事件：发起会话
                mPresenter.createChatting();
                break;
            case R.id.layout_clear_history:  // 点击事件：清空聊天记录
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Confirm deletion of the chat history");
                builder.setPositiveButton("Clear", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.clearChattingHistory();
                    }
                });
                builder.show();
                break;
            case R.id.layout_contact_add:  // 点击事件：发送添加好友请求
                mPresenter.add();
                break;
            case R.id.layout_contact_delete:  // 点击事件：删除好友
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setMessage("Delete this contact and the chat history with it");
                builder2.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.delete();
                    }
                });
                builder2.show();
                break;
        }
    }

    @Override
    public void setAvatar(String avatar) {
        String avatarUrl = context.getString(R.string.server)+"/picture/" + avatar;
        Glide.with(context).load(avatarUrl).into(avatarImageView);  // 设置联系人头像
    }

    @Override
    public void setNickname(String nickname) {
        nameTextView.setText(nickname);
    }

    @Override
    public void setUsername(String username) {
        idTextView.setText("ID: " + username);
    }

    @Override
    public void setRegion(String region) {
        regionTextView.setText("Region: " + region);
    }

    @Override
    public void gotoMainActivity() {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);  // 清除栈中的所有activity
        intent.putExtra("Fragment", 1);
        startActivity(intent);
    }

    @Override
    public void gotoInfoActivity(Contact contact) {
        Intent intent = new Intent(context, InfoActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  // 结束当前activity
        intent.putExtra("Type", Contact.CONTACT_TYPE_LIST);
        intent.putExtra("Contact", contact);  // 传递联系人信息
        startActivity(intent);
    }

    @Override
    public void gotoChattingActivity(Contact contact, String chatId) {
        ArrayList<String> members = new ArrayList<>();
        members.add(contact.getId());
        members.add(AccountInfo.getInstance().getId());

        Intent intent = new Intent(context, ChattingActivity.class);
        intent.putExtra("Chat Type", Chat.CHAT_TYPE_SINGLE);  // 传递会话类型
        intent.putExtra("Chat ID", chatId);  // 传递会话ID
        intent.putStringArrayListExtra("Group Members", members);  // 传递群聊成员
        intent.putExtra("Contact", contact.getUsername());  // 传递联系人信息
        startActivity(intent);
    }

    @Override
    public void showText(String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }
}
