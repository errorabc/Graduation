package com.example.demo.Graduation.service.SysNoticeService;

import com.example.demo.Graduation.Dao.SysNoticeDao.SysUploadDao;
import com.example.demo.Graduation.Tool.DateTime;
import com.example.demo.Graduation.Tool.QnUpload;
import com.example.demo.Graduation.entity.Result;
import com.example.demo.Graduation.entity.SysNoticeEntity;
import com.example.demo.Graduation.entity.SysUploadEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class SysUploadService {
    @Value("${AccessKey}")
    private String AccessKey;
    @Value("${SecretKey}")
    private String SecretKey;
    @Value("${bucket}")
    private String bucket;
    @Autowired
    private SysUploadDao sysUploadDao;
    @Autowired
    private QnUpload qnUpload;
    @Value("${upload.path}")
    private String uploadPath;
    @Value("${fileurl}")
    private String fileurl;


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

    //查询资源
    public PageInfo<SysUploadEntity> FindAllUploadInfo(int PageNo, int PageSzie, String name) {
        PageHelper.startPage(PageNo, PageSzie);
        List<SysUploadEntity> uploadlist = sysUploadDao.FindAllUploadInfo(name);
        PageInfo<SysUploadEntity> uploadpagelist = new PageInfo<SysUploadEntity>(uploadlist);
        return uploadpagelist;
    }


    //添加资源到数据库
    public Result AddUploadReource(String name, String url) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        SysUploadEntity sysUploadEntity = new SysUploadEntity();
        sysUploadEntity.setId(UUID.randomUUID().toString());
        sysUploadEntity.setName(name);
        sysUploadEntity.setType("图片");
        sysUploadEntity.setUrl(url);
        sysUploadEntity.setCreatime(DateTime.strToDateLong(df.format(new Date())));
        if (sysUploadDao.AddUploadReource(sysUploadEntity)) {
            return Result.success(1, "添加成功");
        } else {
            return Result.error(0, "添加失败");
        }
    }


    //删除
    public Result DeleteResource(String id) {
        SysUploadEntity sysUploadEntity = sysUploadDao.IdFindUploadInfo(id);
        Result result = qnUpload.DeleteQiNiuResource(id); //删除七牛云图片
        if (result.getCode() == 1) {
            if (sysUploadDao.DeleteUploadReource(id)) {//删除数据库数据
                String key = sysUploadEntity.getUrl().substring(sysUploadEntity.getUrl().lastIndexOf('/'));
                File file = new File(uploadPath + key);
                if (file.exists()) {
                    file.delete();  //删除本地资源
                }
                return Result.success(1, "删除成功");
            } else {
                return Result.error(0, "删除失败");
            }
        } else {
            return Result.error(0, "七牛云删除失败");
        }
    }




    //Id查询信息
    public SysUploadEntity IdFindUploadInfo(String id) {
        SysUploadEntity sysUploadEntity = sysUploadDao.IdFindUploadInfo(id);
        return sysUploadEntity;
    }


    //修改七牛云资源,先删除本地图片，在删除七牛云图片，重新七牛云图片，在修改数据库信息
    public Result UpdateResource(String id, String localFilePath, String filename, String name) throws Exception {
        SysUploadEntity sysUploadEntity = sysUploadDao.IdFindUploadInfo(id);//查询要修改的信息
        String key = sysUploadEntity.getUrl().substring(sysUploadEntity.getUrl().lastIndexOf('/'));
        File file = new File(uploadPath + key);
        if (file.exists()) {
            file.delete();  //删除本地资源
        }
        Result result = qnUpload.DeleteQiNiuResource(id);//删除七牛云图片
        if (result.getCode() == 1) {
            Result addresult = qnUpload.AddQiNiuResource(localFilePath, filename);//添加数据到七牛云
            if (addresult.getCode() == 1) {
                if (sysUploadDao.UpdateUploadReource(id, name, fileurl + filename)) {
                    return Result.success(1, "修改成功");
                } else {
                    return Result.error(0, "修改失败");
                }
            } else {
                return Result.error(0, "添加到七牛云失败");
            }
        } else {
            return Result.error(0, "七牛云数据删除失败");
        }
    }

}
