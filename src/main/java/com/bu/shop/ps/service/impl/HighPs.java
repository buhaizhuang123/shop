package com.bu.shop.ps.service.impl;

import com.bu.shop.ps.service.ProxyService;
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
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @author haizhuangbu
 * @date 2022/3/14 4:25 下午
 * @mark HighPs 高级es客户端
 */
public class HighPs implements InvocationHandler {

    private RestHighLevelClient client;

    private  Object object;

    public HighPs(Object target) {
        this.object = target;
    }


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

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("first");
        Object invoke = method.invoke(object, args);
        System.out.println("after");
        return invoke;
    }

    public static void main(String[] args) {
        ProxyService proxyService = new ProxyServiceImpl();
        HighPs highPs = new HighPs(proxyService);
        ProxyService o =(ProxyService) Proxy.newProxyInstance(highPs.getClass().getClassLoader(),proxyService.getClass().getInterfaces(), highPs);
        System.out.println("o.getStr(\"123\") = " + o.getStr("123"));
    }

}
