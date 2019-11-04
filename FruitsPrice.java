/*
 * @(#) FruitsPrice
 * 版权声明 没有公司的公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2019
 * <br> Company:没有公司的公司
 * <br> @author Taney
 * <br> 2019-10-29 18:02:11
 */

package test;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FruitsPrice {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<Fruits> list = new LinkedList<>();
        //取页面
        String pagValue = getPagValue();

        //将当前页面转换为Jsoup的Document对象
        Document document = Jsoup.parse(pagValue);
        //取table元素
        Elements fruitsTable = document.select("table[class=list]");

        Elements thead = fruitsTable.get(0).select("tr");
        //头
        for(int i=0;i<thead.size();i++) {
            Fruits fruits = new Fruits();
            String variety;
            String market;
            String gatherTime;
            String saleType;
            String price;
            String unit;
            String volume;
            if(i==0) {
                //th
                variety = thead.get(i).select("th[class=variety]").text();//品种
                market = thead.get(i).select("th[class=market]").text();//市场
                gatherTime = thead.get(i).select("th[class=gatherTime]").text();//日期
                saleType = thead.get(i).select("th[class=saleType]").text();//
                price = thead.get(i).select("th[class=price]").text();//价格
                unit = thead.get(i).select("th[class=unit]").text();//单位
                volume = thead.get(i).select("th[class=volume]").text();//昨日量

                System.out.println("variety="+variety+",market="+market+",gatherTime="+gatherTime+",saleType="
                        +saleType+",price="+price+",unit="+unit+",volume="+volume);
                continue;
            }
            //根据td的class取各自的text
            variety = thead.get(i).select("td[class=variety]").text();//品种
            market = thead.get(i).select("td[class=market]").text();//市场
            gatherTime = thead.get(i).select("td[class=gatherTime]").text();//日期
            saleType = thead.get(i).select("td[class=saleType]").text();//
            price = thead.get(i).select("td[class=price]").text();//价格
            unit = thead.get(i).select("td[class=unit]").text();//单位
            volume = thead.get(i).select("td[class=volume]").text();//昨日量
            //存值如对象中
            fruits.setVariety(variety);
            fruits.setMarket(market);
            fruits.setGatherTime(gatherTime);
            fruits.setSaleType(saleType);
            fruits.setPrice(price);
            fruits.setUnit(unit);
            fruits.setVolume(volume);
            //加入水果list
            list.add(fruits);
            //输出查看
            System.out.println(fruits.toString());
        }

    }

    public static String getPagValue() throws URISyntaxException, IOException {
        //请求路径
        String url = "http://nyncw.cq.gov.cn/marketSta/";

        //构造路径参数
        List<NameValuePair> nameValuePairList = new ArrayList<>();
        nameValuePairList.add(new BasicNameValuePair("vexp", "3"));
        nameValuePairList.add(new BasicNameValuePair("classId", "3"));

        //构造请求路径，并添加参数
        URI uri = new URIBuilder(url).addParameters(nameValuePairList).build();

        //构造Headers
        List<Header> headerList = new ArrayList<>();
        headerList.add(new BasicHeader(HttpHeaders.ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"));
        headerList.add(new BasicHeader(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate"));
        headerList.add(new BasicHeader(HttpHeaders.ACCEPT_LANGUAGE, "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2"));
        headerList.add(new BasicHeader(HttpHeaders.CONNECTION, "keep-alive"));
        headerList.add(new BasicHeader(HttpHeaders.HOST, "nyncw.cq.gov.cn"));
        headerList.add(new BasicHeader(HttpHeaders.REFERER, "http://nyncw.cq.gov.cn/marketSta/?vexp=3&classId=3"));
        headerList.add(new BasicHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:70.0) Gecko/20100101 Firefox/70.0"));

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

        //关闭HttpEntity流
        EntityUtils.consume(entity);

        return pagValue;
    }

    /**
     * 水果的实体类
     */
    static class Fruits {
        String variety;
        String market;
        String gatherTime;
        String saleType;
        String price;
        String unit;
        String volume;
        Fruits(){}
        public void setVariety(String variety) {
            this.variety = variety;
        }

        public void setMarket(String market) {
            this.market = market;
        }

        public void setGatherTime(String gatherTime) {
            this.gatherTime = gatherTime;
        }

        public void setSaleType(String saleType) {
            this.saleType = saleType;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public void setVolume(String volume) {
            this.volume = volume;
        }

        public String getVariety() {
            return variety;
        }

        public String getMarket() {
            return market;
        }

        public String getGatherTime() {
            return gatherTime;
        }

        public String getSaleType() {
            return saleType;
        }

        public String getPrice() {
            return price;
        }

        public String getUnit() {
            return unit;
        }

        public String getVolume() {
            return volume;
        }

        @Override
        public String toString() {
            return "Fruits{" +
                    "variety='" + variety + '\'' +
                    ", market='" + market + '\'' +
                    ", gatherTime='" + gatherTime + '\'' +
                    ", saleType='" + saleType + '\'' +
                    ", price='" + price + '\'' +
                    ", unit='" + unit + '\'' +
                    ", volume='" + volume + '\'' +
                    '}';
        }
    }
}
