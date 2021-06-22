package com.example.im.adapter.discover;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.example.im.R;
import com.example.im.bean.AccountInfo;
import com.example.im.bean.discover.Discover;
import com.example.im.bean.discover.Reply;
import com.example.im.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

@GlideModule
public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.DiscoverViewHolder> {
    private Context context;
    private OnItemClickListener mClickListener;
    private LinkedList<Discover> discoverList;

    public static class DiscoverViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private DiscoverAdapter mAdapter;
        private OnItemClickListener mListener;

        private View discoverItemView;
        public ImageView likeImageView;
        public ImageView commentImageView;
        public LinearLayout likesLayout;
        private TextView likesTextView;

        public int viewType;
        public ArrayList<String> likeList;
        public boolean ifLiked = false;  // 当前用户是否给该动态点赞
        public LinkedList<Reply> commentList = new LinkedList<>();
        public CommentAdapter commentAdapter;


        public DiscoverViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);
            this.viewType = viewType;
        }

        public DiscoverViewHolder(View itemView, int viewType, DiscoverAdapter adapter, OnItemClickListener listener) {
            super(itemView);
            this.mAdapter = adapter;
            this.mListener = listener;

            this.viewType = viewType;
            switch (viewType) {
                case 5:  // Video
                    this.discoverItemView = itemView.findViewById(R.id.moment_type_video);
                    break;
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
                    this.discoverItemView = itemView.findViewById(R.id.moment_type_text);
            }
            this.likeImageView = itemView.findViewById(R.id.img_like);
            this.commentImageView = itemView.findViewById(R.id.img_comment);
            this.likesLayout = itemView.findViewById(R.id.layout_likes);
            this.likesTextView = itemView.findViewById(R.id.text_likes);

            itemView.setOnClickListener(this);  // 为ItemView添加点击事件
            likeImageView.setOnClickListener(this);
            commentImageView.setOnClickListener(this);
        }

        public void setLikeList(ArrayList<String> likeList) {
            likeList.remove(null);  // 若点赞者不是当前用户的好友，则忽略之
            this.likeList = likeList;
        }

        public void updateLikes() {
            String likes_string = new String();
            for (int i = 0; i < likeList.size(); ++i) {
                if (i != 0) likes_string += ", ";
                likes_string = likes_string + likeList.get(i);
                if (likeList.get(i).equals(AccountInfo.getInstance().getNickname()))
                    ifLiked = true;  // 检查本用户是否已点赞
            }

            // 根据是否点赞，更改点赞按键颜色
            if (ifLiked)
                likeImageView.setImageResource(R.drawable.ic_like_2);
            else
                likeImageView.setImageResource(R.drawable.ic_like);

            if (likeList.size() > 0) {
                likesLayout.setVisibility(View.VISIBLE);
                likesTextView.setText(likes_string);
            } else  // 若无赞，则隐藏点赞条
                likesLayout.setVisibility(View.GONE);
        }

        public void giveLike() {
            likeList.add(AccountInfo.getInstance().getNickname());
            ifLiked = true;
            updateLikes();
        }

        public void cancelLike() {
            likeList.remove(AccountInfo.getInstance().getNickname());
            ifLiked = false;
            updateLikes();
        }

        public void onClick(View v) {
            mListener.onItemClick(v, (int) v.getTag());
        }
    }

    public DiscoverAdapter(LinkedList<Discover> discoverList, Context context) {
        this.discoverList = discoverList;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (this.discoverList.get(position).getMomentType().equals("TEXT"))
            return 0;
        else if (this.discoverList.get(position).getMomentType().equals("VIDEO"))
            return 5;
        else if (discoverList.get(position).getPicturesCnt() < 4)
            return  discoverList.get(position).getPicturesCnt();
        else
            return 4;
    }

    @NonNull
    @Override
    public DiscoverAdapter.DiscoverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView;
        switch (viewType) {
            case 1:
                mItemView = LayoutInflater.from(context).inflate(R.layout.item_recycle_discover_type1, parent, false);
                return new DiscoverAdapter.DiscoverViewHolder(mItemView, 1, this, mClickListener);
            case 2:
                mItemView = LayoutInflater.from(context).inflate(R.layout.item_recycle_discover_type2, parent, false);
                return new DiscoverAdapter.DiscoverViewHolder(mItemView, 2, this, mClickListener);
            case 3:
                mItemView = LayoutInflater.from(context).inflate(R.layout.item_recycle_discover_type3, parent, false);
                return new DiscoverAdapter.DiscoverViewHolder(mItemView, 3, this, mClickListener);
            case 4:
                mItemView = LayoutInflater.from(context).inflate(R.layout.item_recycle_discover_type4, parent, false);
                return new DiscoverAdapter.DiscoverViewHolder(mItemView, 4, this, mClickListener);
            case 5:
                mItemView = LayoutInflater.from(context).inflate(R.layout.item_recycle_discover_video, parent, false);
                return new DiscoverAdapter.DiscoverViewHolder(mItemView, 5, this, mClickListener);
            default:
                mItemView = LayoutInflater.from(context).inflate(R.layout.item_recycle_discover_type0, parent, false);
                return new DiscoverAdapter.DiscoverViewHolder(mItemView, 0, this, mClickListener);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull DiscoverViewHolder holder, int position) {
        // Retrieve the data for that position
        Discover moment = discoverList.get(position);
        // Add the data to the view
        ImageView imageView = holder.discoverItemView.findViewById(R.id.avatar_icon);
        String url = context.getString(R.string.server)+"/picture/" + moment.getAvatar();
        Glide.with(context).load(url).into(imageView);  // 设置联系人头像
        TextView textView;
        textView = holder.discoverItemView.findViewById(R.id.nickname_text);
        textView.setText(moment.getPublisher());

        textView = holder.discoverItemView.findViewById(R.id.moment_text);
        if (moment.getText() != null)  // 若存在文本
            textView.setText(moment.getText());  // 则显示之
        else
            textView.setVisibility(View.GONE);  // 否则隐藏文本栏

        textView = holder.discoverItemView.findViewById(R.id.published_time);
        textView.setText(moment.getTime());

        switch (holder.viewType) {
            case 5:
                VideoView videoView = holder.discoverItemView.findViewById(R.id.video_moment);
                url = context.getString(R.string.server) + "/video/" + moment.getVideo();
                videoView.setVideoURI(Uri.parse(url));
                break;
            case 4:
                url = context.getString(R.string.server)+"/picture/" + moment.getPictures().get(3);
                imageView = holder.discoverItemView.findViewById(R.id.picture4);
                Glide.with(context).load(url).into(imageView);  // 设置联系人头像
            case 3:
                url = context.getString(R.string.server)+"/picture/" + moment.getPictures().get(2);
                imageView = holder.discoverItemView.findViewById(R.id.picture3);
                Glide.with(context).load(url).into(imageView);  // 设置联系人头像
            case 2:
                url = context.getString(R.string.server)+"/picture/" + moment.getPictures().get(1);
                imageView = holder.discoverItemView.findViewById(R.id.picture2);
                Glide.with(context).load(url).into(imageView);  // 设置联系人头像
            case 1:
                url = context.getString(R.string.server)+"/picture/" + moment.getPictures().get(0);
                imageView = holder.discoverItemView.findViewById(R.id.picture1);
                Glide.with(context).load(url).into(imageView);  // 设置联系人头像
                break;
        }

        // 设置点赞列表
        holder.setLikeList(moment.getThumbs());
        holder.updateLikes();

        // 设置评论列表
        RecyclerView recyclerView = holder.discoverItemView.findViewById(R.id.comments_recyclerview);
        holder.commentAdapter = new CommentAdapter(moment.getReplies(), context);
        recyclerView.setAdapter(holder.commentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        holder.itemView.setTag(position);
        holder.likeImageView.setTag(position);
        holder.commentImageView.setTag(position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return discoverList.size();
    }
}
