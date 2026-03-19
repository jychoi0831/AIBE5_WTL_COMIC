package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	// DB 접속 정보
	private static final String URL = "jdbc:mysql://localhost:3306/comic_rental?serverTimezone=Asia/Seoul";
    private static final String USER = "root";        // 본인 MySQL 계정으로 수정
    private static final String PASSWORD = "1234";    // 본인 MySQL 비밀번호로 수정

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC 드라이버를 찾을 수 없습니다.", e);
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
