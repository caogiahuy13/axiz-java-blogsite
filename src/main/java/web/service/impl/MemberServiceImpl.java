package web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.dao.MemberDao;
import web.entity.Member;
import web.service.MemberService;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDao memberDao;

	@Override
	public Member authenticate(String loginId, String password) {
		return memberDao.authenticate(loginId, password);
	}

	@Override
	public int register(Member member) {
		return memberDao.register(member);
	}

	@Override
	public int deleteByLoginId(String loginId) {
		return memberDao.deleteByLoginId(loginId);
	}

	@Override
	public int update(Member member) {
		return memberDao.update(member);
	}

	@Override
	public List<Member> findMembersReactAnArticle(Integer articleId) {
		return memberDao.findMembersReactAnArticle(articleId);
	}

	@Override
	public Member findByLoginId(String loginId) {
		return memberDao.findByLoginId(loginId);
	}

	@Override
	public Member findByMemberId(Integer memberId) {
		return memberDao.findByMemberId(memberId);
	}

	@Override
	public Integer getRank(Integer memberId) {
		return memberDao.getRank(memberId);
	}
}
