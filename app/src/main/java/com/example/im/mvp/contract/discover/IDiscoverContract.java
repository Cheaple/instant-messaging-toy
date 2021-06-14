package com.example.im.mvp.contract.discover;

import com.example.im.bean.discover.Discover;

import java.util.LinkedList;

public interface IDiscoverContract {
    interface View {
        void setMomentList(LinkedList<Discover> list);
        void showText(String error);
    }
    interface Presenter {
        void showMomentList();
        void giveLike(int position);
        void cancelLike(int position);
        void makeComment(String content, int position);
    }
    interface Model {
        void loadMomentList();
    }
}
