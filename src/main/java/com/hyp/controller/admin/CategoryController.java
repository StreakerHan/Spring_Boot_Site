package com.hyp.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.hyp.constant.Types;
import com.hyp.constant.WebConst;
import com.hyp.controller.BaseController;
import com.hyp.dto.MetaDto;
import com.hyp.dto.cond.MetaCond;
import com.hyp.exception.BusinessException;
import com.hyp.model.MetaDomain;
import com.hyp.service.meta.MetaService;
import com.hyp.utils.APIResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 

* @Title: CategoryController  

* @Description: 分类和标签管理控制器

* @author HanYupeng  

* @date 2018-06-14 14:19
 */
@Api("分类和标签")
@Controller
@RequestMapping("admin/category")
public class CategoryController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private MetaService metaService;

    @ApiOperation("进入分类和标签页")
    @GetMapping(value = "")
    public String index(HttpServletRequest request){
        List<MetaDto> categories = metaService.getMetaList(Types.CATEGORY.getType(), null, WebConst.MAX_POSTS);
        List<MetaDto> tags = metaService.getMetaList(Types.TAG.getType(), null, WebConst.MAX_POSTS);
        request.setAttribute("categories", categories);
        request.setAttribute("tags", tags);
        return "admin/category";
    }

    @ApiOperation("保存分类")
    @PostMapping(value = "save")
    @ResponseBody
    public APIResponse addCategory(
            @ApiParam(name = "cname", value = "分类名", required = true)
            @RequestParam(name = "cname", required = true)
            String cname,
            @ApiParam(name = "mid", value = "meta编号", required = false)
            @RequestParam(name = "mid", required = false)
            Integer mid
    ){
        try {
            metaService.saveMeta(Types.CATEGORY.getType(),cname,mid);

        } catch (Exception e) {
            e.printStackTrace();
            String msg = "分类保存失败";
            if (e instanceof BusinessException){
                BusinessException ex = (BusinessException) e;
                msg = ex.getErrorCode();
            }
            LOGGER.error(msg, e);

            return APIResponse.fail(msg);
        }
        return APIResponse.success();
    }

    @ApiOperation("删除分类")
    @PostMapping(value = "delete")
    @ResponseBody
    public APIResponse delete(
            @ApiParam(name = "mid", value = "主键", required = true)
            @RequestParam(name = "mid", required = true)
            Integer mid
    ){
        try {
            metaService.deleteMetaById(mid);

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return APIResponse.fail(e.getMessage());
        }
        return  APIResponse.success();
    }
}
