package com.tensquare.search.controller;

import com.tensquare.search.pojo.Article;
import com.tensquare.search.service.ArticleService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.sql.Statement;

@RestController
@RequestMapping("/search")
@CrossOrigin
public class SearchController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Article article){
        articleService.add(article);
        return new Result(true, StatusCode.OK, "添加成功");
    }


    @RequestMapping(value = "/{keyword}/{page}/{size}",method = RequestMethod.GET)
    public Result search(@PathVariable String keyword,@PathVariable int page,@PathVariable int size){
        Page<Article> page1 = articleService.findByTitleOrContent(keyword, page, size);
        return new Result(true, StatusCode.OK, "查询成功",new PageResult(page1.getTotalElements(),page1.getContent()));
    }

}
