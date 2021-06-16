package com.example.im.mvp.contract.discover;

import com.dmcbig.mediapicker.entity.Media;
import com.example.im.bean.discover.Discover;

import java.util.ArrayList;

public interface IPostContract {
    interface View {
        ArrayList<Media> getSelected();
        String getText();
        void gotoMainActivity();
        void showText(String error);
    }
    interface Presenter {
        void postMoment();
    }
    interface Model {
        void postMoment(Discover discover);
    }
}
