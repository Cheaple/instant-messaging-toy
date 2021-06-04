package com.example.im.adapter.contacts;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.im.R;
import com.example.im.bean.contacts.Contact;
import com.example.im.listener.OnItemClickListener;

import java.util.LinkedList;

import static androidx.core.app.ActivityCompat.startActivityForResult;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private Context context;
    private OnItemClickListener mClickListener;
    private LinkedList<Contact> contactList;


    public static class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ContactAdapter mAdapter;
        private OnItemClickListener mListener;
        private View contactItemView;
        private ImageView avatarImageView;
        private TextView nameTextView;


        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        public ContactViewHolder(View itemView, ContactAdapter adapter, OnItemClickListener listener) {
            super(itemView);
            this.mAdapter = adapter;
            this.mListener = listener;
            this.contactItemView = itemView.findViewById(R.id.contact);
            this.avatarImageView = itemView.findViewById(R.id.avatar_icon);
            this.nameTextView = itemView.findViewById(R.id.nickname_text);
            itemView.setOnClickListener(this);  // 为ItemView添加点击事件
        }

        public void onClick(View v) {
            // getpostion()为Viewholder自带的一个方法，用来获取RecyclerView当前的位置，将此作为参数，传出去
            mListener.onItemClick(v, getPosition());
        }
    }

    public ContactAdapter(LinkedList<Contact> contactList, Context context) {
        this.contactList = contactList;
        this.context = context;
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
        // Add the data to the view
        holder.avatarImageView.setImageResource(contact.getAvatarIcon());  // 设置联系人头像
        holder.nameTextView.setText(contact.getNickname());  // 设置联系人昵称
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }
}
