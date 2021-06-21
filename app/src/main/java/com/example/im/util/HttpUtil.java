package com.example.im.util;


import android.text.TextUtils;
import android.util.Log;

import com.example.im.listener.HttpCallbackListener;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HttpUtil {
    private static String cookie;  // 登录以后再次发送http请求时，用于验证已登录的身份信息

    //封装的发送请求函数
    public static void sendHttpRequest(String address, JSONObject body, Boolean ifUpdateCookie, HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                try {
                    URL url = new URL(address);
                    conn = (HttpURLConnection) url.openConnection();
                    System.out.println("send http request = [" + address +"]");

                    if (!ifUpdateCookie && cookie != null) {  // 用cookie验证身份
                        System.out.println("set cookie = [" + cookie + "]");
                        conn.setRequestProperty("Cookie", cookie);
                    }
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);  // 连接超时时间
                    conn.setReadTimeout(8000);  // 读取超时异常
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    conn.connect();

                    // 构造body
                    if (body != null) {
                        OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
                        writer.write(body.toString());
                        writer.flush();
                    }

                    // 更新cookie
                    if (ifUpdateCookie) {
                        cookie = conn.getHeaderField("Set-Cookie");  // 获取cookie
                        System.out.println("get cookie = [" + cookie + "]");
                    }

                    // 获取响应
                    InputStream inputStream = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    String result = java.net.URLDecoder.decode(response.toString());  // 对http响应进行解码
                    System.out.println("Receive http response = " + result);
                    listener.onSuccess(result);
                } catch (Exception e) {
                    e.printStackTrace();
                    listener.onFailure(e);
                } finally {
                    if (conn != null)
                        conn.disconnect();
                }
            }
        }).start();
    }

    public static void uploadFile(String address, String path, String fileType, HttpCallbackListener listener) {
        String endl = "\r\n";
        String boundary = "*****";
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                try {
                    URL url = new URL(address);
                    conn = (HttpURLConnection) url.openConnection();
                    System.out.println("send http request = [" + address +"]");

                    if (cookie != null) {  // 用cookie验证身份
                        System.out.println("set cookie = [" + cookie + "]");
                        conn.setRequestProperty("Cookie", cookie);
                    }
                    conn.setConnectTimeout(8000);  // 连接超时时间
                    conn.setReadTimeout(8000);  // 读取超时异常
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
                    conn.connect();

                    // 构建 form-data
                    DataOutputStream writer = new DataOutputStream(conn.getOutputStream());
                    StringBuilder sb = new StringBuilder();
                    sb.append("--" + boundary + endl);
                    sb.append("Content-Disposition: form-data; name=\"fileType\"").append(endl);
                    sb.append("Content-Type: text/plain; charset=ASCII").append(endl + endl);
                    sb.append(fileType).append(endl);
                    writer.writeBytes(sb.toString());
                    writer.flush();

                    StringBuilder sb1 = new StringBuilder();
                    sb1.append("--" + boundary + endl);
                    sb1.append("Content-Disposition: form-data; name=\"file\"; filename=\""
                            + path + "\"").append(endl);
                    sb1.append("Content-Type: " + URLConnection.guessContentTypeFromName(path)).append(endl);
                    sb1.append("Content-Transfer-Encoding: binary").append(endl + endl);
                    writer.writeBytes(sb1.toString());

                    // 传输文件
                    InputStream fileInputStream = new FileInputStream(path);
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = fileInputStream.read(buffer)) != -1)
                        writer.write(buffer, 0, len);
                    fileInputStream.close();
                    writer.writeBytes(endl);
                    writer.flush();
                    writer.writeBytes("--" + boundary + "--" + endl);
                    writer.close();

                    // 获取响应
                    InputStream inputStream = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    String result = URLDecoder.decode(response.toString());  // 对http响应进行解码
                    System.out.println("Receive http response = " + result);
                    listener.onSuccess(result);
                } catch (Exception e) {
                    e.printStackTrace();
                    listener.onFailure(e);
                } finally {
                    if (conn != null)
                        conn.disconnect();
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
