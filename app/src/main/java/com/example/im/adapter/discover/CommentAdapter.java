package com.example.im.adapter.discover;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.im.R;
import com.example.im.bean.discover.Comment;

import java.util.LinkedList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private LinkedList<Comment> data;
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
    public CommentAdapter(LinkedList<Comment> data, Context context) {
        this.data = data;
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
        Comment comment = data.get(position);
        holder.nicknameTextView.setText(comment.getCommenter() + ": ");  // 设置评论者昵称
        holder.contentTextView.setText(comment.getComment());  // 设置评论内容
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
