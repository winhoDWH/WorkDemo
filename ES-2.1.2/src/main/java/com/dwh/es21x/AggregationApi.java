package com.dwh.es21x;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.avg.Avg;
import org.elasticsearch.search.aggregations.metrics.max.Max;
import org.elasticsearch.search.aggregations.metrics.min.Min;
import org.elasticsearch.search.aggregations.metrics.sum.Sum;

import java.net.InetAddress;

/**
 * 聚合API
 * @author dengwenhao
 * date 2020-12-29
 */
public class AggregationApi {
    public static void main(String[] args) throws Exception{
        Client client = TransportClient.builder().build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.126.149"), 9300));
        AbstractAggregationBuilder aggregationBuilder = AggregationBuilders
                //使用什么类型的aggregation
                .terms("aggName").field("field")
                //按什么字段（orderField）进行排序，true为正序， false为倒序，默认是根据doc_count排序
                .order(Terms.Order.aggregation("orderField", false))
                //子聚合
                .subAggregation(AggregationBuilders.avg("childAggName").field("field"));
        AbstractAggregationBuilder topHitBuilder = AggregationBuilders.topHits("topHitAgg");
        SearchResponse response= client.prepareSearch("index").setTypes("type").setSearchType(SearchType.DEFAULT)
                //注意是使用addAggregation方法，不是set
                .addAggregation(aggregationBuilder).execute().actionGet();
    }

    /**
     * 聚合返回的结果存放在aggregations下，aggregations下有查询时定义的aggName聚合名，其中包含结合后的结果
     * 桶聚合报文格式：
     * {"aggregations": {
     *     "aggName": { //该聚合名是查询时定义的
     *      "buckets": [
     *          {
     *              //该桶的key，即查询时指定的聚合字段（group by fileName）取值之一
     *              "key":"",
     *              //该桶中的文档数
     *               "doc_count": 1,
     *               "子聚合名":{...}
     *          }
     *  }]}}
     * 接收类的都在org.elasticsearch.search.aggregations.包中
     * @param response
     */
    private static void getAggResult(SearchResponse response){
        Aggregations aggregations =response.getAggregations();
        //term形式聚合处理
        Terms agg = aggregations.get("aggName");
        for (Terms.Bucket bucket : agg.getBuckets()){
            //获取桶的key
            bucket.getKey();
            //获取该桶聚合了多少个文档
            bucket.getDocCount();
            //获取子聚合
            bucket.getAggregations().get("childAggName");
        }
        //avg平均值聚合处理
        Avg avg = aggregations.get("avgName");
        avg.getValue();
        //max与min最大/小值聚合处理
        Min min = aggregations.get("minName");
        Max max = aggregations.get("maxName");
        //sum总和
        Sum sum = aggregations.get("sumName");
    }
}
