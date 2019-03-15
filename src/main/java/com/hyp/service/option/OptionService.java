package com.hyp.service.option;

import java.util.List;
import java.util.Map;

import com.hyp.model.OptionsDomain;

/**
 * 

* @Title: OptionService  

* @Description:  网站配置服务层 

* @author HanYupeng  

* @date 2018-06-15 10:32
 */
public interface OptionService {

    /**
     * 删除网站配置
     * @param name
     * @return
     */
    void deleteOptionByName(String name);

    /**
     * 更新网站配置
     * @param name
     * @param value
     * @return
     */
    void updateOptionByName(String name, String value);

    /**
     * 保存网站配置
     * @param options
     */
    void saveOptions(Map<String, String> options);
    /***
     * 根据名称获取网站配置
     * @param name
     * @return
     */
    OptionsDomain getOptionByName(String name);

    /**
     * 获取全部网站配置
     * @return
     */
    List<OptionsDomain> getOptions();
}
