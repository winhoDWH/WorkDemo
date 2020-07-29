package com.dwh.http;

import com.dwh.commonUtil.StringUtil;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 使用apache的http类进行请求
 * @author: dwh
 * @DATE: 2020/7/29
 */
@Log4j2
public class ApacheHttpUtils {

    private static final int defaultTimeout = 30 * 1000;

    private static CloseableHttpClient httpClient = HttpClients.createDefault();

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static String post(String url, Map<String, Object> params) throws IOException {

        String content = "";
        CloseableHttpResponse response = null;

        try {

            //公司环境需设置代理，提交时注释掉
//			HttpHost proxy = new HttpHost("10.200.100.81", 8080, "com.dwh.http");

            HttpPost post = new HttpPost(url);
            RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(defaultTimeout)
                    .setConnectTimeout(defaultTimeout)
                    .setSocketTimeout(defaultTimeout)
//					.setProxy(proxy)
                    .build();

            if (null != params && !params.isEmpty()) {
                List formparams = new ArrayList();
                Iterator it = params.keySet().iterator();
                String key = "";
                while (it.hasNext()) {
                    key = it.next().toString();
                    formparams.add(new BasicNameValuePair(key, params.get(key).toString()));
                }
                post.setEntity(new UrlEncodedFormEntity(formparams, "UTF-8"));
            }

            post.setConfig(config);
            response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            content = EntityUtils.toString(entity);
            EntityUtils.consume(entity);

        } catch (Exception e) {
            log.error(">>>>>>>", e);
        } finally {
            IOUtils.close(response);
        }

        return content;

    }

    public static String sendHttpPost(String url, String body) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.setEntity(new StringEntity(body));

        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String responseContent = EntityUtils.toString(entity, "UTF-8");
        response.close();
        httpClient.close();
        return responseContent;
    }

    public static void main(String[] args) {
        HttpPost post = new HttpPost("http://oepn.ajbcloud.com/SQ-User/Login");

        HttpHost proxy = new HttpHost("10.200.100.81", 8080, "com/dwh/http");

        RequestConfig config = RequestConfig.custom()
                .setConnectionRequestTimeout(10000)
                .setConnectTimeout(10000).setSocketTimeout(10000).setProxy(proxy)
                .build();
        post.setConfig(config);
        try {
            CloseableHttpResponse response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity);
            System.out.println(content);
        } catch (ClientProtocolException e) {
            log.error(">>>>>>>", e);
        } catch (IOException e) {
            log.error(">>>>>>>", e);
        }
    }
    /**
     * 发送get请求
     */
    private String sendGet(String url, Map<String, Object> params) {
        CloseableHttpResponse response = null;
        String content = "";
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            for (String key : params.keySet()) {
                uriBuilder.addParameter(key, StringUtil.toString(params.get(key)));
            }
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(defaultTimeout)
                    .setConnectTimeout(defaultTimeout)
                    .setSocketTimeout(defaultTimeout)
                    .build();
            httpGet.setConfig(config);
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            content = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
        } catch (Exception e) {
            ApacheHttpUtils.log.error("服务调用失败，url:" + url);
            log.error(">>>>>>>", e);
        } finally {
            IOUtils.closeQuietly(response);
        }
        return content;
    }
}
