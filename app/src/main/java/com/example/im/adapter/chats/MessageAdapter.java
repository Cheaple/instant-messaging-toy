package com.example.im.adapter.chats;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.im.R;
import com.example.im.bean.chats.Msg;

import java.util.LinkedList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {
    private Context context;
    private LinkedList<Msg> msgList;

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        private MessageAdapter mAdapter;
        private View msgItemView;
        private LinearLayout leftLayout;
        private LinearLayout rightLayout;
        private TextView leftTextView;
        private TextView rightTextView;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        public MessageViewHolder(View itemView, MessageAdapter adapter) {
            super(itemView);
            this.mAdapter = adapter;  // Associate with this adapter
            this.msgItemView = itemView.findViewById(R.id.msg);
            this.leftLayout = (LinearLayout)itemView.findViewById(R.id.left_speaker);
            this.rightLayout = (LinearLayout)itemView.findViewById(R.id.right_speaker);
            this.leftTextView = (TextView)itemView.findViewById(R.id.msg_text_left);
            this.rightTextView = (TextView)itemView.findViewById(R.id.msg_text_right);
        }
    }
    public MessageAdapter(LinkedList<Msg> msgList, Context context) {
        this.msgList = msgList;
        this.context = context;
    }

    @NonNull
    @Override
    public MessageAdapter.MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView;
        mItemView = LayoutInflater.from(context).inflate(R.layout.item_recycle_msg, parent, false);
        return new MessageAdapter.MessageViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        //Toast.makeText(context, "btn2", Toast.LENGTH_SHORT).show();

        Msg msg = msgList.get(position);
        if(msg.getSpeaker() == 0) {  // 接受消息
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightTextView.setText(msg.getContent());
        } else if(msg.getSpeaker() == 1) {  // 发送消息
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftTextView.setText(msg.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }
}
