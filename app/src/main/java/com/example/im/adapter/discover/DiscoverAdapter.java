package com.example.im.adapter.discover;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.im.R;
import com.example.im.bean.discover.Comment;
import com.example.im.bean.discover.Discover;

import java.util.ArrayList;
import java.util.LinkedList;

public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.DiscoverViewHolder> {
    private LinkedList<Discover> discoverList;
    private Context context;

    public static class DiscoverViewHolder extends RecyclerView.ViewHolder {
        private DiscoverAdapter mAdapter;
        private View discoverItemView;
        private ImageView likeImageView;
        private ImageView commentImageView;

        public LinkedList<Comment> commentList = new LinkedList<Comment>();
        public CommentAdapter commentAdapter;

        public int imageCount;

        public DiscoverViewHolder(@NonNull View itemView, int imageCount) {
            super(itemView);
            this.imageCount = imageCount;
        }
        public DiscoverViewHolder(View itemView, int imageCount, DiscoverAdapter adapter) {
            super(itemView);
            this.mAdapter = adapter;

            // Get the layout
            this.imageCount = imageCount;
            switch (imageCount) {
                case 1:
                    this.discoverItemView = itemView.findViewById(R.id.moment_type1);
                    break;
                case 2:
                    this.discoverItemView = itemView.findViewById(R.id.moment_type2);
                    break;
                case 3:
                    this.discoverItemView = itemView.findViewById(R.id.moment_type3);
                    break;
                case 4:
                    this.discoverItemView = itemView.findViewById(R.id.moment_type4);
                    break;
                default:
                    this.discoverItemView = itemView.findViewById(R.id.moment_type0);
            }

            this.likeImageView = itemView.findViewById(R.id.img_like);
            this.commentImageView = itemView.findViewById(R.id.img_comment);
        }
    }
    public DiscoverAdapter(LinkedList<Discover> discoverList, Context context) {
        this.discoverList = discoverList;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return this.discoverList.get(position).getImageCount();
    }

    @NonNull
    @Override
    public DiscoverAdapter.DiscoverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView;
        switch (viewType) {
            case 1:
                mItemView = LayoutInflater.from(context).inflate(R.layout.item_recycle_discover_type1, parent, false);
                return new DiscoverAdapter.DiscoverViewHolder(mItemView, 1, this);
            case 2:
                mItemView = LayoutInflater.from(context).inflate(R.layout.item_recycle_discover_type2, parent, false);
                return new DiscoverAdapter.DiscoverViewHolder(mItemView, 2, this);
            case 3:
                mItemView = LayoutInflater.from(context).inflate(R.layout.item_recycle_discover_type3, parent, false);
                return new DiscoverAdapter.DiscoverViewHolder(mItemView, 3, this);
            case 4:
                mItemView = LayoutInflater.from(context).inflate(R.layout.item_recycle_discover_type4, parent, false);
                return new DiscoverAdapter.DiscoverViewHolder(mItemView, 4, this);
            default:
                mItemView = LayoutInflater.from(context).inflate(R.layout.item_recycle_discover_type0, parent, false);
                return new DiscoverAdapter.DiscoverViewHolder(mItemView, 0, this);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull DiscoverViewHolder holder, int position) {
        // Retrieve the data for that position
        Discover moment = discoverList.get(position);
        // Add the data to the view
        ImageView imageView = holder.discoverItemView.findViewById(R.id.avatar_icon);
        imageView.setImageResource(moment.getAvatarIcon());
        TextView textView;
        textView = holder.discoverItemView.findViewById(R.id.nickname_text);
        textView.setText(moment.getNickname());
        textView = holder.discoverItemView.findViewById(R.id.moment_text);
        textView.setText(moment.getText());
        textView = holder.discoverItemView.findViewById(R.id.published_time);
        textView.setText(moment.getPublishedTime());

        // 动态图片
        switch (holder.imageCount) {
            case 1:
                imageView = holder.discoverItemView.findViewById(R.id.picture1);
                imageView.setImageResource(moment.getImages().get(0));
                break;
            case 2:
                imageView = holder.discoverItemView.findViewById(R.id.picture1);
                imageView.setImageResource(moment.getImages().get(0));
                imageView = holder.discoverItemView.findViewById(R.id.picture2);
                imageView.setImageResource(moment.getImages().get(1));
                break;
            case 3:
                imageView = holder.discoverItemView.findViewById(R.id.picture1);
                imageView.setImageResource(moment.getImages().get(0));
                imageView = holder.discoverItemView.findViewById(R.id.picture2);
                imageView.setImageResource(moment.getImages().get(1));
                imageView = holder.discoverItemView.findViewById(R.id.picture3);
                imageView.setImageResource(moment.getImages().get(2));
                break;
            case 4:
                imageView = holder.discoverItemView.findViewById(R.id.picture1);
                imageView.setImageResource(moment.getImages().get(0));
                imageView = holder.discoverItemView.findViewById(R.id.picture2);
                imageView.setImageResource(moment.getImages().get(1));
                imageView = holder.discoverItemView.findViewById(R.id.picture3);
                imageView.setImageResource(moment.getImages().get(2));
                imageView = holder.discoverItemView.findViewById(R.id.picture4);
                imageView.setImageResource(moment.getImages().get(3));
                break;
        }

        // 点赞列表
        textView = holder.discoverItemView.findViewById(R.id.likes);
        ArrayList<String> likes = moment.getLikes();
        String likes_list = new String();
        for (int i = 0; i < likes.size(); ++i) {
            if (i != 0) likes_list += ", ";
            likes_list = likes_list + likes.get(i);
        }
        textView.setText(likes_list);

        // 评论列表
        RecyclerView recyclerView = holder.discoverItemView.findViewById(R.id.comments_recyclerview);

        int comment_cnt = moment.getComments().size();
        for (int i = 0; i < comment_cnt; ++i)
            holder.commentList.add(new Comment(moment.getComments().get(i), moment.getCommenter().get(i)));
        holder.commentAdapter = new CommentAdapter(holder.commentList, context);
        recyclerView.setAdapter(holder.commentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        giveLike(holder);
        makeComment(holder);
    }

    @Override
    public int getItemCount() {
        return discoverList.size();
    }

    private void giveLike(@NonNull DiscoverViewHolder holder) {
        // 点击事件：点赞
        holder.likeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 点赞
                ((ImageView)v).setImageResource(R.drawable.ic_like_2);
                Toast.makeText(context, "btn2", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void makeComment(@NonNull DiscoverViewHolder holder) {
        // 点击事件：评论
        holder.commentImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View v = LayoutInflater.from(context).inflate(R.layout.dialog_input, null);
                builder.setView(v);
                builder.setMessage("Comment");
                final EditText editText = (EditText)v.findViewById(R.id.edit_dialog);
                editText.setHint("");
                editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(30)});
                editText.setMaxLines(10);
                builder.setPositiveButton("Comment", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String comment = editText.getText().toString().trim();
                        if (!"".equals(comment)) {
                            // TODO: 评论
                            holder.commentList.add(new Comment(comment, "Me"));
                            holder.commentAdapter.notifyDataSetChanged();
                        }
                    }
                });
                builder.show();
            }
        });
    }
}
