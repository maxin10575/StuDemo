package com.mx.operations;


import com.mx.utils.EsUtils;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequestBuilder;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.ClusterState;
import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Test;

import java.io.IOException;

public class ESMapping {

    private String indexName = "test_mapping_index";
    private String typeName = "test_mapping_type";

    private TransportClient client = EsUtils.getEsClient();

    //获得mapping
    @Test
    public void getMapping() throws IOException {
        indexName = "testmapping";
        ClusterState cs = client.admin().cluster().prepareState().setIndices(indexName).execute().actionGet().getState();
        IndexMetaData imd = cs.getMetaData().index(indexName);
        MappingMetaData mdd = imd.mapping(typeName);
        System.out.println(mdd.sourceAsMap());

        EsUtils.closeClient();
    }

    /**
     * 创建索引并添加映射
     *方式二
     */
    @Test
    public void CreateIndexAndMapping() throws Exception {
        indexName = "testm22";
        CreateIndexRequestBuilder cib = client.admin().indices().prepareCreate(indexName);
        XContentBuilder mapping = XContentFactory.jsonBuilder()
                .startObject()
                .startObject("properties") //设置之定义字段
                .startObject("author")
                .field("type", "string") //设置数据类型
                .endObject()
                .startObject("title")
                .field("type", "string")
                .endObject()
                .startObject("content")
                .field("type", "string")
                .endObject()
                .startObject("price")
                .field("type", "string")
                .endObject()
                .startObject("view")
                .field("type", "string")
                .endObject()
                .startObject("tag")
                .field("type", "string")
                .endObject()
                .startObject("date")
                .field("type", "date")  //设置Date类型
                .field("format", "yyyy-MM-dd HH:mm:ss") //设置Date的格式
                .endObject()
                .endObject()
                .endObject();
        cib.addMapping(typeName, mapping);
        CreateIndexResponse res = cib.execute().actionGet();
        System.out.println("----------添加映射成功----------");
    }



    /**
     * @Author maxin
     * @Date 2019-12-23 21:18
     * @Param []
     * @return void
     * @Description  方式一
     * @Version 1.0
     *
     */
    @Test
    public void createMapping() throws Exception {
        Client client = EsUtils.getEsClient();
        indexName = "testmapping";
        //首先创建index
        CreateIndexResponse createIndexResponse = client.admin().indices()
                .prepareCreate(indexName).execute().actionGet();
        System.out.println("createIndexResponse=" + createIndexResponse.isAcknowledged());

        PutMappingRequestBuilder mappingRequest = client.admin().indices().preparePutMapping(indexName)
                .setType(typeName)
                .setSource(createTestModelMapping());
        PutMappingResponse putMappingResponse = mappingRequest.execute().actionGet();
        System.out.println("putMappingResponse=" + putMappingResponse.isAcknowledged());
        EsUtils.closeClient();
    }


    private XContentBuilder createTestModelMapping() throws Exception {
        XContentBuilder mapping = XContentFactory.jsonBuilder()
                .startObject()
                .startObject(typeName)
                .startObject("properties")
                .startObject("id")
                .field("type", "long")
                .field("store", "yes")
                .endObject()
                .startObject("type")
                .field("type", "string")
                .field("index", "not_analyzed")
                .endObject()
                .startObject("catIds")
                .field("type", "integer")
                .endObject()
                .startObject("catName")
                .field("type", "string")
                .endObject()
                .endObject()
                .endObject()
                .endObject();
        return mapping;
    }



}
