package com.bu.shop.ps.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bu.shop.ps.Dto.PersonInfo;
import com.bu.shop.ps.service.PsService;
import lombok.Data;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * @author haizhuangbu
 * @date 2022/3/14 2:16 下午
 * @mark PsServiceImpl
 */
public class PsServiceImpl implements PsService {

   private Logger logger =  LoggerFactory.getLogger(PsServiceImpl.class);

   private  TransportClient client;

   @Before
   public void before() throws UnknownHostException {
       client  = new PreBuiltTransportClient(Settings.EMPTY)
               .addTransportAddress(new TransportAddress(InetAddress.getByName("101.34.24.189"), 9300));
   }
   @After
   public void after(){
       client.close();
   }


    @Override
    public PersonInfo findPsInfo(String id) {
       GetRequest request = new GetRequest("person");
            request.id(id);
            ActionFuture<GetResponse> response = client.get(request);
            Map<String, Object> source = response.actionGet().getSource();
            source.forEach((k,v)-> System.out.println(k+ ":" +v));
            return null;
    }

    @Override
    public void addPsInfo(PersonInfo personInfo) {

        String json = JSONObject.toJSONString(personInfo);

        IndexResponse response = client.prepareIndex("person", "doc")
                .setSource(json, XContentType.JSON).get();

        System.out.println("response.status() = " + response.status());

        System.out.println("response.getIndex() = " + response.getIndex());

        System.out.println("response.getType() = " + response.getType());

        System.out.println("response.getId() = " + response.getId());
    }

    @Override
    public void deleteById(String id) throws ExecutionException, InterruptedException {
        DeleteRequest deleteRequest = new DeleteRequest("person", "doc", id);
        DeleteResponse deleteResponse = client.delete(deleteRequest).get();
        System.out.println("deleteRequest.toString() = " + deleteResponse.toString());
    }

    @Override
    public List<PersonInfo> findAllInfo() {
        SearchResponse response = client.prepareSearch("person")
                .addDocValueField("布海壮")
                .setSize(10)
                .setFrom(1)
                .get();

        SearchHit[] hits = response.getHits().getHits();
        Arrays.stream(hits).forEach(System.out::println);
        for (SearchHit hit : hits) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();

            sourceAsMap.forEach((k,v)-> System.out.println(k+":"+v));
        }

        return null;
    }

    @Test
    public void ts1(){
//        findPsInfo();
        PersonInfo personInfo = new PersonInfo();

        personInfo.setId(UUID.randomUUID().toString())
        .setName("张三1")
        .setSex("男")
        .setContext("china 牛逼");

        addPsInfo(personInfo);
    }

    @Test
    public void tsFind(){
       findPsInfo("ACcuh38B_KqhMk97q6sg");
    }

    @Test
    public void tsDel() throws ExecutionException, InterruptedException {
       deleteById("search");
    }

    @Test
    public void tsFindAll(){
       findAllInfo();
    }
}
