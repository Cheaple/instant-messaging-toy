package com.example.im.adapter.contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.im.R;
import com.example.im.bean.contacts.Contact;
import com.example.im.listener.OnItemClickListener;

import java.util.LinkedList;

public class InvitationAdapter extends RecyclerView.Adapter<InvitationAdapter.InvitationViewHolder> {
    private Context context;
    private OnItemClickListener mClickListener;
    private LinkedList<Contact> contactList;

    public static class InvitationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private InvitationAdapter mAdapter;
        private OnItemClickListener mListener;
        private View contactItemView;
        private ImageView avatarImageView;
        private TextView nicknameTextView;
        private Button acceptButton;
        private Button refuseButton;

        public InvitationViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        public InvitationViewHolder(View itemView, InvitationAdapter adapter, OnItemClickListener listener) {
            super(itemView);
            this.mAdapter = adapter;
            this.mListener = listener;
            this.contactItemView = itemView.findViewById(R.id.contact);
            this.avatarImageView = itemView.findViewById(R.id.img_invitation_avatar);
            this.nicknameTextView = itemView.findViewById(R.id.text_invitation_nickname);
            this.acceptButton = itemView.findViewById(R.id.button_invitation_accept);
            this.refuseButton = itemView.findViewById(R.id.button_invitation_refuse);

            itemView.setOnClickListener(this);  // 为ItemView添加点击事件
            acceptButton.setOnClickListener(this);
            refuseButton.setOnClickListener(this);
        }

        public void onClick(View v) {
            mListener.onItemClick(v, (int) v.getTag());
        }
    }

    public InvitationAdapter(LinkedList<Contact> contactList, Context context) {
        this.contactList = contactList;
        this.context = context;
    }

    @NonNull
    @Override
    public InvitationAdapter.InvitationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = LayoutInflater.from(context).inflate(R.layout.item_recycle_invitation, parent, false);
        return new InvitationAdapter.InvitationViewHolder(mItemView, this, mClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull InvitationAdapter.InvitationViewHolder holder, int position) {
        // Retrieve the data for that position
        Contact contact = contactList.get(position);
        // Add the data to the view
        holder.avatarImageView.setImageResource(contact.getAvatarIcon());  // 设置联系人头像
        holder.nicknameTextView.setText(contact.getNickname());  // 设置联系人昵称

        holder.itemView.setTag(position);
        holder.acceptButton.setTag(position);
        holder.refuseButton.setTag(position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }
}