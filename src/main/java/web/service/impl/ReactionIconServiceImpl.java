package web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.dao.ReactionIconDao;
import web.service.ReactionIconService;

@Service
public class ReactionIconServiceImpl implements ReactionIconService {
	@Autowired
	ReactionIconDao reactionIconDao;
}
