package com.hyp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.hyp.constant.Types;
import com.hyp.constant.WebConst;
import com.hyp.dto.MetaDto;
import com.hyp.dto.cond.ContentCond;
import com.hyp.model.UserDomain;
import com.hyp.service.content.ContentService;
import com.hyp.service.meta.MetaService;
import com.hyp.service.site.SiteService;
import com.hyp.utils.MapCache;
import com.hyp.utils.TaleUtils;

/**
 * 

* @Title: BaseController  

* @Description:   

* @author HanYupeng  

* @date 2018-06-14 12:44
 */
public abstract class BaseController {
	
	@Autowired
	private ContentService contentService;
	
	@Autowired
	private MetaService metaService;
	
	@Autowired
	private SiteService service;
	
	protected MapCache cache = MapCache.single();
	
	public BaseController title(HttpServletRequest request,String title) {
		request.setAttribute("title", title);
		return this;
	}
	
	/**
	 * 获取blog页面需要的公共数据
	 * @param request
	 * @return
	 */
	public BaseController blogBaseData(HttpServletRequest request, ContentCond contentCond) {
		List<MetaDto> links = metaService.getMetaList(Types.LINK.getType(), null, WebConst.MAX_POSTS);
		request.setAttribute("links", links);
		return this;
	}
	
	/**
	 * 获取请求绑定的登陆对象
	 * @param request
	 * @return
	 */
	public UserDomain user(HttpServletRequest request) {
		return TaleUtils.getLoginUser(request);
	}
	public Integer getUid(HttpServletRequest request) {
		return this.user(request).getUid();
	}
	
	/**
     * 数组转字符串
     *
     * @param arr
     * @return
     */
    public String join(String[] arr) {
        StringBuilder ret = new StringBuilder();
        String[] var3 = arr;
        int var4 = arr.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            String item = var3[var5];
            ret.append(',').append(item);
        }

        return ret.length() > 0 ? ret.substring(1) : ret.toString();
    }
} 
