package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Comic;

public class ComicRepository {

    public static final String DELETE_OK = "DELETE_OK";
    public static final String DELETE_NOT_FOUND = "DELETE_NOT_FOUND";
    public static final String DELETE_RENTAL_HISTORY_EXISTS = "DELETE_RENTAL_HISTORY_EXISTS";
    public static final String DELETE_FAIL = "DELETE_FAIL";

    // 만화책 등록
    public long save(String title, String author, long volumeNumber, long rentPrice) {
        String sql = """
                INSERT INTO comics (title, author, volume_number, rent_price, created_at)
                VALUES (?, ?, ?, ?, CURDATE())
                """;

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setLong(3, volumeNumber);
            pstmt.setLong(4, rentPrice);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                return -1;
            }

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }

            return -1;

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    // 만화책 전체 조회
    public List<Comic> findAll() {
        List<Comic> comics = new ArrayList<>();

        String sql = """
                SELECT comic_id, title, author, volume_number, rent_price, created_at
                FROM comics
                ORDER BY comic_id ASC
                """;

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()
        ) {
            while (rs.next()) {
            	Date createdAt = rs.getDate("created_at");
            	
                Comic comic = new Comic(
                		rs.getLong("comic_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getLong("volume_number"),
                        rs.getLong("rent_price"),
                        createdAt != null ? createdAt.toLocalDate() : null
                );

                comics.add(comic);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comics;
    }

    // 만화책 상세 조회
    public Comic findById(long comicId) {
        String sql = """
                SELECT comic_id, title, author, volume_number, rent_price, created_at
                FROM comics
                WHERE comic_id = ?
                """;

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setLong(1, comicId);

            try (ResultSet rs = pstmt.executeQuery("")) {
                if (rs.next()) {
                	Date createdAt = rs.getDate("created_at");
                	
                    return new Comic(
                            rs.getLong("comic_id"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getLong("volume_number"),
                            rs.getLong("rent_price"),
                            createdAt != null ? createdAt.toLocalDate() : null
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // 만화책 수정
    public boolean update(long comicId, String title, String author, long volumeNumber, long rentPrice) {
        String sql = """
                UPDATE comics
                SET title = ?, author = ?, volume_number = ?, rent_price = ?
                WHERE comic_id = ?
                """;

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setLong(3, volumeNumber);
            pstmt.setLong(4, rentPrice);
            pstmt.setLong(5, comicId);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 만화책 삭제
    public String delete(long comicId) {
        // 존재 여부 확인
        if (findById(comicId) == null) {
            return DELETE_NOT_FOUND;
        }

        String sql = "DELETE FROM comics WHERE comic_id = ?";

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setLong(1, comicId);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0 ? DELETE_OK : DELETE_FAIL;

        } catch (SQLException e) {
        	
            // rentals 테이블에서 FK로 참조 중이면 삭제 실패

            if ("23000".equals(e.getSQLState())) {
                return DELETE_RENTAL_HISTORY_EXISTS;
            }

            e.printStackTrace();
            return DELETE_FAIL;
        }
    }
}
