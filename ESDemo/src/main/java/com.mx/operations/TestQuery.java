package com.mx.operations;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.Test;

/**
 * @program: StuDemo
 * @description:
 * @author:
 * @create: 2020-06-11 17:32
 * @Modified By:
 * @Version: 1.0
 **/
public class TestQuery {


    TransportClient client = EsUtils.getEsClient();
    /**
     * QueryBuilder 子类查询
     */
    @Test
    public void query2() {
        //match_all 查询所有
        MatchAllQueryBuilder builder = QueryBuilders.matchAllQuery();
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch("bayonet_202006")
                .setQuery(builder)
                .setSize(10); //返回多少条数据
        SearchResponse searchResponse = searchRequestBuilder.get();
        SearchHits hits = searchResponse.getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
        EsUtils.closeClient();
    }

}
