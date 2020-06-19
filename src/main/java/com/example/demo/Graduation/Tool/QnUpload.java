package com.example.demo.Graduation.Tool;

import com.example.demo.Graduation.Dao.SysNoticeDao.SysUploadDao;
import com.example.demo.Graduation.entity.Result;
import com.example.demo.Graduation.entity.SysUploadEntity;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class QnUpload {
    @Value("${AccessKey}")
    private String AccessKey;
    @Value("${SecretKey}")
    private String SecretKey;
    @Value("${bucket}")
    private String bucket;
    @Autowired
    private SysUploadDao sysUploadDao;

    //上传资源到七牛云服务器
    public Result AddQiNiuResource(String localFilePath, String filename) throws Exception {
        Configuration cfg = new Configuration(Region.region2());
        UploadManager uploadManager = new UploadManager(cfg);
        String key = filename;
        Auth auth = Auth.create(AccessKey, SecretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            if (putRet.key.equals("")) {
                return Result.error(0, "上传失败");
            } else {
                return Result.success(1, "上传成功");
            }
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
            return Result.error(0, "上传失败");
        }
    }



    //删除七牛云的资源
    public Result DeleteQiNiuResource(String id) {
        SysUploadEntity sysUploadEntity = sysUploadDao.IdFindUploadInfo(id);
        Configuration cfg = new Configuration(Region.region0());
        Auth auth = Auth.create(AccessKey, SecretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        String key = sysUploadEntity.getUrl().substring(sysUploadEntity.getUrl().lastIndexOf('/'));
        try {
            bucketManager.delete(bucket, key.replace("/", ""));
            return Result.success(1, "删除成功");
        } catch (QiniuException ex) {
            return Result.error(0, "删除失败");
        }

    }
}
