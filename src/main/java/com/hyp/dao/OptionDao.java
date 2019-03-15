package com.hyp.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hyp.model.OptionsDomain;

import java.util.List;

/**
 * 

* @Title: OptionDao  

* @Description:  网站配置接口类 

* @author HanYupeng  

* @date 2018-06-15 09:53
 */
@Mapper
public interface OptionDao {

    /**
     * 删除网站配置
     * @param name
     * @return
     */
    int deleteOptionByName(@Param("name") String name);

    /**
     * 更新网站配置
     * @param options
     * @return
     */
    int updateOptionByName(OptionsDomain options);

    /***
     * 根据名称获取网站配置
     * @param name
     * @return
     */
    OptionsDomain getOptionByName(@Param("name") String name);

    /**
     * 获取全部网站配置
     * @return
     */
    List<OptionsDomain> getOptions();
}
