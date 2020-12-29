package com.dwh.es21x;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptService;

import java.io.IOException;
import java.net.InetAddress;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * 更新和删除
 * @author dengwenhao
 * date 2020-12-28
 */
public class DeleteAndUpdateApi {
    public static void main(String[] args) throws IOException {
        Client client = TransportClient.builder().build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.126.149"), 9300));
        //ES2.1.2中不存在delete_by_query，条件删除的API，删除只能根据具体ID来删除
        DeleteResponse response = client.prepareDelete("twitter", "tweet", "1").get();
    }

    private void updateData(Client client) throws Exception {
        /**
         * 更新操作，有三种
         * 第一种使用UpdateRequest类
         * 第二种使用prepareUpdate
         * 第三种也是使用prepareUpdate，但是使用脚本作为更新内容
         */
        //使用updateRequest
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index("index");
        updateRequest.type("type");
        updateRequest.id("1");
        updateRequest.doc(jsonBuilder()
                .startObject()
                .field("gender", "male")
                .endObject());
        client.update(updateRequest).get();
        //使用prepareUpdate
        client.prepareUpdate("index", "type", "1")
                .setDoc(jsonBuilder().startObject().field("gender", "male")
                .endObject()).get();
        //使用script
        client.prepareUpdate("ttl", "doc", "1")
                .setScript(new Script(
                        "ctx._source.gender = \"male\"",
                        ScriptService.ScriptType.INLINE, null, null))
                .get();

    }

    private void multiApi(Client client){
        MultiGetResponse multiGetItemResponses = client.prepareMultiGet()
                .add("twitter", "tweet", "1")
                .add("twitter", "tweet", "2", "3", "4")
                .add("another", "type", "foo")
                .get();

        for (MultiGetItemResponse itemResponse : multiGetItemResponses) {
            GetResponse response = itemResponse.getResponse();
            if (response.isExists()) {
                String json = response.getSourceAsString();
            }
        }
    }
}
