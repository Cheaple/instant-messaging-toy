package com.example.im.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.im.R;
import com.example.im.activity.discover.PostActivity;
import com.example.im.adapter.discover.DiscoverAdapter;
import com.example.im.bean.discover.Discover;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DiscoverFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiscoverFragment extends Fragment {
    public static final int TEXT_REQUEST = 1;
    private DiscoverAdapter discoverAdapter;
    private LinkedList<Discover> moments;
    private RecyclerView recyclerView;
    private View postView;

    public DiscoverFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ExploreFragment.
     */
    public static DiscoverFragment newInstance() {
        DiscoverFragment fragment = new DiscoverFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.discover_recyclerview);
        Context context = getActivity();

        postView = view.findViewById(R.id.new_moment);
        // 点击事件：跳转至动态发布界面
        postView.setOnClickListener(new  View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PostActivity.class);
                startActivityForResult(intent, TEXT_REQUEST);
            }
        });

        moments = new LinkedList<Discover>();
        ArrayList<String> likes0 = new ArrayList<String>();
        ArrayList<String> likes1 = new ArrayList<String>();
        likes0.add("何金龙");
        likes1.add("何金龙");
        likes1.add("陈宇乐");
        ArrayList<Integer> images0 = new ArrayList<Integer>();
        ArrayList<Integer> images1 = new ArrayList<Integer>();
        ArrayList<String> comments0 = new ArrayList<String>();
        ArrayList<String> comments1 = new ArrayList<String>();
        ArrayList<String> commenters0 = new ArrayList<String>();
        ArrayList<String> commenters1 = new ArrayList<String>();
        comments0.add("强");
        comments0.add("太强了");
        commenters0.add("徐金龙");
        commenters0.add("王继良老师");
        images1.add(R.drawable.image1);
        moments.add(new Discover(getString(R.string.nickname1), R.drawable.avatar1, getString(R.string.paragraph1), "1小时前", images0, likes0, comments0, commenters0));
        moments.add(new Discover(getString(R.string.nickname2), R.drawable.avatar2, getString(R.string.paragraph2), "2小时前", images1, likes1, comments1, commenters1));
        discoverAdapter = new DiscoverAdapter(moments, context);
        recyclerView.setAdapter(discoverAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_discover, container, false);
    }
}