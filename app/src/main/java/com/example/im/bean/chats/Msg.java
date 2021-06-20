package com.example.im.bean.chats;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.File;
import java.net.URI;

public class Msg {
    public final static int SPEAKER_TYPE_ME = 0;
    public final static int SPEAKER_TYPE_OTHER = 1;

    public final static int TYPE_MSG = 100;
    public final static int TYPE_PICTURE = 101;
    public final static int TYPE_VIDEO = 102;

    private int speaker;  // 0为自己，1为对方
    private int type;
    private String content;
    //private Bitmap picture;
    private Uri picture;
    private Uri video;

    public Msg(int speaker, int type, String content) {
        this.speaker = speaker;
        this.type = type;
        if (type == TYPE_MSG)
            this.content = content;
        else if (type == TYPE_PICTURE) {
            /*BitmapFactory.Options opt = new BitmapFactory.Options();
            opt.inPreferredConfig = Bitmap.Config.RGB_565;
            opt.inPurgeable = true;
            opt.inInputShareable = true;
            opt.inSampleSize = 16;
            picture = BitmapFactory.decodeFile(content);*/
            File picPath = new File(content);
            picture = Uri.fromFile(picPath);
        }
        else if (type == TYPE_VIDEO) {
            File picPath = new File(content);
            video = Uri.fromFile(picPath);
        }
    }

    public int getSpeaker() {
        return speaker;
    }

    public int getType() {
        return type;
    }

    public Uri getPicture() {
        return picture;
    }

    public Uri getVideo() {
        return video;
    }

    public String getContent() {
        return content;
    }
}
