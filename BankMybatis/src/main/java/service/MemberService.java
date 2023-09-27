package service;

import bean.Member;

public interface MemberService {
	void join(Member member) throws Exception;

	Member login(String id, String password) throws Exception;
}
