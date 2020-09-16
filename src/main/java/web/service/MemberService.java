package web.service;

import java.util.List;

import web.entity.Member;

public interface MemberService {
	public Member authenticate(String loginId, String password);

	public int register(Member member);

	public int deleteByLoginId(String loginId);

	public int update(Member member);

	public List<Member> findMembersReactAnArticle(Integer articleId);

	public Member findByLoginId(String loginId);

	public Member findByMemberId(Integer memberId);

	public Integer getRank(Integer memberId);
}
