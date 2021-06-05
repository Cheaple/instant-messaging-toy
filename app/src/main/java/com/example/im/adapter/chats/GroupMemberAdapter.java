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

import java.util.ArrayList;

public class GroupMemberAdapter extends RecyclerView.Adapter<GroupMemberAdapter.MemberViewHolder> {

    private GroupInfoActivity context;
    private ArrayList<Contact> memberList;

    public GroupMemberAdapter(ArrayList<Contact> memberList, GroupInfoActivity context) {
        this.context = context;
        this.memberList = memberList;
    }

    public static class MemberViewHolder extends RecyclerView.ViewHolder {
        private GroupMemberAdapter mAdapter;
        private ImageView imageView;
        private TextView textView;
        public MemberViewHolder(@NonNull View itemView) { super(itemView); }
        public MemberViewHolder(View itemView, GroupMemberAdapter adapter) {
            super(itemView);
            this.mAdapter =  adapter;
            imageView = (ImageView)itemView.findViewById(R.id.img_group_member);
            textView = (TextView)itemView.findViewById(R.id.text_group_member);
        }
    }

    @NonNull
    @Override
    public GroupMemberAdapter.MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        parent.setPadding(20, 0, 20, 0);
        View mItemView = LayoutInflater.from(context).inflate(R.layout.item_recycle_group_member, parent, false);
        return new GroupMemberAdapter.MemberViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder holder, int position) {
        if (position == getItemCount() - 1) {
            holder.textView.setVisibility(View.INVISIBLE);
            // 点击事件：邀请联系人加入群聊
            /*holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Invite", Toast.LENGTH_SHORT).show();
                    // TODO: 邀请联系人
                }
            });*/
        }
        else {
            // 点击事件：查看群聊成员信息
            /*holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Member", Toast.LENGTH_SHORT).show();
                    // TODO: 查看群聊成员信息
                }
            });*/
        }
    }

    @Override
    public int getItemCount() {
        return memberList.size() + 1;
    }
}
