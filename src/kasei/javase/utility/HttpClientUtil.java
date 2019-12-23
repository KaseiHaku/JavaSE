package com.rongyue.efix.utility;


import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 本工具类主要应用于需要采用 java 代码来发送 http 请求的情况
 * */
public class HttpClientUtil {

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


    /**
     * todo 模拟表单 post 提交数据
     * @param url 接收请求的 url 地址
     * @param params 发送的请求参数
     * @return 返回的 json 字符串
     * */
    public static String formPost(String url, List<NameValuePair> params){
        // 创建一个 http 客户端实例
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建一个 post 请求实例
        HttpPost httpPost = new HttpPost(url);
        // 设置 post 请求头
        Header[] headers = new Header[]{
                new BasicHeader("accept", "application/json;q=1.0"), // 设置客户端接收的返回数据类型
                new BasicHeader("accept-type", "utf-8;q=1.0"), // 表示客户端能接受的字符集类型
                new BasicHeader("content-type", "application/x-www-form-urlencoded;charset=utf-8") // 表示请求内容的 MIME 类型
        };
        httpPost.setHeaders(headers);

        // 设置 post 传递的参数
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8); // 设置请求参数的编码格式
        httpPost.setEntity(entity);

        // 正式开始发送请求
        CloseableHttpResponse response = null; // 定义一个返回实例，用于接受返回数据
        InputStream content = null;
        try {
            response = httpclient.execute(httpPost); // 发送 post 请求

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
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
