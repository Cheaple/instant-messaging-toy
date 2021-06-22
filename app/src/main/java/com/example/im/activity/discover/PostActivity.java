package com.example.im.activity.discover;

import android.content.Context;
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

import com.dmcbig.mediapicker.PickerActivity;
import com.dmcbig.mediapicker.PickerConfig;
import com.dmcbig.mediapicker.entity.Media;
import com.example.im.R;
import com.example.im.adapter.discover.DiscoverAdapter;
import com.example.im.adapter.discover.ImageAdapter;
import com.example.im.mvp.contract.discover.IPostContract;
import com.example.im.mvp.presenter.discover.PostPresenter;
import java.util.ArrayList;


public class PostActivity extends AppCompatActivity implements IPostContract.View, View.OnClickListener {
    private static final int REQUEST_PICTURE = 100;

    private Context context;
    private IPostContract.Presenter mPresenter;

    private ImageAdapter imageAdapter;
    private RecyclerView recyclerView;
    private EditText editText;
    private Button postButton;
    private ArrayList<String> imageList = new ArrayList<>();
    private ArrayList<Media> selected = new ArrayList<>();  // 已选择的图片或视频

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moment_post);
        Intent intent = getIntent();

        context = getApplicationContext();
        mPresenter = new PostPresenter(this, context);

        recyclerView = (RecyclerView)findViewById(R.id.recycle_view_images);
        editText = (EditText)findViewById(R.id.edit_moment_text);
        postButton = (Button)findViewById(R.id.button_post);
        postButton.setOnClickListener(this);

        showImages();
    }

    @Override
    public void onClick(View view) {
        mPresenter.postMoment();
    }

    public void uploadPhotos() {
        Intent intent = new Intent(PostActivity.this, PickerActivity.class);
        intent.putExtra(PickerConfig.MAX_SELECT_COUNT, 9);  // 最大选择数量：9
        ArrayList<Media> defaultSelect = selected;
        intent.putExtra(PickerConfig.DEFAULT_SELECTED_LIST, defaultSelect);  // 设置默认选中的照片
        PostActivity.this.startActivityForResult(intent, REQUEST_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PICTURE && resultCode == PickerConfig.RESULT_CODE) {
            ArrayList<Media> newSelected = data.getParcelableArrayListExtra(PickerConfig.EXTRA_RESULT);  // 选择完后返回的list
            if (!newSelected.isEmpty() && !selected.equals(newSelected)) {  // 如果更新了被选图片，则刷新
                selected.clear();
                selected.addAll(newSelected);
                imageAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public ArrayList<Media> getSelected() {
        return  selected;
    }

    @Override
    public String getText() {
        return editText.getText().toString();
    }

    public void showImages() {
        imageAdapter = new ImageAdapter(selected, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(imageAdapter);
    }

    @Override
    public void gotoMainActivity() {
        finish();
    }

    @Override
    public void showText(String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }
}
