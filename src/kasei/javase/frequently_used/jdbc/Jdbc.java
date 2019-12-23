package main.java.se.jdbc;

import java.sql.*;

public class Jdbc {

	public void demo() {
		Connection con = null;   			// 数据库连接实例
		Statement st = null;				// 静态 sql 的声明实例
		PreparedStatement pst = null;		// 动态 sql （即带参数的 sql）的声明实例
		CallableStatement cst = null;		// 数据库存储过程的声明实例
		ResultSet rs = null;				// 数据库操作返回的结果集
		
		try {
			/** 加载数据库驱动类 */
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			/** 设置连接参数 */
			String username = "kasei"; // 设置用户名
			String password = "kasei"; // 设置密码
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE"; // 设置连接 url
			
			/** 创建数据库连接实例 */
			con = DriverManager.getConnection(url, username, password);
			con.setAutoCommit(false); // 关闭事务自动提交
			
			/** 编写 sql 语句 */
			String sql = "insert into users(kaseiID, password)values("+username+","+"'"+password+"')";
			/*注意:
			 * 0、!!!!! 使用‘+’进行字符串连接对自动调用对象的toString()方法  !!!!!
			 * 1、sql语句中的字符串必须用''包含，否则报错；
			 * 2、此处sql 语句不需要用';'结尾；
			 * */
			
			/** 创建一个 Statement 的实例，方法一： 一般用于执行静态 SQL 语句  */
			st = con.createStatement();
			
			/** 创建一个 Statement 的实例，方法二： 一般用于执行动态 SQL 语句 */
			String sql1 = "select * from table where id = ? and aa=?";
			pst = con.prepareStatement(sql1);
			pst.setInt(1, 9);// 设置第一个 ？ 的值
		    pst.setString(2, "Haku");// 设置第二个 ？ 的值
			
			/** 创建一个 Statement 的实例，方法三： 一般用于执行数据库存储过程 */
			cst = con.prepareCall(sql);
			
			/** 执行 sql 语句
			 * ResultSet rs = st.executeQuery(sql);执行查询数据库的SQL语句，返回一个结果集（ResultSet）对象。
			 * int result	= st.executeUpdate(sql);用于执行INSERT、UPDATE或DELETE语句以及SQL DDL语句，如：CREATE TABLE和DROP TABLE等   
			 * boolean flag = st.execute(sql);用于执行返回多个结果集、多个更新计数或二者组合的语句。   
			 *  */
			rs = st.executeQuery(sql);// ResultSet 的实例 rs 用于存放DB的处理结果集，这是一个游标，指向结果集中的一行，用 rs.next() 进行游标移动	
			int result	= st.executeUpdate(sql);//result > 0成功   
			boolean flag = st.execute(sql);
			
			/**7、使用结果集 and 判断是否有数据返回*/
			while(rs.next()){   
				String name = rs.getString("name"); //根据列名取数据
				String pass = rs.getString(1) ; // 根据列的标号取数据（列是从左到右编号的，并且从列1开始） 
			}
					
			/** 提交事务 */
			con.commit();			
		} catch (ClassNotFoundException e) {	
			// 没有驱动类异常
			e.printStackTrace();			
		} catch (SQLException e) {
			// sql 执行异常
			try {
				/** 当 sql 语句出现异常时，回滚事务 */
				con.rollback();
			} catch (SQLException e1) {				
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				/** 重新设置事务为自动提交 */	
				con.setAutoCommit(true);
				/** 8、按创建顺序的倒序关闭实例*/			 
				rs.close() ;  // 关闭记录集  
				st.close() ;  // 关闭声明    
				con.close() ; // 关闭连接对象     					
			} catch (SQLException e) {
				e.printStackTrace();
			}			  
		}
	}
	
}
