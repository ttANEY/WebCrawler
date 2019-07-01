/*
 * @(#) HttpClientTest
 * 版权声明 没有公司的公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2019
 * <br> Company:没有公司的公司
 * <br> @author Taney
 * <br> 2019-06-29 09:31:07
 */

package test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * HttpClient 使用示例
 */
public class HttpClientTest {
    public static void main(String[] args) throws URISyntaxException, IOException {
        //请求路径
        String url = "";

        //构造路径参数
        List<NameValuePair> nameValuePairList = new ArrayList<>();
        nameValuePairList.add(new BasicNameValuePair("username", "test"));
        nameValuePairList.add(new BasicNameValuePair("password", "password"));

        //构造请求路径，并添加参数
        URI uri = new URIBuilder(url).addParameters(nameValuePairList).build();

        //构造Headers
        List<Header> headerList = new ArrayList<>();
        headerList.add(new BasicHeader(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate"));
        headerList.add(new BasicHeader(HttpHeaders.CONNECTION, "keep-alive"));

        //构造HttpClient
        HttpClient httpClient = HttpClients.custom().setDefaultHeaders(headerList).build();

        //构造HttpGet请求
        HttpUriRequest httpUriRequest = RequestBuilder.get().setUri(uri).build();

        //获取结果
        HttpResponse httpResponse = httpClient.execute(httpUriRequest);

        //获取返回结果中的实体
        HttpEntity entity = httpResponse.getEntity();

        //查看页面内容结果
        String pagValue = EntityUtils.toString(entity);

        System.out.println(pagValue);

        //关闭HttpEntity流
        EntityUtils.consume(entity);
    }
}
