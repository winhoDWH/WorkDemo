package com.dwh.es21x;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetAddress;

/**
 * @author dengwenhao
 * date 2020-12-23
 */
public class EsConnect {

    public static void main(String[] args) throws Exception {
        //使用的集群名为默认的elasticsearch
        Client client = TransportClient.builder().build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("172.25.20.93"), 9300));
        //当使用的集群名为bigdata时
        //注意需要添加es集群地址
        Settings settings = Settings.settingsBuilder().put("cluster.name", "bigdata").build();
        client = TransportClient.builder().settings(settings).build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("172.25.20.93"), 9300));
    }
    
}
