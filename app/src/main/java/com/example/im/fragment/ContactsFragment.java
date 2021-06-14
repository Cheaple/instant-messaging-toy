package com.example.im.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.im.R;
import com.example.im.activity.contacts.InfoActivity;
import com.example.im.activity.contacts.SearchActivity;
import com.example.im.activity.contacts.GroupCreatingActivity;
import com.example.im.adapter.contacts.ContactAdapter;
import com.example.im.bean.contacts.Contact;
import com.example.im.listener.OnItemClickListener;
import com.example.im.mvp.contract.contacts.IContactsContract;
import com.example.im.mvp.presenter.contacts.ContactsPresenter;

import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactsFragment extends Fragment implements IContactsContract.View, OnItemClickListener {
    Activity context;
    private ContactsPresenter mPresenter;

    private ContactAdapter contactAdapter;
    private RecyclerView recyclerView;

    public ContactsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ContactsFragment.
     */
    public static ContactsFragment newInstance() {
        ContactsFragment fragment = new ContactsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        mPresenter = new ContactsPresenter(this, context);

        recyclerView = view.findViewById(R.id.recycle_view_contacts);

        mPresenter.showContactList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.add(Menu.NONE, 0, Menu.NONE,"New Contact");
        menu.add(Menu.NONE, 1, Menu.NONE, "New Group Chat");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case 0:
            // 点击事件：跳转至联系人搜索界面
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            startActivityForResult(intent, 1);
            break;
        case 1:
            // 点击事件：跳转至群聊创建界面
            Intent intent2 = new Intent(getActivity(), GroupCreatingActivity.class);
            startActivityForResult(intent2, 1);
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(View view, int position) {
        // 点击事件：跳转至联系人信息界面
        Intent intent = new Intent(context, InfoActivity.class);
        intent.putExtra("Type", Contact.CONTACT_TYPE_LIST);
        intent.putExtra("Contact", mPresenter.getContact(position));  // 传递联系人信息
        startActivityForResult(intent, 1);
    }

    @Override
    public void setContactList(List list) {
        contactAdapter = new ContactAdapter((LinkedList<Contact>) list, context, false);
        contactAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(contactAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public void setContactList() {
        contactAdapter.notifyDataSetChanged();
    }

    @Override
    public void showText(String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }
}