package com.hyp.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hyp.model.LogDomain;

import java.util.List;

/**
 * 

* @Title: LogDao  

* @Description: 日志接口类  

* @author HanYupeng  

* @date 2018-06-15 09:51
 */
@Mapper
public interface LogDao {

    /**
     * 添加日志
     * @param logDomain
     * @return
     */
    int addLog(LogDomain logDomain);

    /**
     * 删除日志
     * @param id
     * @return
     */
    int deleteLogById(@Param("id") Integer id);

    /**
     * 获取日志
     * @return
     */
    List<LogDomain> getLogs();
}
