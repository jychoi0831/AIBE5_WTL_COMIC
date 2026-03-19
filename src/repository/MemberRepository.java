package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Member;

// DB에 SQL 전달 <-> 조회 결과를 객체로 변환
// App.java 가 SQL 을 다루지 않도록 분리
public class MemberRepository {
	
	// 회원 등록 : INSERT
	public long save(String name, String phone) {
		String sql = """
				INSERT INTO members (name, phone, reg_date)
				VALUES (?, ?, CURDATE())
				""";
		
		try (
				Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)
		) {
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			
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
	
	// 전체 회원 조회 : SELECT
	public List<Member> findAll() {
		List<Member> members = new ArrayList<>();
		
		String sql = """
				SELECT member_id, name, phone, reg_date
				FROM members
				ORDER BY member_id ASC
				""";
		
		try (
				Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()
		) {
			while (rs.next()) {
				Date regDate = rs.getDate("reg_date");
				
				Member member = new Member(
						rs.getLong("member_id"),
						rs.getString("name"),
						rs.getString("phone"),
						regDate != null ? regDate.toLocalDate() : null
				);
				
				members.add(member);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return members;
	}
	
	// 회원 상세 조회 : SELECT
	public Member findById(long memberId) {
		String sql = """
				SELECT member_id, name, phone, reg_date
				FROM members
				WHERE member_id = ?
				""";
		
		try (
				Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)
		) {
			pstmt.setLong(1, memberId);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					Date regDate = rs.getDate("reg_date");
					
					return new Member(
							rs.getLong("member_id"),
							rs.getString("name"),
							rs.getString("phone"),
							regDate != null ? regDate.toLocalDate() : null
					);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
