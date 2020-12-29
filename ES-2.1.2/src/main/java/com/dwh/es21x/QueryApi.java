package com.dwh.es21x;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author dengwenhao
 * date 2020-12-29
 */
public class QueryApi {

    public static void main(String[] args) throws Exception{
        //field = value
        QueryBuilder termBuilder = QueryBuilders.termQuery("field", "value");
        //参数：field, value数组，即field in (value数组)
        QueryBuilder termsBuilder = QueryBuilders.termsQuery("field", "value1", "value2");
        //默认闭区间
        QueryBuilder rangeBuilder = QueryBuilders.rangeQuery("field").from(5).to(10)
                .includeLower(true).includeUpper(false);
        //range另一种写法
        QueryBuilder rangBuilder2 = QueryBuilders.rangeQuery("field")
                .gt(5).lt(10);
        QueryBuilder existsBuilder = QueryBuilders.existsQuery("field");
        QueryBuilder missingBuilder = QueryBuilders.missingQuery("field")
                .existence(true).nullValue(true);
        QueryBuilder wildBuilder = QueryBuilders.wildcardQuery("field", "value");
        QueryBuilder boolBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.termQuery("field", "value"))
                .filter(QueryBuilders.termQuery("field", "value"))
                .mustNot(QueryBuilders.termQuery("field", "value"))
                .should(QueryBuilders.termQuery("field", "value"));


        Client client = TransportClient.builder().build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.126.149"), 9300));
        SearchResponse response = client.prepareSearch("index1").setTypes("mappings").setSearchType(SearchType.DEFAULT)
                .setQuery(QueryBuilders.termQuery("field", "value"))
                .setFrom(0).setSize(60)
                .execute().actionGet();
        List<Map<String, Object>> result = getSearchResult(response);
    }

    private static List<Map<String, Object>> getSearchResult(SearchResponse searchResponse){
        List<Map<String, Object>> result = new ArrayList<>();
        //先取出查询结果中第一层Hits，获取命中结果的一些信息
        SearchHits hit = searchResponse.getHits();
        float score = hit.getMaxScore();
        long count = hit.getTotalHits();
        //取出第二次hit，该hit是一个数组，其中每个元素的_source对应查询结果
        SearchHit[] hits = hit.getHits();
        for (SearchHit searchHit : hits){
            Map<String, Object> map = searchHit.getSource();
            result.add(map);
        }
        return result;
    }
}
