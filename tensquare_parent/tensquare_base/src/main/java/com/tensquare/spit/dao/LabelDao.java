package com.tensquare.spit.dao;

import com.tensquare.base.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @ Author     ：xzy
 * @ Date       ：Created in 20:50 2019/4/23
 */
public interface LabelDao extends
        JpaRepository<Label,String>,JpaSpecificationExecutor<Label> {


}
