package kasei;

import com.google.gson.*;
import com.google.gson.internal.GsonBuildConfig;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.*;

public class InfoUtil {


    public static Gson getGson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        gsonBuilder.serializeNulls();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson;
    }

    /** todo 获取环境变量
     * Gson: 不会错
     * FastJson: 复杂 Bean 转 JSON 会出错
     * Jackson: 复杂 Bean 转 JSON 会出错
     * @strategy 方案： Bean 转 JSON 用 Gson ，JSON 转 Bean 用 FastJson 或 Jackson
     * */
    public static void getEnvironmentVariable() {

        String jsonStr = getGson().toJson(System.getenv());
        System.out.println(jsonStr);

        //gson.fromJson("", new TypeToken<List<String>>() {}.getType());
    }

    /** todo Java 中获取系统属性
     * */
    public static void getSystemProperties(){
        String jsonStr = getGson().toJson(System.getProperties());
        System.out.println(jsonStr);
    }

    /** todo Java 中获取各种路径
     * */
    public static void getPath() throws Exception {
        /** Java 中获取路径总结 */
        /* Class 中获取路径 */
        System.out.println(new File("").getCanonicalPath()); // 获取当前目录
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getPath()); //
        System.out.println(InfoUtil.class.getClassLoader().getResource("").getPath()); // 获取类的加载路径
        System.out.println(InfoUtil.class.getClassLoader().getResource("/").getPath()); // 获取类加载的根路径

        /* Servlet 中的路径 */
        // HttpServletRequest request = null;
        // request.getRequestURI(); // 得到浏览器 URL 地址栏中的路径，也就是资源映射路径，不是服务器上的路径
        // request.getContextPath(); // 得到工程名，例如： /ProjectName
        // request.getServletPath(); // 得到从工程名开始到当前页面的路径， 例如： /ProjectName/WEB-INF/view/test.html
        // request.getSession().getServletContext().getRealPath(""); // 得到工程目录： /srv/web/ProjectName
    }






}
