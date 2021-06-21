package com.example.im.adapter.chats;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.im.R;
import com.example.im.activity.chats.GroupInfoActivity;
import com.example.im.bean.contacts.Contact;
import com.example.im.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.LinkedList;

public class GroupMemberAdapter extends RecyclerView.Adapter<GroupMemberAdapter.MemberViewHolder> {
    private GroupInfoActivity context;
    private OnItemClickListener mClickListener;
    private LinkedList<Contact> memberList;

    public GroupMemberAdapter(LinkedList<Contact> memberList, GroupInfoActivity context) {
        this.context = context;
        this.memberList = memberList;
    }

    public static class MemberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private GroupMemberAdapter mAdapter;
        private OnItemClickListener mListener;
        private ImageView imageView;
        private TextView textView;

        public MemberViewHolder(@NonNull View itemView) { super(itemView); }
        public MemberViewHolder(View itemView, GroupMemberAdapter adapter, OnItemClickListener listener) {
            super(itemView);
            this.mAdapter =  adapter;
            this.mListener = listener;
            imageView = (ImageView)itemView.findViewById(R.id.img_group_member);
            textView = (TextView)itemView.findViewById(R.id.text_group_member);
            itemView.setOnClickListener(this);  // 为ItemView添加点击事件
        }

        public void onClick(View v) {
            mListener.onItemClick(v, (int) v.getTag());
        }
    }

    @NonNull
    @Override
    public GroupMemberAdapter.MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        parent.setPadding(20, 0, 20, 0);
        View mItemView = LayoutInflater.from(context).inflate(R.layout.item_recycle_group_member, parent, false);
        return new GroupMemberAdapter.MemberViewHolder(mItemView, this, mClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder holder, int position) {
        holder.itemView.setTag(position);
        if (position == getItemCount() - 1) {
            holder.textView.setVisibility(View.INVISIBLE);  // 隐藏邀请按键的名词栏
        }
        else {
            Contact contact = memberList.get(position);
            //holder.imageView.setImageResource(contact.getAvatar());
            holder.textView.setText(contact.getNickname());
        }
        holder.itemView.setTag(position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return memberList.size() + 1;
    }
}
