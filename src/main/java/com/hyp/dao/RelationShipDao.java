package com.hyp.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hyp.model.RelationShipDomain;

import java.util.List;

/**
 * 

* @Title: RelationShipDao  

* @Description: 中间关系表  

* @author HanYupeng  

* @date 2018-06-15 09:53
 */
@Mapper
public interface RelationShipDao {

    /**
     * 添加
     * @param relationShip
     * @return
     */
    int addRelationShip(RelationShipDomain relationShip);

    /**
     * 根据文章编号和meta编号删除关联
     * @param cid
     * @param mid
     * @return
     */
    int deleteRelationShipById(@Param("cid") Integer cid, @Param("mid") Integer mid);

    /**
     * 根据文章编号删除关联
     * @param cid
     * @return
     */
    int deleteRelationShipByCid(@Param("cid") Integer cid);

    /**
     * 根据meta编号删除关联
     * @param mid
     * @return
     */
    int deleteRelationShipByMid(@Param("mid") Integer mid);

    /**
     * 更新
     * @param relationShip
     * @return
     */
    int updateRelationShip(RelationShipDomain relationShip);

    /**
     * 根据文章主键获取关联
     * @param cid
     * @return
     */
    List<RelationShipDomain> getRelationShipByCid(@Param("cid") Integer cid);

    /**
     * 根据meta编号获取关联
     * @param mid
     * @return
     */
    List<RelationShipDomain> getRelationShipByMid(@Param("mid") Integer mid);

    /**
     * 获取数量
     * @param cid
     * @param mid
     * @return
     */
    Long getCountById(@Param("cid") Integer cid, @Param("mid") Integer mid);
}
