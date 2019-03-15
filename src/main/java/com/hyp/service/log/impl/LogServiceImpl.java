package com.hyp.service.log.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyp.constant.ErrorConstant;
import com.hyp.dao.LogDao;
import com.hyp.exception.BusinessException;
import com.hyp.model.LogDomain;
import com.hyp.service.log.LogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 

* @Title: LogServiceImpl  

* @Description:  用户请求日志

* @author HanYupeng  

* @date 2018-06-15 10:31
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    @Override
    public void addLog(String action, String data, String ip, Integer authorId) {
        LogDomain logDomain = new LogDomain();
        logDomain.setAuthorId(authorId);
        logDomain.setIp(ip);
        logDomain.setData(data);
        logDomain.setAction(action);
        logDao.addLog(logDomain);
    }

    @Override
    public void deleteLogById(Integer id) {
        if (null == id)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        deleteLogById(id);
    }

    @Override
    public PageInfo<LogDomain> getLogs(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<LogDomain> logs = logDao.getLogs();
        PageInfo<LogDomain> pageInfo = new PageInfo<>(logs);
        return pageInfo;
    }
}
