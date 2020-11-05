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
import org.elasticsearch.common.xcontent.XContentType;
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
public class ESCud {
    TransportClient client = EsUtils.getEsClient();

    @Test
    public void insert() throws IOException {
        //1.使用map存储
        Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", "mx");
            map.put("age", 3);
            map.put("userid", 3);
        //2。使用XContentBuilder存储
        XContentBuilder contentBuilder = XContentFactory.jsonBuilder()
                .startObject()
                .field("name", "张三2")
                .field("age", 12)
                .field("userid", 8)
                .field("bz","bz1")
                .endObject();
        //1。第一种写法
//        IndexResponse indexResponse = client.prepareIndex("user_index", "user","6")
//                .setSource(contentBuilder)
//                .get();
        //2.第二种写法
        IndexResponse response = client.prepareIndex()
                .setIndex("user_index")
                .setType("user")
//                 .setId("1")   //设置id，id存在，便是update id那条值  //不设置，则自动设置id
//                .setSource("{\"author\":\"mx1\",\"title\":\"哈哈\",\"myId\":1}")
//                .setSource(map)
                .setSource(contentBuilder)
                .get();

        // CREATED-CREATED   ok-updated
        System.out.println("index="+response.getIndex()+"；type="+response.getType()+"；id="+response.getId()+"；version="+response.getVersion());
        System.out.println(response.status());
        System.out.println("插入成功, isCreated=" + response.getResult().toString());
        EsUtils.closeClient();
    }



    //删除
    @Test
    public void delete() {
//        client = EsUtils.getEsClient();
//        DeleteResponse deleteResponse = client.prepareDelete("index3", "user3", "10").get();
        DeleteResponse deleteResponse = client.prepareDelete()
                .setIndex("user_index")
                .setType("user")
                .setId("AXN0gZku-41f2ecMs0wj")
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

    //更新 一次version增加1
    @Test
    public void update() {
        Map<String, Object> updateMap = new HashMap<String, Object>();
        updateMap.put("myid", 22);
        updateMap.put("author", "哈哈哈2");
        updateMap.put("title", "更新了2");
        UpdateResponse updateResponse = client.prepareUpdate()
                .setIndex(EsUtils.getIndexName())
                .setType(EsUtils.getTypeName())
                .setDoc(updateMap)
                .setId("AXN0E26K-41f2ecMs0wc")
                .execute()
                .actionGet();
        System.out.println("更新成功， isUpdated=" + updateResponse.getResult());
        EsUtils.closeClient();
    }
}
