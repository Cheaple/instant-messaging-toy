package com.example.im.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


import com.example.im.R;
import com.example.im.activity.discover.PostActivity;
import com.example.im.adapter.discover.DiscoverAdapter;
import com.example.im.bean.discover.Discover;
import com.example.im.bean.discover.Reply;
import com.example.im.listener.OnItemClickListener;
import com.example.im.mvp.contract.discover.IDiscoverContract;
import com.example.im.mvp.presenter.discover.DiscoverPresenter;

import java.util.LinkedList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DiscoverFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiscoverFragment extends Fragment implements IDiscoverContract.View, OnItemClickListener {
    private Context context;
    private IDiscoverContract.Presenter mPresenter;

    private DiscoverAdapter discoverAdapter;
    private RecyclerView recyclerView;

    public DiscoverFragment() {
        // Required empty public constructor
    }

    public static DiscoverFragment newInstance() {
        DiscoverFragment fragment = new DiscoverFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        mPresenter = new DiscoverPresenter(this, context);

        recyclerView = view.findViewById(R.id.discover_recyclerview);

        mPresenter.showMomentList();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discover, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem item = menu.add("New Moment");
        item.setIcon(R.drawable.ic_new_discover);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 点击事件：跳转至动态发布界面
        Intent intent = new Intent(getActivity(), PostActivity.class);
        startActivityForResult(intent, 1);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (view.getId()){
            case R.id.img_like:  // 点击事件：点赞
                giveLike(position);
                break;
            case R.id.img_comment:  // 点击事件：评论
                makeComment(position);
                break;
            default:
                break;
        }
    }

    private void giveLike(int position) {
        // 获取被点击的item的holder
        View v = recyclerView.getChildAt(position);
        System.out.println(position);
        DiscoverAdapter.DiscoverViewHolder holder = (DiscoverAdapter.DiscoverViewHolder) recyclerView.getChildViewHolder(v);
        if (!holder.ifLiked) {
            holder.giveLike();
            mPresenter.giveLike(position);
        }
        else {
            holder.cancelLike();
            mPresenter.cancelLike(position);
        }
    }

    private void makeComment(int position) {
        // 获取被点击的item的holder
        View v = recyclerView.getChildAt(position);
        DiscoverAdapter.DiscoverViewHolder holder = (DiscoverAdapter.DiscoverViewHolder )recyclerView.getChildViewHolder(v);

        // 弹出对话框：输入评论内容
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        v = LayoutInflater.from(context).inflate(R.layout.dialog_input, null);
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
                    holder.commentList.add(new Reply(comment, "Me"));
                    holder.commentAdapter.notifyDataSetChanged();
                    mPresenter.makeComment(comment, position);
                }
            }
        });
        builder.show();
    }

    @Override
    public void setMomentList(LinkedList<Discover> list) {
        discoverAdapter = new DiscoverAdapter(list, context);
        discoverAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(discoverAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public void showText(String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }
}