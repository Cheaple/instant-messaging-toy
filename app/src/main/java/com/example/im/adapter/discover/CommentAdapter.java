package com.example.im.adapter.discover;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.im.R;
import com.example.im.bean.discover.Reply;

import java.util.LinkedList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private LinkedList<Reply> commentList;
    private Context context;

    public static class CommentViewHolder extends RecyclerView.ViewHolder {
        private CommentAdapter mAdapter;
        private View commentItemView;
        private TextView contentTextView;
        private TextView nicknameTextView;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        public CommentViewHolder(View itemView, CommentAdapter adapter) {
            super(itemView);
            this.commentItemView = itemView.findViewById(R.id.comment);
            this.mAdapter = adapter;
            this.contentTextView = itemView.findViewById(R.id.comment_content);
            this.nicknameTextView = itemView.findViewById(R.id.comment_nickname);
        }
    }
    public CommentAdapter(LinkedList<Reply> commentList, Context context) {
        this.commentList = commentList;
        this.context = context;
    }

    @NonNull
    @Override
    public CommentAdapter.CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView;
        mItemView = LayoutInflater.from(context).inflate(R.layout.item_recycle_comment, parent, false);
        return new CommentAdapter.CommentViewHolder(mItemView, this);

    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Reply comment = commentList.get(position);
        holder.nicknameTextView.setText(comment.getSender() + ": ");  // 设置评论者昵称
        holder.contentTextView.setText(comment.getText());  // 设置评论内容
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }
}
