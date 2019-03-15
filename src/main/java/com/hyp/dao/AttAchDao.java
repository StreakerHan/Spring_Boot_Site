package com.hyp.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hyp.dto.AttAchDto;
import com.hyp.model.AttAchDomain;

import java.util.List;

/**
 * 

* @Title: AttAchDao  

* @Description: 附件管理接口  

* @author HanYupeng  

* @date 2018-06-14 16:48
 */
@Mapper
public interface AttAchDao {


    /**
     * 添加单个附件信息
     * @param attAchDomain
     * @return
     */
    int addAttAch(AttAchDomain attAchDomain);

    /**
     * 批量添加附件信息
     * @param list
     * @return
     */
    int batchAddAttAch(List<AttAchDomain> list);

    /**
     * 根据主键编号删除附件信息
     * @param id
     * @return
     */
    int deleteAttAch(int id);

    /**
     * 更新附件信息
     * @param attAchDomain
     * @return
     */
    int updateAttAch(AttAchDomain attAchDomain);

    /**
     * 根据主键获取附件信息
     * @param id
     * @return
     */
    AttAchDto getAttAchById(@Param("id") int id);

    /**
     * 获取所有的附件信息
     * @return
     */
    List<AttAchDto> getAtts();

    /**
     * 查找附件的数量
     * @return
     */
    Long getAttsCount();
}
