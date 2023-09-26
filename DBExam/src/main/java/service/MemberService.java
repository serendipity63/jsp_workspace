package service;

import dao.MemberDAO;
import dto.Member;

public class MemberService {
	private MemberDAO dao = new MemberDAO();

	public void join(Member member) {
		dao.insertMember(member);

	}
}
