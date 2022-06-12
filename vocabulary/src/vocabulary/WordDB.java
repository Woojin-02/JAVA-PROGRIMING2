package vocabulary;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class WordDB {
	public static Scanner in = new Scanner(System.in);
	 
    public static Connection getConnection() throws ClassNotFoundException, SQLException  {
        
        String url = "jdbc:mysql://localhost:3306/voca";
        String user = "root";
        String pwd = "1234";
        Connection conn = null;
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url, user, pwd);
        System.out.println("접속");
            
        return conn;
    }
    
    public static void insert(String eng, String kor) throws ClassNotFoundException, SQLException {
        //입력하는 메소드
        Connection conn = getConnection();
        String sql = "insert into word(eng, kor) values(?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        pstmt.setString(1, eng);
        pstmt.setString(2, kor);
        
        int res = pstmt.executeUpdate();
        if(res > 0){
            System.out.println("레코드 입력 완료");
        } else {
        	System.out.println("레코드 입력 실패");
        }
        
        if(pstmt != null) 
			pstmt.close();
        if(conn != null) 
			conn.close();
    }
}