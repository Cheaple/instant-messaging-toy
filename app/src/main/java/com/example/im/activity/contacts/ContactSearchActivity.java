package com.example.im.activity.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.im.R;
import com.example.im.adapter.contacts.ContactAdapter;
import com.example.im.adapter.contacts.InvitationAdapter;
import com.example.im.bean.contacts.Contact;
import com.example.im.listener.OnItemClickListener;
import com.example.im.mvp.contract.contacts.IContactSearchContract;
import com.example.im.mvp.presenter.contacts.ContactSearchPresenter;
import com.example.im.mvp.presenter.contacts.GroupCreatingPresenter;

import java.util.LinkedList;
import java.util.List;

import butterknife.OnClick;


public class ContactSearchActivity extends AppCompatActivity implements IContactSearchContract.View, OnItemClickListener, View.OnClickListener {
    private Context context;
    private ContactSearchPresenter mPresenter;

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
        mPresenter = new ContactSearchPresenter(this, context);

        editText = (EditText)findViewById(R.id.edit_new_friend_id);
        searchButton = (Button)findViewById(R.id.button_search);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view_invitations);

        searchButton.setOnClickListener(this);

        mPresenter.showInvitationList();
    }

    @Override
    public void onItemClick(View view, int position) {}

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
    public String getTargetID() {
        return editText.getText().toString();
    }
}
