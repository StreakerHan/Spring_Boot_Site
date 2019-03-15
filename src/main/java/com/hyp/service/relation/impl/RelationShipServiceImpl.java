package com.hyp.service.relation.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyp.dao.RelationShipDao;
import com.hyp.service.relation.RelationShipService;

/**
 * 

* @Title: RelationShipServiceImpl  

* @Description:   关联关系实现层

* @author HanYupeng  

* @date 2018-06-15 10:33
 */
@Service
public class RelationShipServiceImpl implements RelationShipService {

    @Autowired
    private RelationShipDao relationShipDao;


}
