package kasei.javase.se.frequently.net.http;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.BufferedHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpClientUtil {

    public void get() {

        URI uri = null;
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        try {
            uri = new URIBuilder()
                    .setScheme("http")
                    .setHost("localhost")
                    .setPath("/efix/page/html/error.html")
                    .setParameter("name", "Kasei")
                    .setParameter("age", "22")
                    .setParameter("wife", "Haku")
                    .build();


            HttpGet httpGet = new HttpGet(uri);  // 创建 get 请求
            httpclient = HttpClients.createDefault(); // 创建 http client 实例
            response = httpclient.execute(httpGet); // 执行 get 请求，并接收返回结果

            // 读取返回的内容
            response.getStatusLine();
            HttpEntity entity = response.getEntity();
            InputStream inputStream = entity.getContent();// 读取返回的内容
            //entity.writeTo(os); // 将返回的内容一次性写入到一个输出流里
            Header header = entity.getContentType();
            entity.getContentEncoding();
            entity.getContentLength();

            EntityUtils.consume(entity); // 消耗掉这次返回的 HttpEntity，即关闭返回结果

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * todo 模拟表单 post 提交数据
     * @param url 接收请求的 url 地址
     * @param params 发送的请求参数
     * @return 返回的 json 字符串
     * */
    public String postForm(String url, Map<String, String> params){

        List<NameValuePair> formParams = new ArrayList<NameValuePair>();

        Set<String> keySet = params.keySet();
        for (String key:keySet) {
            formParams.add(new BasicNameValuePair(key,  params.get(key)));
        }


        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
        HttpPost httpPost = new HttpPost(url);
        Header[] headers = new Header[]{
                new BasicHeader("accept", "application/json;q=1.0"), // 设置客户端接收的返回数据类型
                new BasicHeader("accept-type", "utf-8;q=1.0"), // 表示客户端能接受的字符集类型
                new BasicHeader("content-type", "application/x-www-form-urlencoded;charset=utf-8"), // 表示请求内容的 MIME 类型
        };
        httpPost.setHeaders(headers);
        httpPost.setEntity(entity);

        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        InputStream content = null;
        try {
            httpclient = HttpClients.createDefault();
            response = httpclient.execute(httpPost);

            HttpEntity responseEntity = response.getEntity();
            content = responseEntity.getContent();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(content, "utf-8"));

            StringBuffer stringBuffer = new StringBuffer();
            int read = -1;
            while((read = bufferedReader.read())!= -1){
                stringBuffer.append((char)read);
            }
            EntityUtils.consume(responseEntity); // 采用工具类关闭返回实体流
            return stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * TODO 发送 http post 请求
     * @param url 发送的 url 地址
     * @param json 发送的请求参数
     * @return 返回的 json 字符串
     * */
    public static String postJson(String url, String json){
        StringEntity entity = new StringEntity(json, "utf-8"); // 注意这里是设置内容的字符编码格式
        HttpPost httpPost = new HttpPost(url);
        Header[] headers = new Header[]{
                new BasicHeader("accept", "application/json;q=1.0"), // 设置客户端接收的返回数据类型
                new BasicHeader("accept-type", "utf-8;q=1.0"), // 表示客户端能接受的字符集类型
                new BasicHeader("content-type", "application/json;charset=utf-8"), // 表示请求内容的 MIME 类型
        };
        httpPost.setHeaders(headers);
        httpPost.setEntity(entity);

        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        InputStream content = null;
        try {
            httpClient = HttpClients.createDefault();
            response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            content = responseEntity.getContent();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(content, "utf-8"));

            StringBuffer stringBuffer = new StringBuffer();
            int read = -1;
            while((read = bufferedReader.read())!= -1){
                stringBuffer.append((char)read);
            }

            // EntityUtils.consume(responseEntity); // 采用工具类关闭返回实体流
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                content.close(); // 自行关闭返回实体流，跟采用工具类关闭，二选一
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
