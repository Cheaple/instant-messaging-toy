package com.example.im.mvp.contract.discover;

import com.example.im.bean.discover.Discover;

public interface IPostContract {
    interface View {

    }
    interface Presenter {
        void postMoment();
    }
    interface Model {
        void postMoment(Discover discover);
    }
}
