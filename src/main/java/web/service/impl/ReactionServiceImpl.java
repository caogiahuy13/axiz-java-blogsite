package web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.dao.ReactionDao;
import web.service.ReactionService;

@Service
public class ReactionServiceImpl implements ReactionService {
	@Autowired
	ReactionDao reactionDao;
}
