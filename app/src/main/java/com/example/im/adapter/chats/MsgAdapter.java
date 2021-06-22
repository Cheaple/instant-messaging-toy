package com.example.im.adapter.chats;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.example.im.R;
import com.example.im.bean.AccountInfo;
import com.example.im.bean.chats.Msg;

import org.w3c.dom.Text;

import java.util.LinkedList;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.MessageViewHolder> {
    private Context context;
    private LinkedList<Msg> msgList;

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        private MsgAdapter mAdapter;
        private View msgItemView;
        private ConstraintLayout leftLayout;
        private ConstraintLayout rightLayout;
        private TextView leftTextView;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        public MessageViewHolder(View itemView, MsgAdapter adapter) {
            super(itemView);
            this.mAdapter = adapter;  // Associate with this adapter
            this.msgItemView = itemView.findViewById(R.id.msg);
            this.leftLayout = (ConstraintLayout)itemView.findViewById(R.id.layout_left);
            this.rightLayout = (ConstraintLayout)itemView.findViewById(R.id.layout_right);
            this.leftTextView = (TextView)itemView.findViewById(R.id.msg_text_left);
        }
    }
    public MsgAdapter(LinkedList<Msg> msgList, Context context) {
        this.msgList = msgList;
        this.context = context;
    }

    @NonNull
    @Override
    public MsgAdapter.MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView;
        mItemView = LayoutInflater.from(context).inflate(R.layout.item_recycle_msg, parent, false);
        return new MsgAdapter.MessageViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Msg msg = msgList.get(position);
        if (msg.getSpeaker() == AccountInfo.getInstance().getUsername()) { // 发送消息
            holder.leftLayout.setVisibility(View.GONE);  // 隐藏左消息栏
            holder.rightLayout.setVisibility(View.VISIBLE);  // 显示右消息栏
            switch (msg.getType()) {
                case Msg.TYPE_MSG:
                    holder.itemView.findViewById(R.id.img_picture_right).setVisibility(View.GONE);
                    holder.itemView.findViewById(R.id.video_right).setVisibility(View.GONE);
                    TextView textView = holder.itemView.findViewById(R.id.text_msg_right);
                    textView.setVisibility(View.VISIBLE);
                    textView.setText(msg.getContent());
                    break;
                case Msg.TYPE_PICTURE:
                    holder.itemView.findViewById(R.id.text_msg_right).setVisibility(View.GONE);
                    holder.itemView.findViewById(R.id.video_right).setVisibility(View.GONE);
                    ImageView imageView = holder.itemView.findViewById(R.id.img_picture_right);
                    imageView.setVisibility(View.VISIBLE);
                    imageView.setImageURI(msg.getPicture());
                    break;
                case Msg.TYPE_VIDEO:
                    holder.itemView.findViewById(R.id.text_msg_right).setVisibility(View.GONE);
                    holder.itemView.findViewById(R.id.img_picture_right).setVisibility(View.GONE);
                    VideoView videoView = holder.itemView.findViewById(R.id.video_right);
                    videoView.setVisibility(View.VISIBLE);
                    videoView.setVideoURI(msg.getVideo());
                    //videoView.setMediaController(new MediaController(context));
                    videoView.start();
                    break;
            }
        }
        else {  // 接受消息
            holder.leftLayout.setVisibility(View.VISIBLE);  // 显示左消息栏
            holder.rightLayout.setVisibility(View.GONE);  // 隐藏右消息栏
        }
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }
}
