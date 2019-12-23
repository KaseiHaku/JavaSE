package kasei;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * todo Oracle 数据类型：
 * VARCHAR2 (size) 可变长度的字符串, 必须规定长度
 * NUMBER(p,s) 数字型p是位数总长度, s是小数的长度, 可存负数
 * DATE 日期类型
 * CLOB 超长文本字符串
 * BLOB 二进制文件
 * BFILE 外部二进制文件
 */
public class OracleJdbc {


    public static Connection getOracleConnection() throws SQLException, IOException {

        /** todo URL 基本格式 jdbc:oracle:<drivertype>:<username/password>@<database>
         * 常用格式： jdbc:oracle:thin:<username/password>@//<host>:<port>/<service>
         * Example: jdbc:oracle:thin:@//10.63.29.99:1521/BPM12CTS
         * @trap ojdbc8.jar 不能通过 gradle 或者 maven 引入到项目中（引入了也没用，因为 oracle 不允许通过这种形式下载 jar 包，需要授权才行），必须手工下载 jar 包放在项目的 lib 目录下才行
         * */

        /** todo 获取 HikariCp 连接池的配置文件
         * getResource("") 获取的是 out/production/classes
         * 当时当给 getResource("configuration") 传参数的时候，他就会去找 out/production/resources 目录下的文件的
         * */
        String propsFilePath = Haku.class.getClassLoader().getResource("configuration/hikari.properties").getPath();
        System.out.println(propsFilePath);
        HikariConfig hikariConfig = new HikariConfig(propsFilePath);

        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
        Connection connection = hikariDataSource.getConnection();


        return connection;
    }




}
