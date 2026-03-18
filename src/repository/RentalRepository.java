package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RentalRepository {
	
    // 대여 등록
    public boolean rent(int memberId, int comicId) {
        
        String checkSql = "SELECT * FROM rentals WHERE comic_id = ? AND return_date IS NULL";
        String insertSql = "INSERT INTO rentals (comic_id, member_id, rent_date) VALUES (?, ?, CURDATE())";
        
        try (
        		Connection conn = DBUtil.getConnection();
        		PreparedStatement checkStmt = conn.prepareStatement(checkSql);
        ) {
        	
        	// 대여 여부 확인
        	checkStmt.setInt(1, comicId);
        	ResultSet rs = checkStmt.executeQuery();
        	
        	if (rs.next()) {
        		
                System.out.println("이미 대여중인 만화책입니다.");
                return false;
            }
        	
        	// 대여 가능 시 INSERT 실행
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {

                insertStmt.setInt(1, comicId);
                insertStmt.setInt(2, memberId);

                insertStmt.executeUpdate();

                System.out.println("대여 완료");
                
                return true;

            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean returnComic(int comicId) {
    	
        String checkSql = "SELECT * FROM rentals WHERE comic_id = ? AND return_date IS NULL";
        String updateSql = "UPDATE rentals SET return_date = CURDATE() WHERE comic_id = ? AND return_date IS NULL";
        
        try (
        		Connection conn = DBUtil.getConnection();
        		PreparedStatement checkStmt = conn.prepareStatement(checkSql);
        ) {
        	
        	// 대여 여부 확인
            checkStmt.setInt(1, comicId);
            ResultSet rs = checkStmt.executeQuery();
            
            if (!rs.next()) {
                System.out.println("대여 중인 만화책이 아닙니다.");
                return false;
            }
            
            // 대여 가능 시 UPDATE 실행
            try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
            	
                updateStmt.setInt(1, comicId);
                
                updateStmt.executeUpdate();
                
                return true;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
	
}
