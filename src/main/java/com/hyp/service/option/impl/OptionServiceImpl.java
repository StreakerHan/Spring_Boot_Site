package com.hyp.service.option.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyp.constant.ErrorConstant;
import com.hyp.dao.OptionDao;
import com.hyp.exception.BusinessException;
import com.hyp.model.OptionsDomain;
import com.hyp.service.option.OptionService;

import java.util.List;
import java.util.Map;

/**
 * 

* @Title: OptionServiceImpl  

* @Description:  网站配置服务实现层 

* @author HanYupeng  

* @date 2018-06-15 10:32
 */
@Service
public class OptionServiceImpl implements OptionService {

    @Autowired
    private OptionDao optionDao;

    @Override
    public void deleteOptionByName(String name) {
        if(StringUtils.isBlank(name))
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        optionDao.deleteOptionByName(name);

    }

    @Override
    @Transactional
    public void updateOptionByName(String name, String value) {
        if(StringUtils.isBlank(name))
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        OptionsDomain option = new OptionsDomain();
        option.setName(name);
        option.setValue(value);
        optionDao.updateOptionByName(option);

    }

    @Override
    @Transactional
    public void saveOptions(Map<String, String> options) {
        if (null != options && !options.isEmpty()) {
            options.forEach(this::updateOptionByName);
        }
    }

    @Override
    public OptionsDomain getOptionByName(String name) {
        if(StringUtils.isBlank(name))
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        return optionDao.getOptionByName(name);
    }

    @Override
    public List<OptionsDomain> getOptions() {
        return optionDao.getOptions();
    }
}
