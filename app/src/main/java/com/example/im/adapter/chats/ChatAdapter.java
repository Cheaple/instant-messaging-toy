package com.example.im.adapter.chats;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.im.R;
import com.example.im.bean.chats.Chat;

import java.util.LinkedList;

public class ChatAdapter extends BaseAdapter {

    private LinkedList<Chat> data;
    private Context context;

    public ChatAdapter(LinkedList<Chat> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_list_chat, parent, false);
        Chat chat = data.get(position);
        
        // 修改View中各个控件的属性，使之显示对应位置Chat的内容
        ImageView imageView = convertView.findViewById(R.id.avatar_icon);
        imageView.setImageResource(chat.getAvatarIcon());
        TextView textView;
        textView = convertView.findViewById(R.id.nickname_text);
        textView.setText(chat.getNickname());
        textView = convertView.findViewById(R.id.last_speak_time_text);
        textView.setText(chat.getLastSpeakTime());
        textView = convertView.findViewById(R.id.last_speak_text);
        textView.setText(chat.getLastSpeak());
        return convertView;
    }
}
