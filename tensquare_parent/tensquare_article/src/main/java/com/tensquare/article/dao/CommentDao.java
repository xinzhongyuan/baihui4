package com.tensquare.article.dao;

import com.tensquare.article.pojo.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @ Author     ：xzy
 * @ Date       ：Created in 15:26 2019/4/27
 */
public interface CommentDao extends MongoRepository<Comment, String> {
    public List<Comment> findByArticleid(String articleid);
    public void deleteById(String articleid);
}
