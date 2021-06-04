package com.example.im.adapter.discover;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.im.R;
import com.example.im.activity.discover.PostActivity;

import java.util.ArrayList;
import java.util.LinkedList;


import static android.app.PendingIntent.getActivity;

public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int IMAGE_TYPE_UPLOAD = 0x00001;  // 用于上传图片的加号图片
    private static final int IMAGE_TYPE_USER = 0x00002;  // 用户上传的图片

    private PostActivity context;
    private ArrayList<String> imageList;

    public ImageAdapter(ArrayList<String> imageList, PostActivity context) {
        this.context = context;
        this.imageList = imageList;
    }

    public static class UploadImageViewHolder extends RecyclerView.ViewHolder {
        private ImageAdapter mAdapter;
        private ImageView imageView;
        public UploadImageViewHolder(@NonNull View itemView) { super(itemView); }
        public UploadImageViewHolder(View itemView, ImageAdapter adapter) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.img_upload);
            this.mAdapter =  adapter;
        }
    }

    public static class UserImageViewHolder extends RecyclerView.ViewHolder {
        private ImageAdapter mAdapter;
        private ImageView imageView;
        public UserImageViewHolder(@NonNull View itemView) { super(itemView); }
        public UserImageViewHolder(View itemView, ImageAdapter adapter) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img_user);
            this.mAdapter =  adapter;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        parent.setPadding(20, 0, 20, 0);
        View mItemView;
        switch (viewType) {
            case IMAGE_TYPE_UPLOAD:
                mItemView = LayoutInflater.from(context).inflate(R.layout.item_recycle_img_user, parent, false);
                return new UploadImageViewHolder(mItemView);
            case IMAGE_TYPE_USER:
                mItemView = LayoutInflater.from(context).inflate(R.layout.item_recycle_img_upload, parent, false);
                return new UserImageViewHolder(mItemView);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof UploadImageViewHolder) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "1", Toast.LENGTH_SHORT).show();
                    context.uploadPhotos();
                }
            });
        } else if (holder instanceof UserImageViewHolder) {

        }
    }

    @Override
    public int getItemCount() {
        return imageList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount())
            return IMAGE_TYPE_UPLOAD;
        else
            return IMAGE_TYPE_USER;
    }
}
