package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import util.IdWorker;

import java.util.Date;
import java.util.List;

/**
 * @ Author     ：xzy
 * @ Date       ：Created in 20:12 2019/4/26
 */

@Service
public class SpitService {
    @Autowired
    private SpitDao spitDao;
    @Autowired
    private IdWorker idWorker;


    /**
     * 增加
     */
   public  void add(Spit spit){
       spit.set_id(idWorker.nextId()+"");
       spit.setPublishtime(new Date());//发布日期
       spit.setVisits(0);//浏览量
       spit.setShare(0);//分享数
       spit.setThumbup(0);//点赞数
       spit.setComment(0);//回复数
       spit.setState("1");//状态
       spitDao.save(spit);
       if(spit.getParentid()!=null && !"".equals(spit.getParentid())){
           Query  query=new Query();
           query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
           Update update=new Update();
           update.inc("comment", 1);
           mongoTemplate.updateFirst(query, update, "spit");
       }

   }
    /**
     * 增加
     */
   public  void  delete(String id){
            spitDao.deleteById(id);
   }
    /**
     * 增加
     */
   public  void  update(Spit spit){
        spitDao.save(spit);
   }
    /**
     * 增加
     */
   public   Spit  findbyid(String id){
       Spit spit = spitDao.findById(id).get();
       return spit;
   }
    /**
     * 增加
     */
   public List<Spit> findAll(){
       List<Spit> all = spitDao.findAll();
       return all;
   }


 /*   public void Page     findByParentid(String parentid,int page,int size){

        PageRequest pagerequest = PageRequest.of(page - 1, size);
        return  spitDao.findByParentId(parentid, pagerequest);
    }*/

    public Page<Spit> findByParentid(String parentid, int page, int size){
        PageRequest pageRequest = PageRequest.of(page-1, size);
        return spitDao.findByParentid(parentid, pageRequest);
    }


    public void updateThumb(String id){
        Spit spit = spitDao.findById(id).get();
        spit.setThumbup(spit.getThumbup()+1);
        spitDao.save(spit);
    }

    @Autowired
    private MongoTemplate mongoTemplate;

    //如何用 mongoTemplate 来进行更新
    public void updateThumbup(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc("thumbup", 1);
        mongoTemplate.updateFirst(query, update, "spit");
    }







}
