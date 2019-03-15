package com.hyp.service.log;

import com.github.pagehelper.PageInfo;
import com.hyp.model.LogDomain;

/**
 * 

* @Title: LogService  

* @Description:  用户请求日志 

* @author HanYupeng  

* @date 2018-06-15 10:31
 */
public interface LogService {

    /**
     * 添加
     * @param action
     * @param data
     * @param ip
     * @param authorId
     */
    void addLog(String action, String data, String ip, Integer authorId);

    /**
     * 删除日志
     * @param id
     * @return
     */
    void deleteLogById(Integer id);

    /**
     * 获取日志
     * @return
     */
    PageInfo<LogDomain> getLogs(int pageNum, int pageSize);
}
