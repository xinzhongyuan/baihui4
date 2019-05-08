package com.tensquare.search.dao;

import com.tensquare.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ArticleDao extends ElasticsearchRepository<Article,String> {

    public Page<Article> findByTitleLikeOrContentLike(String title, String content, Pageable pageable);
}
