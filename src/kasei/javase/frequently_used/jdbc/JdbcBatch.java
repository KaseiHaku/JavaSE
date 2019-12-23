package kasei;

import java.sql.*;

public class JdbcBatch {

    private static Connection conn = null;

    public static void batchOfStatement() throws Exception {


        // Statement 对象的批处理
        Statement stmt = conn.createStatement();
        conn.setAutoCommit(false);
        String SQL = "INSERT INTO Employees (id, first, last, age) VALUES(200,'Ruby', 'Yang', 30)";
        stmt.addBatch(SQL); // 加入到批处理

        String SQL2 = "INSERT INTO Employees (id, first, last, age) VALUES(201,'Java', 'Lee', 35)";
        stmt.addBatch(SQL2);

        String SQL3 = "UPDATE Employees SET age = 35 WHERE id = 100";
        stmt.addBatch(SQL3);

        int[] count = stmt.executeBatch(); // 执行批处理

        conn.commit();
    }

    public static void batchOfPrepareStatement() throws Exception {
        // Create SQL statement
        String SQL = "INSERT INTO Employees (id, first, last, age) VALUES(?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(SQL);
        conn.setAutoCommit(false);

        // Set the variables
        pstmt.setInt( 1, 400 );
        pstmt.setString( 2, "JDBC" );
        pstmt.setString( 3, "Li" );
        pstmt.setInt( 4, 33 );
        // Add it to the batch
        pstmt.addBatch();

        // Set the variables
        pstmt.setInt( 1, 401 );
        pstmt.setString( 2, "CSharp" );
        pstmt.setString( 3, "Liang" );
        pstmt.setInt( 4, 31 );
        // Add it to the batch
        pstmt.addBatch();


        //Create an int[] to hold returned values
        int[] count = pstmt.executeBatch();

        //Explicitly commit statements to apply changes
        conn.commit();
    }

}
