package com.tensquare.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{




    @Query("SELECT p FROM Problem p WHERE id IN(SELECT problemid FROM PL   WHERE labelid=?1 ) ORDER BY replytime DESC")
    public Page<Problem>  findNewListBylabelid(String labelid, Pageable pageable);


    @Query("select p from Problem p where id in( select problemid from PL  where labelid=?1 ) order by reply desc ")

    public Page<Problem>  findhotListBylabelid(String labelid, Pageable pageable);

    @Query("select p from Problem p where id in( select problemid from PL   where labelid=?1 ) order by createtime desc ")
    public Page<Problem>  findwaitListBylabid(String labelid, Pageable pageable);
	
}
