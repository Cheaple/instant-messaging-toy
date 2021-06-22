package com.example.im.activity.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.im.R;
import com.example.im.adapter.contacts.InvitationAdapter;
import com.example.im.bean.contacts.Contact;
import com.example.im.listener.OnItemClickListener;
import com.example.im.mvp.contract.contacts.ISearchContract;
import com.example.im.mvp.presenter.contacts.SearchPresenter;

import java.util.LinkedList;
import java.util.List;


public class SearchActivity extends AppCompatActivity implements ISearchContract.View, OnItemClickListener, View.OnClickListener {
    private Context context;
    private SearchPresenter mPresenter;

    private InvitationAdapter invitationAdapter;
    private EditText editText;
    private Button searchButton;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_search);
        Intent intent = getIntent();

        context = getApplicationContext();
        mPresenter = new SearchPresenter(this, context);

        editText = (EditText)findViewById(R.id.edit_new_friend_id);
        searchButton = (Button)findViewById(R.id.button_search);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view_invitations);

        String digits = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(15)});
        editText.setKeyListener(DigitsKeyListener.getInstance(digits));
        searchButton.setOnClickListener(this);

        mPresenter.showInvitationList();
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (view.getId()){
            case R.id.button_invitation_accept:  // 点击事件：接受邀请
                mPresenter.accept(position);
                Toast.makeText(context,"Accepted",Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_invitation_refuse:  // 点击事件：拒绝邀请
                Toast.makeText(context,"Refused", Toast.LENGTH_SHORT).show();
                mPresenter.refuse(position);
                break;
            default:
                Toast.makeText(context,"Default"+(position+1),Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onClick(View view) {
        // 点击事件：搜索联系人
        mPresenter.searchUser();
    }

    @Override
    public void setInvitationList(List list) {
        invitationAdapter = new InvitationAdapter((LinkedList<Contact>) list, context);
        invitationAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(invitationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public void setInvitationList() {
        invitationAdapter.notifyDataSetChanged();
    }

    @Override
    public String getTargetUsername() {
        return editText.getText().toString();
    }

    @Override
    public void gotoInfoActivity(Contact contact, boolean isContact) {
        Intent intent = new Intent(context, InfoActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  // 结束当前activity
        if (!isContact)  // 如果查找的用户不是当前用户的好友
            intent.putExtra("Type", Contact.CONTACT_TYPE_SEARCH);
        else  // 查找的用户是当前用户的好友
            intent.putExtra("Type", Contact.CONTACT_TYPE_LIST);
        intent.putExtra("Contact", contact);  // 传递联系人信息
        startActivity(intent);
        finish();
    }


    @Override
    public void showText(String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }
}
