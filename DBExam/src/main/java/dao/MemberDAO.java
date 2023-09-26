package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import dto.Member;

public class MemberDAO {

	public static int insertMember(Member member) {
		Connection conn = JdbcUtil.getConnection();
		int cnt = 0;
		PreparedStatement pstmt = null;
		String sql = "insert into member values(?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPassword());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getAddress());
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		JdbcUtil.close(conn);
		return cnt;
	}

}
