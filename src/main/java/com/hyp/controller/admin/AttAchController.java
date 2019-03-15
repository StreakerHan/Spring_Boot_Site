package com.hyp.controller.admin;

import com.github.pagehelper.PageInfo;
import com.hyp.api.QiniuCloudService;
import com.hyp.constant.ErrorConstant;
import com.hyp.constant.Types;
import com.hyp.constant.WebConst;
import com.hyp.dto.AttAchDto;
import com.hyp.exception.BusinessException;
import com.hyp.model.AttAchDomain;
import com.hyp.model.UserDomain;
import com.hyp.service.attach.AttAchService;
import com.hyp.utils.APIResponse;
import com.hyp.utils.Commons;
import com.hyp.utils.TaleUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 

* @Title: AttAchController  

* @Description: 附件控制器  

* @author HanYupeng  

* @date 2018-06-14 14:11
 */
@Api("附件相关接口")
@Controller
@RequestMapping("admin/attach")
public class AttAchController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AttAchController.class);

    public static final String CLASSPATH = TaleUtils.getUplodFilePath();


    @Autowired
    private AttAchService attAchService;



    @ApiOperation("文件管理首页")
    @GetMapping(value = "")
    public String index(
            @ApiParam(name = "page", value = "页数", required = false)
            @RequestParam(name = "page", required = false, defaultValue = "1")
            int page,
            @ApiParam(name = "limit", value = "条数", required = false)
            @RequestParam(name = "limit", required = false, defaultValue = "12")
            int limit,
            HttpServletRequest request
    ){
        PageInfo<AttAchDto> atts = attAchService.getAtts(page, limit);
        request.setAttribute("attachs", atts);
        request.setAttribute(Types.ATTACH_URL.getType(), Commons.site_option(Types.ATTACH_URL.getType(), Commons.site_url()));
        request.setAttribute("max_file_size", WebConst.MAX_FILE_SIZE / 1024);
        return "admin/attach";
    }




    @ApiOperation("markdown文件上传")
    @PostMapping("/uploadfile")
    public void fileUpLoadToTencentCloud(HttpServletRequest request,
                                                HttpServletResponse response,
                                                @ApiParam(name = "editormd-image-file", value = "文件数组", required = true)
                                                @RequestParam(name = "editormd-image-file", required = true)
                                                MultipartFile file){
        //文件上传
        try {
            request.setCharacterEncoding( "utf-8" );
            response.setHeader( "Content-Type" , "text/html" );

            String fileName = TaleUtils.getFileKey(file.getOriginalFilename()).replaceFirst("/","");

            QiniuCloudService.upload(file, fileName);
            AttAchDomain attAch = new AttAchDomain();
            HttpSession session = request.getSession();
            UserDomain sessionUser = (UserDomain) session.getAttribute(WebConst.LOGIN_SESSION_KEY);
            attAch.setAuthorId(sessionUser.getUid());
            attAch.setFtype(TaleUtils.isImage(file.getInputStream()) ? Types.IMAGE.getType() : Types.FILE.getType());
            attAch.setFname(fileName);
            attAch.setFkey(QiniuCloudService.QINIU_UPLOAD_SITE + fileName);
            attAchService.addAttAch(attAch);
            response.getWriter().write( "{\"success\": 1, \"message\":\"上传成功\",\"url\":\"" + attAch.getFkey() + "\"}" );
        } catch (IOException e) {
            e.printStackTrace();
            try {
                response.getWriter().write( "{\"success\":0}" );
            } catch (IOException e1) {
                throw BusinessException.withErrorCode(ErrorConstant.Att.UPLOAD_FILE_FAIL)
                        .withErrorMessageArguments(e.getMessage());
            }
            throw BusinessException.withErrorCode(ErrorConstant.Att.UPLOAD_FILE_FAIL)
                    .withErrorMessageArguments(e.getMessage());
        }
    }

    @ApiOperation("多文件上传")
    @PostMapping(value = "upload")
    @ResponseBody
    public APIResponse filesUploadToCloud(HttpServletRequest request,
                                          HttpServletResponse response,
                                          @ApiParam(name = "file", value = "文件数组", required = true)
                                          @RequestParam(name = "file", required = true)
                                          MultipartFile[] files){
        //文件上传
        try {
            request.setCharacterEncoding( "utf-8" );
            response.setHeader( "Content-Type" , "text/html" );

            for (MultipartFile file : files) {

                String fileName = TaleUtils.getFileKey(file.getOriginalFilename()).replaceFirst("/","");

                QiniuCloudService.upload(file, fileName);
                AttAchDomain attAch = new AttAchDomain();
                HttpSession session = request.getSession();
                UserDomain sessionUser = (UserDomain) session.getAttribute(WebConst.LOGIN_SESSION_KEY);
                attAch.setAuthorId(sessionUser.getUid());
                attAch.setFtype(TaleUtils.isImage(file.getInputStream()) ? Types.IMAGE.getType() : Types.FILE.getType());
                attAch.setFname(fileName);
                attAch.setFkey(QiniuCloudService.QINIU_UPLOAD_SITE + fileName);
                attAchService.addAttAch(attAch);
            }
            return APIResponse.success();
        } catch (IOException e) {
            e.printStackTrace();
            throw BusinessException.withErrorCode(ErrorConstant.Att.UPLOAD_FILE_FAIL)
                    .withErrorMessageArguments(e.getMessage());
        }
    }

    @ApiOperation("删除文件记录")
    @PostMapping(value = "/delete")
    @ResponseBody
    public APIResponse deleteFileInfo(
            @ApiParam(name = "id", value = "文件主键", required = true)
            @RequestParam(name = "id", required = true)
            Integer id,
            HttpServletRequest request
    ){
        try {
            AttAchDto attAch = attAchService.getAttAchById(id);
            if (null == attAch)
                throw BusinessException.withErrorCode(ErrorConstant.Att.DELETE_ATT_FAIL +  ": 文件不存在");
            attAchService.deleteAttAch(id);
            return APIResponse.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw BusinessException.withErrorCode(e.getMessage());
        }
    }
}
