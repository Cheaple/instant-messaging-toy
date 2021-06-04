package com.example.im.activity.discover;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.im.R;
import com.example.im.adapter.discover.ImageAdapter;

import java.util.ArrayList;

import me.nereo.multi_image_selector.MultiImageSelector;

public class PostActivity extends AppCompatActivity {
    private static final int REQUEST_PHOTOS = 0x00001;

    private ImageAdapter imageAdapter;
    private RecyclerView recyclerView;
    private EditText inputText;
    private Button postButton;
    private ArrayList<String> imageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moment_post);
        Intent intent = getIntent();

        imageAdapter = new ImageAdapter(imageList, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView = (RecyclerView)findViewById(R.id.recycle_view_images);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(imageAdapter);


        inputText = (EditText)findViewById(R.id.edit_moment_text);
        postButton = (Button)findViewById(R.id.button_post);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO：发布动态
            }
        });
    }

    public void uploadPhotos() {
        MultiImageSelector selector = MultiImageSelector.create(this);
        selector.showCamera(true);
        selector.multi();
        selector.origin(imageList);
        selector.start(PostActivity.this, REQUEST_PHOTOS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PHOTOS) {
            Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
