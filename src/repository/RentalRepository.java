package repository;

import model.Rental;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RentalRepository {

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