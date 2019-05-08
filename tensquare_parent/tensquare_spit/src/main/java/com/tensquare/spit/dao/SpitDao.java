package com.tensquare.spit.dao;

import com.mongodb.client.MongoDatabase;
import com.tensquare.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @ Author     ：xzy
 * @ Date       ：Created in 20:10 2019/4/26
 */
public  interface  SpitDao extends MongoRepository<Spit,String> {


    public Page<Spit>  findByParentid(String parentid, Pageable pageable);


}
