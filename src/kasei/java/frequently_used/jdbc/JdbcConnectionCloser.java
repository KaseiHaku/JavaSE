public class JdbcConnectionCloser {

    /** todo 关闭 jdbc 连接，以下三个异常同时抛出，最后只会抛异常3
     * @throws SQLException 关闭异常
     * */
    public static void close(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
        try {
            if(rs != null){
                rs.close();         // 抛出异常 1
            }
        } finally{
            try{
                if(stmt != null){
                    stmt.close();   // 抛出异常 2
                }
            } finally{
                if(conn != null){
                    conn.close();   // 抛出异常 3
                }
            }
        } 
    }
    
    /** todo 关闭 jdbc 连接，不抛出异常
     * */
    public static void closeQuietly(Connection conn, Statement stmt, ResultSet rs){
        try{
            close(conn, stmt, rs);
        } catch(SQLException e){
            // quietly
        }
    }
    
    
    /** todo 关闭 jdbc 连接，最严格正确的方式
     * */
    public static void closeStrict(Connection conn, Statement stmt, ResultSet rs){ 
        try {
            if(rs != null){
                rs.close();
            }
        } catch(SQLException e){
            e.printStackTrace();
        } finally{
            try{
                if(stmt != null){
                    stmt.close();
                }
            } catch(Exception e){
                e.printStackTrace();
            } finally{
                if(conn != null){
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
