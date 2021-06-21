package com.example.im.adapter.contacts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.im.R;
import com.example.im.bean.contacts.Contact;
import com.example.im.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.LinkedList;

import static androidx.core.app.ActivityCompat.startActivityForResult;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private Context context;
    private OnItemClickListener mClickListener;
    private LinkedList<Contact> contactList;
    private boolean ifDisplayCheckBox = true;
    private ArrayList<String> selectedContactList = new ArrayList<>();


    public static class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ContactAdapter mAdapter;
        private OnItemClickListener mListener;
        private View contactItemView;
        private ImageView avatarImageView;
        private TextView nicknameTextView;
        private CheckBox checkBox;  // 复选框，用于在创建群聊时选择联系人


        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        public ContactViewHolder(View itemView, ContactAdapter adapter, OnItemClickListener listener) {
            super(itemView);
            this.mAdapter = adapter;
            this.mListener = listener;
            this.contactItemView = itemView.findViewById(R.id.contact);
            this.avatarImageView = itemView.findViewById(R.id.img_contact_avatar);
            this.nicknameTextView = itemView.findViewById(R.id.text_contact_nickname);
            this.checkBox = itemView.findViewById(R.id.checkbox_contact);
            itemView.setOnClickListener(this);  // 为ItemView添加点击事件
        }

        public void onClick(View v) {
            // getpostion()为Viewholder自带的一个方法，用来获取RecyclerView当前的位置，将此作为参数，传出去
            mListener.onItemClick(v, getPosition());
        }
    }

    public ContactAdapter(LinkedList<Contact> contactList, Context context, boolean ifDisplayCheckBox) {
        this.contactList = contactList;
        this.context = context;
        this.ifDisplayCheckBox = ifDisplayCheckBox;
    }

    @NonNull
    @Override
    public ContactAdapter.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = LayoutInflater.from(context).inflate(R.layout.item_recycle_contact, parent, false);
        return new ContactAdapter.ContactViewHolder(mItemView, this, mClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        // Retrieve the data for that position
        Contact contact = contactList.get(position);

        String avatarUrl = context.getString(R.string.server)+"/picture/" + contact.getAvatar();
        Glide.with(context).load(avatarUrl).into(holder.avatarImageView);  // 设置联系人头像
        holder.nicknameTextView.setText(contact.getNickname());  // 设置联系人昵称
        if (!ifDisplayCheckBox) holder.checkBox.setVisibility(View.GONE);  // 隐藏复选框
        else {
            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        selectedContactList.add(contact.getID());
                    } else {
                        selectedContactList.remove(contact.getID());
                    }
                }
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public ArrayList<String> getSelectedContacts() {
        return selectedContactList;
    }
}
