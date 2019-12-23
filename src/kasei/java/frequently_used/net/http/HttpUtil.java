package kasei.javase.se.frequently.net.http;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class HttpUtil {

    public String sendGet(String urlStr) throws IOException {

        URL url = new URL(urlStr);
        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");
        httpURLConnection.setRequestProperty("Accept-Charset", "utf-8;q=1.000, iso-8859-1;q=0.5, *;q=0.1"); // q 表示优先级

        int responseCode = httpURLConnection.getResponseCode(); // 该函数内部发送请求，返回状态码
        InputStream inputStream = httpURLConnection.getInputStream(); // 创建输入流读取 http 返回内容
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));

        StringBuffer stringBuffer = new StringBuffer();
        String inputLine = null;
        while((inputLine = in.readLine()) != null){
            stringBuffer.append(inputLine);
        }
        in.close();

        return stringBuffer.toString();
    }


    public String sendPost(String urlStr) throws IOException {

        URL url = new URL(urlStr);
        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true); // 表示内容中有数据携带
        httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");
        httpURLConnection.setRequestProperty("Accept-Charset", "utf-8;q=1.000, iso-8859-1;q=0.5, *;q=0.1"); // q 表示优先级
        /** Content-Type 对照表
         * multipart/form-data;boundary=something       // 表单提交的数据
         * text/plain;charset=utf-8                     .txt
         * text/html;charset=utf-8                      .html
         * text/xml;charset=utf-8                       .xml
         * text/css;charset=utf-8                       .css
         * application/json                             .json
         * application/octet-stream                     .*(二进制流，不知道下载文件类型)
         * application/x-javascript                     .js
         * */
        httpURLConnection.setRequestProperty("Content-Type", "application/json");

        DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
       // wr.writeBytes(parameter);
        wr.flush();
        wr.close();

        // 获取返回数据
        int responseCode = httpURLConnection.getResponseCode(); // 该函数内部发送请求，返回状态码
        InputStream inputStream = httpURLConnection.getInputStream(); // 创建输入流读取 http 返回内容
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));

        StringBuffer stringBuffer = new StringBuffer();
        String inputLine = null;
        while((inputLine = in.readLine()) != null){
            stringBuffer.append(inputLine);
        }
        in.close();

        return stringBuffer.toString();
    }
}
