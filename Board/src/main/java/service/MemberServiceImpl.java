package service;

import dao.MemberDao;
import dao.MemberDaoImpl;
import dto.Member;

public class MemberServiceImpl implements MemberService {
	private MemberDao memberDao;

	public MemberServiceImpl() {
		memberDao = new MemberDaoImpl();
	}

	@Override
	public Member login(String id, String password) throws Exception {
		Member member = memberDao.selectMember(id);
		if (member == null)
			throw new Exception("아이디 틀림");
		if (member.getPassword().equals(password) == false)
			throw new Exception("비밀번호 오류");
		return member;
	}

	@Override
	public void join(Member member) throws Exception {
		Member smember = memberDao.selectMember(member.getId());
		if (smember != null)
			throw new Exception("아이디 중복오류");
		memberDao.insertMember(member);
	}

}
