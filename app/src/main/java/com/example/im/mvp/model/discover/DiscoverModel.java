package com.example.im.mvp.model.discover;

import com.example.im.R;
import com.example.im.bean.discover.Discover;
import com.example.im.mvp.contract.discover.IDiscoverContract;

import java.util.ArrayList;
import java.util.LinkedList;

public class DiscoverModel implements IDiscoverContract.Model {
    @Override
    public LinkedList<Discover> loadMomentList() {
        LinkedList<Discover> moments = new LinkedList<Discover>();

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
        moments.add(new Discover("Bear", R.drawable.avatar1, "学长好", "1小时前", images0, likes0, comments0, commenters0));
        moments.add(new Discover("Frog", R.drawable.avatar2, "苟利国家生死以", "2小时前", images1, likes1, comments1, commenters1));

        // TODO: 导入动态数据
        return moments;
    }
}
