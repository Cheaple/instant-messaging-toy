package com.example.im.util;


import com.example.im.listener.HttpCallbackListener;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class HttpUtil {
    //封装的发送请求函数
    public static void sendHttpRequest(final String address, JSONObject body, final HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection con = null;
                try {
                    URL url = new URL(address);
                    con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setConnectTimeout(8000);  // 连接超时时间
                    con.setReadTimeout(8000);  // 读取超时异常
                    con.setDoInput(true);
                    con.setDoOutput(true);
                    con.connect();
                    if (body != null) {
                        OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
                        writer.write(body.toString());
                        writer.flush();
                    }

                    // 获取响应
                    InputStream inputStream = con.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){
                        response.append(line);
                    }
                    listener.onSuccess(response.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    listener.onFailure(e);
                } finally {
                    if (con != null)
                        con.disconnect();
                }
            }
        }).start();
    }

    //组装出带参数的完整URL
    public static String getUrlWithParams(String address,HashMap<String,String> params) throws UnsupportedEncodingException {
        final String encode = "UTF-8";  //设置编码

        StringBuilder url = new StringBuilder(address);
        url.append("?");
        //将map中的key，value构造进入URL中
        for(Map.Entry<String, String> entry:params.entrySet()) {
            url.append(entry.getKey()).append("=");
            url.append(URLEncoder.encode(entry.getValue(), encode));
            url.append("&");
        }
        url.deleteCharAt(url.length() - 1);  //删掉最后一个&
        return url.toString();
    }
}
