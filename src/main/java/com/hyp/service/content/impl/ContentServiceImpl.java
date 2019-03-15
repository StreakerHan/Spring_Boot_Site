package com.hyp.service.content.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyp.constant.ErrorConstant;
import com.hyp.constant.Types;
import com.hyp.constant.WebConst;
import com.hyp.dao.CommentDao;
import com.hyp.dao.ContentDao;
import com.hyp.dao.RelationShipDao;
import com.hyp.dto.cond.ContentCond;
import com.hyp.dto.cond.MetaCond;
import com.hyp.exception.BusinessException;
import com.hyp.model.CommentDomain;
import com.hyp.model.ContentDomain;
import com.hyp.model.RelationShipDomain;
import com.hyp.model.UserDomain;
import com.hyp.service.content.ContentService;
import com.hyp.service.meta.MetaService;
import com.hyp.service.user.UserService;
import com.hyp.utils.TaleUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 

* @Title: ContentServiceImpl  

* @Description:   文章服务实现层

* @author HanYupeng  

* @date 2018-06-15 10:31
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentDao contentDao;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private MetaService metaService;

    @Autowired
    private RelationShipDao relationShipDao;
    


    @Transactional
    @Override
    public void addArticle(ContentDomain contentDomain, HttpServletRequest request) {
        if (null == contentDomain)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        if (StringUtils.isBlank(contentDomain.getTitle()))
            throw BusinessException.withErrorCode(ErrorConstant.Article.TITLE_CAN_NOT_EMPTY);
        if (contentDomain.getTitle().length() > WebConst.MAX_TITLE_COUNT)
            throw BusinessException.withErrorCode(ErrorConstant.Article.TITLE_IS_TOO_LONG);
        if (StringUtils.isBlank(contentDomain.getContent()))
            throw BusinessException.withErrorCode(ErrorConstant.Article.CONTENT_CAN_NOT_EMPTY);
        if (contentDomain.getContent().length() > WebConst.MAX_TEXT_COUNT)
            throw BusinessException.withErrorCode(ErrorConstant.Article.CONTENT_IS_TOO_LONG);

        //标签和分类
        String tags = contentDomain.getTags();
        String categories = contentDomain.getCategories();
        //从session中获取当前登录用户的id
        UserDomain userInfo = (UserDomain) request.getSession().getAttribute("login_user");
        /*Integer authorId = (Integer) request.getSession().getAttribute("userInfo");*/
        Integer authorId = userInfo.getUid();
        /*System.out.println("*************************" + authorId);*/
        contentDomain.setAuthorId(authorId);
        contentDao.addArticle(contentDomain);

        int cid = contentDomain.getCid();
        metaService.addMetas(cid, tags, Types.TAG.getType());
        metaService.addMetas(cid, categories, Types.CATEGORY.getType());
    }

    @Override
    @Transactional
    public void deleteArticleById(Integer cid) {
        if (null == cid)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        contentDao.deleteArticleById(cid);
        //同时也要删除该文章下的所有评论
        List<CommentDomain> comments = commentDao.getCommentsByCId(cid);
        if (null != comments && comments.size() > 0){
            comments.forEach(comment ->{
                commentDao.deleteComment(comment.getCoid());
            });
        }
        //删除标签和分类关联
        List<RelationShipDomain> relationShips = relationShipDao.getRelationShipByCid(cid);
        if (null != relationShips && relationShips.size() > 0){
            relationShipDao.deleteRelationShipByCid(cid);
        }

    }

    @Override
    @Transactional
    public void updateArticleById(ContentDomain contentDomain) {
        //标签和分类
        String tags = contentDomain.getTags();
        String categories = contentDomain.getCategories();

        contentDao.updateArticleById(contentDomain);
        int cid = contentDomain.getCid();
        relationShipDao.deleteRelationShipByCid(cid);
        metaService.addMetas(cid, tags, Types.TAG.getType());
        metaService.addMetas(cid, categories, Types.CATEGORY.getType());

    }

    @Override
    @Transactional
    public void updateCategory(String ordinal, String newCatefory) {
        ContentCond cond = new ContentCond();
        cond.setCategory(ordinal);
        List<ContentDomain> atricles = contentDao.getArticlesByCond(cond);
        atricles.forEach(atricle -> {
            atricle.setCategories(atricle.getCategories().replace(ordinal, newCatefory));
            contentDao.updateArticleById(atricle);
        });
    }



    @Override
    public void updateContentByCid(ContentDomain content) {
        if (null != content && null != content.getCid()) {
            contentDao.updateArticleById(content);
        }
    }

    @Override
    public ContentDomain getAtricleById(Integer cid) {
        if (null == cid)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        return contentDao.getArticleById(cid);
    }

    @Override
    public PageInfo<ContentDomain> getArticlesByCond(ContentCond contentCond, int pageNum, int pageSize) {
        if (null == contentCond)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        PageHelper.startPage(pageNum, pageSize);
        List<ContentDomain> contents = contentDao.getArticlesByCond(contentCond);
        PageInfo<ContentDomain> pageInfo = new PageInfo<>(contents);
        return pageInfo;
    }

    @Override
    public PageInfo<ContentDomain> getRecentlyArticle(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ContentDomain> recentlyArticle = contentDao.getRecentlyArticle();
        PageInfo<ContentDomain> pageInfo = new PageInfo<>(recentlyArticle);
        return pageInfo;
    }

    @Override
    public PageInfo<ContentDomain> searchArticle(String param, int pageNun, int pageSize) {
        PageHelper.startPage(pageNun,pageSize);
        List<ContentDomain> contentDomains = contentDao.searchArticle(param);
        PageInfo<ContentDomain> pageInfo = new PageInfo<>(contentDomains);
        return pageInfo;
    }
}
