package com.mx.operations;

import com.mx.utils.EsUtils;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.max.Max;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * @Author maxin
 * @Date 2019-12-20 17:32
 * @ClassName ESCrud
 * @Description get(timeout) = execute().actionGet(timeout)
 * 这是由于，Elasticsearch提供的java客户端是天生异步的。
 * 1、其中，execute() 方法中，创建了一个ActionListener，用来监听action的执行结果；然后在调用actionGet(timeout)，
 * 获取最终返回结果，actionGet是一个阻塞的方法，可以设置一个超时时间，也可以不设置。
 * 2、get(timeout)方法：
 * 通过源码可以发现，get(timeout)方法内部就是封装了execute().actionGet(timeout)方法，其中参数timeout也是超时时间，
 * 当然也可以不设置，一致阻塞知道有返回结果。
 * @Version 1.0
 **/


/**
 * @Author maxin
 * @Date 2019-12-28 12:51
 * @Param
 * @return indexResponse.status() : OK /   indexResponse.getResult().toString() :Created
 * @Description 增加
 * @Version 1.0
 */
public class ESCrud {
    TransportClient client = EsUtils.getEsClient();

    @Test
    public void insert() throws IOException {
        //1.使用map存储
//        Map<String, Object> map = new HashMap<String, Object>();
//            map.put("catid", "6");
//            map.put("author", "mx");
//            map.put("id", "6");
//            map.put("title", "菜鸟");
        //2。使用XContentBuilder存储
        XContentBuilder contentBuilder = XContentFactory.jsonBuilder()
                .startObject()
                .field("catid", "6")
                .field("author", "mx")
                .field("id", "6")
                .field("title", "菜鸟")
                .endObject();
        //1。第一种写法
//        IndexResponse indexResponse = client.prepareIndex("java_demo_index", "java_demo_type","6")
//                .setSource(contentBuilder)
//                .get();
        //2.第二种写法
        IndexResponse indexResponse = client.prepareIndex()
                .setIndex("java_demo_index")
                .setType("Object")
                //.setId("2")   //设置id，id重复，便是update id那条值  //不设置，则自动设置id
                //.setSource("{\"prodId\":1,\"prodName\":\"ipad5\",\"prodDesc\":\"比你想的更强大\",\"catId\":1}")
                .setSource(contentBuilder)
                .get();

        System.out.println(indexResponse.status());
        System.out.println("插入成功, isCreated=" + indexResponse.getResult().toString());
        EsUtils.closeClient();
    }


    /**
     * QueryBuilder 子类查询
     */
    @Test
    public void query2() {
        //match_all 查询所有
        MatchAllQueryBuilder builder = QueryBuilders.matchAllQuery();

        // title里包含"菜鸟"的
//        MatchQueryBuilder builder = QueryBuilders.matchQuery("title", "菜鸟");

        //  mutilMatch 查询  在"author","title"中查找含有ssve的文档
//        MultiMatchQueryBuilder builder = QueryBuilders.multiMatchQuery("ssve", "author","title");


        //TermsQueryBuilder   QueryBuilders.termsQuery("author", "ssve","mx","hah"); 完全匹配才行
        //QueryBuilder builder
//        TermsQueryBuilder builder = QueryBuilders.termsQuery("author", "ssve111", "mx");


        SearchRequestBuilder searchRequestBuilder = client.prepareSearch("java_demo_index")
                .setQuery(builder)
                .setSize(10); //返回多少条数据
        SearchResponse searchResponse = searchRequestBuilder.get();
        SearchHits hits = searchResponse.getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }




