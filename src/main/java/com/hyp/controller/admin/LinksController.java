package com.hyp.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.hyp.constant.ErrorConstant;
import com.hyp.constant.Types;
import com.hyp.controller.BaseController;
import com.hyp.dto.cond.MetaCond;
import com.hyp.exception.BusinessException;
import com.hyp.model.MetaDomain;
import com.hyp.service.meta.MetaService;
import com.hyp.utils.APIResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 

* @Title: LinksController  

* @Description: 友情链接管理控制器  

* @author HanYupeng  

* @date 2018-06-14 15:17
 */
@Api("友链")
@Controller
@RequestMapping(value = "admin/links")
public class LinksController extends BaseController {


    private static final Logger LOGGER = LoggerFactory.getLogger(LinksController.class);

    @Autowired
    private MetaService metaService;

    @ApiOperation("友链页面")
    @GetMapping(value = "")
    public String index(HttpServletRequest request) {

        MetaCond metaCond = new MetaCond();
        metaCond.setType(Types.LINK.getType());
        List<MetaDomain> metas = metaService.getMetas(metaCond);
        request.setAttribute("links", metas);
        return "admin/links";
    }

    @ApiOperation("新增友链")
    @PostMapping(value = "save")
    @ResponseBody
    public APIResponse addLink(
            @ApiParam(name = "title", value = "标签", required = true)
            @RequestParam(name = "title", required = true)
            String title,
            @ApiParam(name = "url", value = "链接", required = true)
            @RequestParam(name = "url", required = true)
            String url,
            @ApiParam(name = "logo", value = "logo", required = false)
            @RequestParam(name = "logo", required = false)
            String logo,
            @ApiParam(name = "mid", value = "meta编号", required = false)
            @RequestParam(name = "mid", required = false)
            Integer mid,
            @ApiParam(name = "sort", value = "sort", required = false)
            @RequestParam(name = "sort", required = false, defaultValue = "0")
            int sort
    ){
        try {
            MetaDomain meta = new MetaDomain();
            meta.setName(title);
            meta.setSlug(url);
            meta.setDescription(logo);
            meta.setSort(sort);
            meta.setType(Types.LINK.getType());
            if (null != mid){
                meta.setMid(mid);
                metaService.updateMeta(meta);
            }else {
                metaService.addMeta(meta);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw BusinessException.withErrorCode(ErrorConstant.Meta.ADD_META_FAIL);
        }
        return APIResponse.success();
    }


    @ApiOperation("删除友链")
    @PostMapping(value = "delete")
    @ResponseBody
    public APIResponse delete(
            @ApiParam(name = "mid", value = "meta主键", required = true)
            @RequestParam(name = "mid", required = true)
                    int mid
    ) {
        try {
            metaService.deleteMetaById(mid);
        } catch (Exception e) {
            e.printStackTrace();
            throw BusinessException.withErrorCode(ErrorConstant.Meta.ADD_META_FAIL);
        }
        return APIResponse.success();

    }
}
