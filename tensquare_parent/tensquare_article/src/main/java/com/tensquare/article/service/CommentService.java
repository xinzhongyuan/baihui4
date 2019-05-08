package com.tensquare.article.service;

import com.tensquare.article.dao.CommentDao;
import com.tensquare.article.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.List;

/**
 * @ Author     ：xzy
 * @ Date       ：Created in 15:27 2019/4/27
 */

@Service
public class CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private IdWorker idWorker;
    public void add(Comment comment){
        comment.set_id( idWorker.nextId()+"" );
        commentDao.save(comment);
    }

    public List<Comment> findByArticleid(String articleid){
        return commentDao.findByArticleid(articleid);
    }

    public void deletebyid(String articleid){
        commentDao.deleteById(articleid);
    }
}
