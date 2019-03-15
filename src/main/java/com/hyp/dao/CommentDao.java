package com.hyp.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hyp.dto.cond.CommentCond;
import com.hyp.model.CommentDomain;

import java.util.List;

/**
 * 

* @Title: CommentDao  

* @Description:  评论实体类

* @author HanYupeng  

* @date 2018-06-15 09:50
 */

//添加了@Mapper注解之后这个接口在编译时会生成相应的实现类 
//需要注意的是：这个接口中不可以定义同名的方法，因为会生成相同的id
//也就是说这个接口是不支持重载的

@Mapper
public interface CommentDao {

    /**
     * 新增评论
     * @param commentDomain
     * @return
     */
    int addComment(CommentDomain commentDomain);

    /**
     * 删除评论
     * @param coid
     * @return
     */
    int deleteComment(@Param("coid") Integer coid);

    /**
     * 更新评论的状态
     * @param coid
     * @return
     */
    int updateCommentStatus(@Param("coid") Integer coid, @Param("status") String status);

    /**
     * 获取单条评论
     * @param coid
     * @return
     */
    CommentDomain getCommentById(@Param("coid") Integer coid);
    /**
     * 根据文章编号获取评论列表
     * @param cid
     * @return
     */
    List<CommentDomain> getCommentsByCId(@Param("cid") Integer cid);

    /**
     * 根据条件获取评论列表
     * @param commentCond
     * @return
     */
    List<CommentDomain> getCommentsByCond(CommentCond commentCond);

    /**
     * 获取文章数量
     * @return
     */
    Long getCommentsCount();
}
