/*
 * @(#) DataLearnerCrawler
 *  学习而来
 * <br> Copyright:  Copyright (c) 2019
 * <br> Company:没有公司的公司
 * <br> @author Taney
 * <br> 2019-06-29 10:28:34
 */

package test;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DataLearnerCrawler {

    public static void main(String[] args) {
        testCase01();
    }

    public static void testCase01() {
        String url = "http://www.datalearner.com/blog_list";
        String rawHtml = null;
        try {
            rawHtml = getPageValue(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //将当前页面转换为Jsoup的Document对象
        Document document = Jsoup.parse(rawHtml);

        //获取所有的博客列表集合
        Elements blogList = document.select("div[class=card]");

        //针对每个博客内容进行解析，并输出
        for (Element elements : blogList) {
            String title = elements.select("a[class=text-break]").text();
            String introduction = elements.select("p[class=card-text text-justify]").text();
            String author = elements.select("span[class=fa fa-user]").text();
            String readNum = elements.select("span[class=fa fa-eye second]").text();
            String date = elements.select("span[class=fa fa-clock-o second]").text();

            //输出信息
            System.out.println("标题:"+title);
            System.out.println("introduction:\n"+introduction);
            System.out.println("作者:"+author);
            System.out.println("阅读量:"+readNum);
            System.out.println("发表时间:"+date);
            System.out.println("-----------------------");
        }
    }

    /**
     * 根据url返回html内容
     * @param url
     * @return
     * @throws IOException
     */
    private static String getPageValue(String url) throws IOException {
        //创建一个客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //使用HttpGet访问
        HttpGet httpGet = new HttpGet(url);

        //获取访问的返回结果
        CloseableHttpResponse response = response = httpClient.execute(httpGet);

        //获取返回值中的实体
        HttpEntity entity = response.getEntity();

        //将实体转为字符串对象
        String pageValue = EntityUtils.toString(entity);

        //关闭HttpEntity流
        EntityUtils.consume(entity);

        return pageValue;
    }

}
