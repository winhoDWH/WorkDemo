package com.dwh.http;

import com.alibaba.fastjson.JSONObject;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * http类请求工具类
 * 使用Java自带的http类
 * @author dwh
 */
public class JavaHttpRequestUtils {

    /**
     * @param urlString
     * @param obj
     * @throws Exception
     */
    public static JSONObject execute(String urlString, JSONObject obj) {
        DataOutputStream out = null;
        BufferedReader reader = null;
        HttpURLConnection connection = null;
        StringBuffer sb = new StringBuffer("");
        if(null == obj) {
            obj = new JSONObject();
        }
        try {
            // 创建连接
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setConnectTimeout(60000);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept-Charset", "utf-8");
            connection.connect();

            // POST请求
            out = new DataOutputStream(connection.getOutputStream());
            out.write(obj.toString().getBytes("utf-8"));
            out.flush();

            // 读取响应
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String lines;
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes());
                sb.append(lines);
            }
            return JSONObject.parseObject(sb.toString());
        } catch (MalformedURLException | UnsupportedEncodingException e) {
            System.out.println(e.getStackTrace());
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    System.out.println(e.getStackTrace());
                }
            }
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println(e.getStackTrace());
                }
            }
            if (null != out) {
                // 断开连接
                connection.disconnect();
            }
        }
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("REQ_ERROR_CODE", "-9999");
        jsonObj.put("REQ_ERROR_MSG", "请求服务("+urlString+")异常，请联系服务负责人");
        return jsonObj;
    }
}
