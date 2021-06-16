package com.example.im.mvp.model.discover;

import android.os.Handler;
import android.os.Message;

import com.example.im.bean.discover.Discover;
import com.example.im.mvp.contract.discover.IPostContract;
import com.example.im.mvp.presenter.discover.DiscoverPresenter;
import com.example.im.mvp.presenter.discover.PostPresenter;

import java.lang.ref.WeakReference;
import java.util.LinkedList;

public class PostModel implements IPostContract.Model {
    private static final int POST_SUCCESS = 1;
    private static final int POST_FAILURE = 2;

    private PostModel.MyHandler mHandler;
    public PostModel(PostPresenter presenter) {
        mHandler = new PostModel.MyHandler(presenter);
    }

    private static class MyHandler extends Handler {
        private WeakReference<PostPresenter> mWeakReference;

        public MyHandler(PostPresenter presenter) {
            mWeakReference = new WeakReference<>(presenter);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            PostPresenter mPresenter = mWeakReference.get();
            switch (msg.what) {
                case POST_SUCCESS:
                    mPresenter.postSuccess();
                    break;
                case POST_FAILURE:
                    mPresenter.postFailure(msg.obj.toString());
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void postMoment(Discover discover) {
        // TODO: 发布动态
    }
}
