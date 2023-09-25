package service;

import java.sql.Connection;

import dao.MemberDAO;
import dto.Member;

public class MemberService {
	public void join(Member member) {
		Connection conn = MemberDAO.getConnection();
		MemberDAO.insertMember(conn, member);
		MemberDAO.close(conn);

	}
}
