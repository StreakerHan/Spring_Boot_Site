package com.hyp.service.attach.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyp.constant.ErrorConstant;
import com.hyp.dao.AttAchDao;
import com.hyp.dto.AttAchDto;
import com.hyp.exception.BusinessException;
import com.hyp.model.AttAchDomain;
import com.hyp.service.attach.AttAchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 

* @Title: AttAchServiceImpl  

* @Description: 附件服务实现层  

* @author HanYupeng  

* @date 2018-06-15 10:30
 */
@Service
public class AttAchServiceImpl implements AttAchService {

    @Autowired
    private AttAchDao attAchDao;

    @Override
    public void addAttAch(AttAchDomain attAchDomain) {
        if (null == attAchDomain)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        attAchDao.addAttAch(attAchDomain);

    }

    @Override
    public void batchAddAttAch(List<AttAchDomain> list) {
        if (null == list || list.size() == 0)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        attAchDao.batchAddAttAch(list);

    }

    @Override
    public void deleteAttAch(Integer id) {
        if (null == id)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        attAchDao.deleteAttAch(id);

    }

    @Override
    public void updateAttAch(AttAchDomain attAchDomain) {
        if (null == attAchDomain || null == attAchDomain.getId())
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        attAchDao.updateAttAch(attAchDomain);

    }

    @Override
    public AttAchDto getAttAchById(Integer id) {
        if (null == id)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        return attAchDao.getAttAchById(id);
    }

    @Override
    public PageInfo<AttAchDto> getAtts(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AttAchDto> atts = attAchDao.getAtts();
        PageInfo<AttAchDto> pageInfo = new PageInfo<>(atts);
        return pageInfo;
    }


}