        EsUtils.closeClient();
    }

    /**
     * 使用query查询  范围  通配符 前缀 模糊查询
     */
    @Test
    public void query1() {
        //范围查询
//        QueryBuilder queryBuilder = QueryBuilders.rangeQuery("age").from("1").to("5");

        //通配符查询 * 零个或多个
        QueryBuilder queryBuilder = QueryBuilders.wildcardQuery("name", "Sandra*");


        //前缀查询
//        QueryBuilder queryBuilder = QueryBuilders.prefixQuery("author","周");

        //模糊查询 查询类型的
//        QueryBuilder queryBuilder = QueryBuilders.fuzzyQuery("id","aac");

        //id查询
//        QueryBuilder queryBuilder = QueryBuilders.idsQuery().addIds("1","2","4");
        SearchRequestBuilder index = client.prepareSearch("testm22").setQuery(queryBuilder).setSize(10);
        SearchHits hits = index.get().getHits();
        for (SearchHit hit : hits
        ) {
            System.out.println(hit.getSourceAsString());
        }
    }

    /**
     * 聚合查询
     */
    @Test
    public void aggregation() throws Exception {
        AggregationBuilder aggregationBuilder = AggregationBuilders.max("max").field("id");
        SearchResponse index3 = client.prepareSearch("index3").addAggregation(aggregationBuilder).get();
        Max max = index3.getAggregations().get("max");
        System.out.println(max.getValue());
    }


    /**
     * 同时指定 index type id 进行查询
     */

    @Test
    public void query() {
        GetResponse getResponse = client.prepareGet()
                .setIndex(EsUtils.getIndexName())
                .setType(EsUtils.getTypeName())
                .setId("AW8w4nmTkO64bZqeHJ91")
                .get();//AsString
        System.out.println("get=" + getResponse.getSource());
        EsUtils.closeClient();
    }

    //搜索
    @Test
    public void search() {
        QueryBuilder query = QueryBuilders.queryStringQuery("iphone6");
        SearchResponse searchResponse = client.prepareSearch(EsUtils.getIndexName())
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

    //搜索2
    @Test
    public void search2() {
//        client = EsUtils.getEsClient();
        QueryBuilder query = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("name", "Walter Parker"))
                .must(QueryBuilders.rangeQuery("age").lte(50));
        SearchResponse searchResponse = client.prepareSearch(EsUtils.getIndexName())
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


    //过滤
    @Test
    public void filter() {
//        client = EsUtils.getEsClient();

        QueryBuilder postFilter = QueryBuilders.rangeQuery("age").gte(50).lte(60);
        SearchResponse searchResponse = client.prepareSearch(EsUtils.getIndexName())
                .setPostFilter(postFilter)
                .setFrom(0).setSize(10)
                .addSort("age", SortOrder.DESC)
                .get();
        //SearchHits是SearchHit的复数形式，表示这个是一个列表
        SearchHits shs = searchResponse.getHits();
        for (SearchHit hit : shs) {
            System.out.println(hit.getSourceAsString());
        }
        EsUtils.closeClient();
    }

    //删除
    @Test
    public void delete() {
//        client = EsUtils.getEsClient();
//        DeleteResponse deleteResponse = client.prepareDelete("index3", "user3", "10").get();
        DeleteResponse deleteResponse = client.prepareDelete()
                .setIndex(EsUtils.getIndexName())
                .setType(EsUtils.getTypeName())
                .setId("1")
                .execute()
                .actionGet();
        System.out.println(deleteResponse.status());
//        System.out.println("del id found=" + delResponse.getResult());
        EsUtils.closeClient();
    }

    /**
     * upsert使用 如有存在对应文档就修改  不存在就新增  需要指定修改的文档 和新增的文档
     */
    @Test
    public void upsert() throws IOException, ExecutionException, InterruptedException {
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .field("name", "up")   //ES 中已经存在的数据
                .field("age", 1)  //ES 不存在 这个为插入数据
                .endObject();
        UpdateRequest request = new UpdateRequest();
        IndexRequest indexRequest = new IndexRequest();
        indexRequest.index("testm22")
                .type("bulk_user")
                .id("10")
                .source(builder);
        request.index("testm22")
                .type("bulk_user")
                .id("10")
                .doc(new XContentFactory().jsonBuilder()
                        .startObject()
                        .field("age", "222")   //需要更新的数据
                        .field("name", "hahah")
                        .endObject()
                ).upsert(indexRequest);
        UpdateResponse updateResponse = client.update(request).get();

        System.out.println(updateResponse.status());

    }


    /**
     * 修改文档
     */
    @Test
    public void update1() throws Exception {
        UpdateRequest request = new UpdateRequest();
        XContentBuilder contentBuilder = XContentFactory.jsonBuilder()
                .startObject()
                .field("name", "updatename")
                .field("age", 0)
                .endObject();
        request.index("testm22")
                .type("bulk_user")
                .id("0")  //_id
                .doc(contentBuilder);
        UpdateResponse updateResponse = client.update(request).get();

        System.out.println(updateResponse.status());
    }

    //更新
    @Test
    public void update() {
        Map<String, Object> updateMap = new HashMap<String, Object>();
        updateMap.put("prodId", 2);
        updateMap.put("prodName", "iphone6s");
        updateMap.put("prodDesc", "手机");
        //updateMap.put("catId", 2);
        UpdateResponse updateResponse = client.prepareUpdate()
                .setIndex(EsUtils.getIndexName())
                .setType(EsUtils.getTypeName())
                .setDoc(updateMap)
                .setId("1")
                .execute()
                .actionGet();
//        System.out.println("更新成功， isUpdated=" + updateResponse.getResult());  //???? 一直是false
        EsUtils.closeClient();
    }
}
