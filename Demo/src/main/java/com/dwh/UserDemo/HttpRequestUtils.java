package com.dwh.UserDemo;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpRequestUtils {
    private static final Logger logger = LoggerFactory.getLogger(HttpRequestUtils.class);

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
            out.write(obj.toString().getBytes("utf-8")); //TODO 乱码处理（完成）
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
            logger.error("",e);
        } catch (IOException e) {
            logger.error("",e);
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error("",e);
                }
            }
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.error("",e);
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
