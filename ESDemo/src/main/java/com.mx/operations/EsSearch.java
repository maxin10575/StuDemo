package com.mx.operations;

import com.mx.utils.EsUtils;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.sort.SortOrder;

import org.junit.Test;


/**
 * @program: StuDemo
 * @description:
 * @author:
 * @create: 2020-07-22 10:37
 * @Modified By:
 * @Version: 1.0
 **/
public class EsSearch {
    TransportClient client = EsUtils.getEsClient();

    /**
     * QueryBuilder 子类查询
     * es查询都需要指定查询的条数，没指定会只返回10条数据。
     */
    @Test
    public void query2() {
        //matchAllQuery 查询所有
        MatchAllQueryBuilder builder1 = QueryBuilders.matchAllQuery();

        // matchQuery  模糊匹配
//        matchQuery：会将搜索词分词，再与目标查询字段进行匹配，若分词中的任意一个词与目标字段匹配上，则可查询到
        MatchQueryBuilder builder2 = QueryBuilders.matchQuery("name", "2");

        //termQuery 完全匹配  可一次匹配多个值
//       termQuery：不会对搜索词进行分词处理，而是作为一个整体与目标字段进行匹配，若完全匹配，则可查询到
        TermsQueryBuilder builder3 = QueryBuilders.termsQuery("name.keyword", "李四");
//      或    QueryBuilder builder6 = QueryBuilders.termQuery("name.keyword", "张三");ß
        TermsQueryBuilder builder4 = QueryBuilders.termsQuery("bz.keyword", "bz1", "备注哈哈");

        //  multiMatchQuery 模糊匹配   查找包含text的文档
        MultiMatchQueryBuilder builder5 = QueryBuilders.multiMatchQuery("李四", "name", "bz");

        SearchRequestBuilder searchRequestBuilder = client.prepareSearch("user_index")
                .setQuery(builder3);
//                .setSize(10); //返回多少条数据
        SearchResponse searchResponse = searchRequestBuilder.get();
        SearchHits hits = searchResponse.getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
        com.mx.utils.EsUtils.closeClient();
    }

    /**
     * 范围  通配符 前缀 模糊查询
     */
    @Test
    public void query1() {
        //范围查询
        QueryBuilder queryBuilder1 = QueryBuilders.rangeQuery("age").from("-20").to("40");

        //通配符查询 * 零个或多个
        QueryBuilder queryBuilder2 = QueryBuilders.wildcardQuery("name.keyword", "李*1");

        //前缀查询
        QueryBuilder queryBuilder3 = QueryBuilders.prefixQuery("name.keyword", "张");

        //模糊查询 查询类型的  同 matchQuery
        QueryBuilder queryBuilder4 = QueryBuilders.fuzzyQuery("name", "2");

        //id查询
        QueryBuilder queryBuilder5 = QueryBuilders.idsQuery().addIds("AXN0g12f-41f2ecMs0wl", "AXN0haCH-41f2ecMs0wn", "4");
        SearchRequestBuilder index = client.prepareSearch("user_index").setQuery(queryBuilder5);
//                .setSize(10);
        SearchHits hits = index.get().getHits();
        for (SearchHit hit : hits
        ) {
            System.out.println(hit.getSourceAsString());
        }
    }

    /**
     * 聚合查询
     * 更多见： https://www.csdn.net/gather_2e/NtTaUgzsNDk3LWJsb2cO0O0O.html
     */
    @Test
    public void aggregation() throws Exception {
        AggregationBuilder aggregationBuilder = AggregationBuilders.max("max_userid").field("userid");
        SearchResponse index3 = client.prepareSearch("user_index")
                .addAggregation(aggregationBuilder).get();
    /*    Max max = index3.getAggregations().get("max_userid");
        System.out.println(max.getValue());*/
    }

    //过滤
    // gt，选填项。大于
    //gte，选填项。大于等于
    //lt，选填项。小于
    //lte，选填项。小于等于
    @Test
    public void filter() {
        QueryBuilder postFilter = QueryBuilders.rangeQuery("age").gte(40).lte(60);
        SearchResponse searchResponse = client.prepareSearch("user_index")
                .setPostFilter(postFilter)
                .setFrom(0).setSize(10)
                .addSort("age", SortOrder.DESC)
                .get();
        SearchHits shs = searchResponse.getHits();
        for (SearchHit hit : shs) {
            System.out.println(hit.getSourceAsString());
        }
        EsUtils.closeClient();
    }

    /**
     * 同时指定 index type id 进行查询
     */

    @Test
    public void query() {
        GetResponse getResponse = client.prepareGet()
                .setIndex(com.mx.utils.EsUtils.getIndexName())
                .setType(com.mx.utils.EsUtils.getTypeName())
                .setId("AXN0FPq4-41f2ecMs0wd")
                .get();//AsString
        System.out.println("Version=" + getResponse.getVersion());
        com.mx.utils.EsUtils.closeClient();
    }

    //搜索
    @Test
    public void search() {
        QueryBuilder query = QueryBuilders.queryStringQuery("张");
        SearchResponse searchResponse = client.prepareSearch("user_index")
                .setQuery(query)
                .setFrom(0).setSize(10)
                .execute()
                .actionGet();
        //SearchHits是SearchHit的复数形式，表示这个是一个列表
        SearchHits shs = searchResponse.getHits();
        for (SearchHit hit : shs) {
            System.out.println(hit.getSourceAsString());
        }
        com.mx.utils.EsUtils.closeClient();
    }

    //搜索2
    // gt，选填项。大于
    //gte，选填项。大于等于
    //lt，选填项。小于
    //lte，选填项。小于等于
    @Test
    public void search2() {
//        client = EsUtils.getEsClient();
        QueryBuilder query = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("name", "张"))  //英文不行
                .must(QueryBuilders.rangeQuery("age").lte(53));
        SearchResponse searchResponse = client.prepareSearch("user_index")
                .setQuery(query)
                .setFrom(0).setSize(10)
                .execute()
                .actionGet();
        //SearchHits是SearchHit的复数形式，表示这个是一个列表
        SearchHits shs = searchResponse.getHits();
        for (SearchHit hit : shs) {
            System.out.println(hit.getSourceAsString());
        }
        EsUtils.closeClient();
    }

}
