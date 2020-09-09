package web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.dao.ReactionDao;
import web.service.ReactionService;

@Service
@Transactional
public class ReactionServiceImpl implements ReactionService {
	@Autowired
	ReactionDao reactionDao;
}
