package web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.dao.ReactionIconDao;
import web.service.ReactionIconService;

@Service
@Transactional
public class ReactionIconServiceImpl implements ReactionIconService {
	@Autowired
	ReactionIconDao reactionIconDao;
}
