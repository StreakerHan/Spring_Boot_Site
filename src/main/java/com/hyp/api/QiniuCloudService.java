package com.hyp.api;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**

* @Title: QiniuCloudService  

* @Description: 七牛云对象存储服务器

* @author HanYupeng  

* @date 2018-06-13 09:25

*/
public class QiniuCloudService {
    //设置好账号的ACCESS_KEY和SECRET_KEY
    private static final String ACCESS_KEY = "G-8vr4DsH4m4EPps2ed33UyhT24FzhfpmlmgcsnR";
    private static final String SECRET_KEY = "aD1REGh_-a2oL6uY1hJnMKhe1MbmFoC8MPc5-Gh0";
    /**
     * 仓库
     */
    private static final String BUCKET = "buckethyp";
    /**
     * 七牛云外网访问地址
     */
    public static final String QINIU_UPLOAD_SITE = "http://paif6r5x5.bkt.clouddn.com/";

    public static String upload(MultipartFile file, String fileName) {

        //构造一个带指定Zone对象的配置类(zone0华东机房)
        Configuration cfg = new Configuration(Zone.zone0());
        //创建上传对象
        UploadManager uploadManager = new UploadManager(cfg);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        //密钥配置
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        String upToken = auth.uploadToken(BUCKET);
        System.out.println(upToken);
        try {
            Response response = null;
            //调用put方法上传
            response = uploadManager.put(file.getInputStream(), fileName, upToken, null, null);

            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
            return putRet.key;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
