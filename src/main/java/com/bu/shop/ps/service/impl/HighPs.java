package com.bu.shop.ps.service.impl;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * @author haizhuangbu
 * @date 2022/3/14 4:25 下午
 * @mark HighPs 高级es客户端
 */
public class HighPs {

    private RestHighLevelClient client;

    @Before
    public void before(){
        client = new RestHighLevelClient(RestClient.builder(new HttpHost("101.34.24.189", 9200, "http")));
    }

    @After
    public void after() throws IOException {
        client.close();
    }

    @Test
    public void tsFind() throws IOException {
        GetRequest request = new GetRequest("person", "doc", "1");
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        Map<String, Object> sourceAsMap = response.getSourceAsMap();

        sourceAsMap.forEach((k,v)-> System.out.println(k+":"+v));
    }

}
