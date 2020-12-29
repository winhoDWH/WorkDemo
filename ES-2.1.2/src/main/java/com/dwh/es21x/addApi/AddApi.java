package com.dwh.es21x.addApi;

import com.alibaba.fastjson.JSON;
import entity.DocumentEntity;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetAddress;

/**
 * 往ES里面增加数据
 * @author dengwenhao
 * date 2020-12-28
 */
public class AddApi {
    public static void main(String[] args) throws Exception {
        Client client = TransportClient.builder().build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.126.149"), 9300));
        DocumentEntity entity = new DocumentEntity();
        entity.setCreatTime("2020-12-28 18:57:00");
        entity.setDocId("0002");
        entity.setUserName("admin2");
        //使用setSource可以设置请求体的信息
        //使用JSON插入数据
        IndexResponse response = client.prepareIndex("twitter", "tweet")
                .setSource(JSON.toJSONString(entity))
                .get();
        System.out.println(JSON.toJSONString(response));
        // Index name
        String index = response.getIndex();
        // Type name
        String type = response.getType();
        // Document ID (generated or not)
        String id = response.getId();
        // Version (if it's the first time you index this document, you will get: 1)
        long version = response.getVersion();
        // isCreated() is true if the document is a new one, false if it has been updated
        boolean created = response.isCreated();
    }
}
