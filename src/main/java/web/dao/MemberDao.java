package web.dao;

import java.util.List;

import web.entity.Member;

public interface MemberDao {
	public Member authenticate(String loginId, String password);

	public int register(Member user);

	public int deleteByLoginId(String loginId);

	public int update(Member user);

	public List<Member> findMembersReactAnArticle(Integer articleId);

	public Member findByLoginId(String loginId);

	public Member findByMemberId(Integer memberId);

	public Integer getRank(Integer memberId);
}
