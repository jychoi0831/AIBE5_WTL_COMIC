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
	
	public static final String RENT_OK = "RENT_OK";
    public static final String RENT_NO_MEMBER = "RENT_NO_MEMBER";
    public static final String RENT_NO_COMIC = "RENT_NO_COMIC";
    public static final String RENT_ALREADY_RENTED = "RENT_ALREADY_RENTED";
    public static final String RENT_FAIL = "RENT_FAIL";

    public static final String RETURN_OK = "RETURN_OK";
    public static final String RETURN_NO_HISTORY = "RETURN_NO_HISTORY";
    public static final String RETURN_ALREADY_RETURNED = "RETURN_ALREADY_RETURNED";
    public static final String RETURN_FAIL = "RETURN_FAIL";
    
    // 회원 존재 여부 확인
    public boolean existsMember(long memberId) {
        String sql = "SELECT 1 FROM members WHERE member_id = ?";

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setLong(1, memberId);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // 만화책 존재 여부 확인
    public boolean existsComic(long comicId) {
        String sql = "SELECT 1 FROM comics WHERE comic_id = ?";

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setLong(1, comicId);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // 현재 대여 중 여부 확인
    public boolean isRented(long comicId) {
        String sql = "SELECT 1 FROM rentals WHERE comic_id = ? AND return_date IS NULL";

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setLong(1, comicId);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // 대여 진행
    public String rent(long memberId, long comicId) {
        if (!existsMember(memberId)) {
            return RENT_NO_MEMBER;
        }

        if (!existsComic(comicId)) {
            return RENT_NO_COMIC;
        }

        if (isRented(comicId)) {
            return RENT_ALREADY_RENTED;
        }

        String sql = "INSERT INTO rentals (comic_id, member_id, rent_date) VALUES (?, ?, CURDATE())";

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setLong(1, comicId);
            pstmt.setLong(2, memberId);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0 ? RENT_OK : RENT_FAIL;

        } catch (SQLException e) {
            e.printStackTrace();
            return RENT_FAIL;
        }
    }
    
    // 반납 진행
    public String returnComic(long comicId) {
        String historySql = "SELECT 1 FROM rentals WHERE comic_id = ?";
        String rentedSql = "SELECT 1 FROM rentals WHERE comic_id = ? AND return_date IS NULL";
        String updateSql = "UPDATE rentals SET return_date = CURDATE() WHERE comic_id = ? AND return_date IS NULL";

        try (Connection conn = DBUtil.getConnection()) {

            // 대여 이력 확인
            try (PreparedStatement pstmt = conn.prepareStatement(historySql)) {
                pstmt.setLong(1, comicId);

                try (ResultSet rs = pstmt.executeQuery()) {
                    if (!rs.next()) {
                        return RETURN_NO_HISTORY;
                    }
                }
            }

            // 현재 대여 중 여부 확인
            try (PreparedStatement pstmt = conn.prepareStatement(rentedSql)) {
                pstmt.setLong(1, comicId);

                try (ResultSet rs = pstmt.executeQuery()) {
                    if (!rs.next()) {
                        return RETURN_ALREADY_RETURNED;
                    }
                }
            }

            // 반납 등록
            try (PreparedStatement pstmt = conn.prepareStatement(updateSql)) {
                pstmt.setLong(1, comicId);

                int affectedRows = pstmt.executeUpdate();
                return affectedRows > 0 ? RETURN_OK : RETURN_FAIL;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return RETURN_FAIL;
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
            e.printStackTrace();
        }

        return rentals;
    }
}
