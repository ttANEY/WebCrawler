import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;

/**
 * 第一个爬虫测试
 */
public class FirstTest {

    public static void main(String[] args) {

        //建立一个新的请求客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //使用HttpGet方式请求网站
        HttpGet httpGet = new HttpGet("https://www.google.com");

        //获取网址的返回结果
        CloseableHttpResponse response = null;

        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        //获取返回值结果中的实体
        HttpEntity entity = response.getEntity();

        //将返回的实体输出
        try{
            System.out.println(EntityUtils.toString(entity));
            //关闭HttpEntity流
            EntityUtils.consume(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
