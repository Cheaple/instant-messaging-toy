package com.example.im.adapter.discover;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dmcbig.mediapicker.PickerConfig;
import com.dmcbig.mediapicker.entity.Media;
import com.example.im.R;
import com.example.im.activity.discover.PostActivity;

import java.util.ArrayList;
import java.util.LinkedList;


import static android.app.PendingIntent.getActivity;

public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int IMAGE_TYPE_UPLOAD = 0x00001;  // 用于上传图片的加号图片
    private static final int IMAGE_TYPE_USER = 0x00002;  // 用户上传的图片

    private PostActivity context;
    private ArrayList<Media> imageList;

    public ImageAdapter(ArrayList<Media> imageList, PostActivity context) {
        this.context = context;
        this.imageList = imageList;
    }

    public static class UploadImageViewHolder extends RecyclerView.ViewHolder {
        private ImageAdapter mAdapter;
        private ImageView imageView;
        public UploadImageViewHolder(@NonNull View itemView) { super(itemView); }
        public UploadImageViewHolder(View itemView, ImageAdapter adapter) {
            super(itemView);
            this.mAdapter =  adapter;
            imageView = (ImageView)itemView.findViewById(R.id.img_upload);
        }
    }

    public static class UserImageViewHolder extends RecyclerView.ViewHolder {
        private ImageAdapter mAdapter;
        private ImageView imageView;
        public UserImageViewHolder(@NonNull View itemView) { super(itemView); }
        public UserImageViewHolder(View itemView, ImageAdapter adapter) {
            super(itemView);
            this.mAdapter =  adapter;
            imageView = (ImageView) itemView.findViewById(R.id.img_user);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        parent.setPadding(20, 0, 20, 0);
        View mItemView;
        switch (viewType) {
            case IMAGE_TYPE_UPLOAD:
                mItemView = LayoutInflater.from(context).inflate(R.layout.item_recycle_img_upload, parent, false);
                return new UploadImageViewHolder(mItemView, this);
            case IMAGE_TYPE_USER:
                mItemView = LayoutInflater.from(context).inflate(R.layout.item_recycle_img_user, parent, false);
                return new UserImageViewHolder(mItemView, this);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof UploadImageViewHolder) {
            // 点击事件：上传照片或视频
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.uploadPhotos();
                }});
        }
        else if (holder instanceof UserImageViewHolder) {
            Media media = imageList.get(position);
            if (media.mediaType == 1) {  // 图片
                BitmapFactory.Options opt = new BitmapFactory.Options();
                opt.inPreferredConfig = Bitmap.Config.RGB_565;
                opt.inPurgeable = true;
                opt.inInputShareable = true;
                opt.inSampleSize = 16;
                Bitmap bitmap = BitmapFactory.decodeFile(media.path);
                ((UserImageViewHolder) holder).imageView.setImageBitmap(bitmap);
            }
            else { // 视频
                context.showText("Video");
            }
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
