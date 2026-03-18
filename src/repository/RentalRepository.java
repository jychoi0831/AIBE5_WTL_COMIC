package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Rental;

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
    
    // 반납 등록
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
            
            // 반납 가능 시 UPDATE 실행
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
    
    // 전체 대여 목록 조회
    public List<Rental> findAll() {
        List<Rental> rentals = new ArrayList<>();

        String sql = """
                SELECT rent_id, comic_id, member_id, rent_date, return_date
                FROM rentals
                ORDER BY rent_id ASC
                """;

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()
        ) {
            while (rs.next()) {
                long rentId = rs.getLong("rent_id");
                long comicId = rs.getLong("comic_id");
                long memberId = rs.getLong("member_id");
                Date rentDateValue = rs.getDate("rent_date");
                Date returnDateValue = rs.getDate("return_date");

                Rental rental = new Rental(
                        rentId,
                        comicId,
                        memberId,
                        rentDateValue.toLocalDate(),
                        returnDateValue == null ? null : returnDateValue.toLocalDate()
                );

                rentals.add(rental);
            }
        } catch (SQLException e) {
            System.out.println("대여 목록 조회 중 오류가 발생했습니다.");
            e.printStackTrace();
        }

        return rentals;
    }
}
