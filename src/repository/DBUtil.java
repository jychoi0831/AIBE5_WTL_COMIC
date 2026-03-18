package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    
	// DB 접속 정보
    private static final String URL = "jdbc:mysql://localhost:3306/comic_rental";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    
    // DB 연결 메서드
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("DB 연결 실패");
            e.printStackTrace();
            return null;
        }
    }
    
}
