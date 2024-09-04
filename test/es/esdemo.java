/*
package com.mx.es;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

*/
/**
 * @program: springbootdemo
 * @description:
 * @author:
 * @create: 2020-08-18 16:25
 * @Modified By:
 * @Version: 1.0
 **//*


@RestController
public class esdemo {

    @Resource
    private RestHighLevelClient client;

    @Test
    public void createIndex() {

        CreateIndexRequest request = new CreateIndexRequest("hightest");
        try {
            CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
            System.out.println(createIndexResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
//    public SearchResponse searchMessage(BoolQueryBuilder boolQueryBuilder) {
    public void searchMessage() {
        try {
            BoolQueryBuilder query = new BoolQueryBuilder();
            query.must(QueryBuilders.termsQuery("name.keyword", "李四"));
//            SearchRequest searchRequest = new SearchRequest("zz_dn_traffic_index");
            SearchRequest searchRequest = new SearchRequest("user_index");
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//            sourceBuilder.size(100);
            sourceBuilder.query(QueryBuilders.matchAllQuery());
            searchRequest.source(sourceBuilder);
            System.out.println(client);
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            searchResponse.getHits().forEach(message -> {
                try {
                    System.out.println(message.getSourceAsString());
//                    String sourceAsString = message.getSourceAsString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
//            return searchResponse;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Couldn't get Detail");
        }
    }
}
*/
