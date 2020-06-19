package com.example.demo.Graduation.controller.SysController;

import com.example.demo.Graduation.Annotation.LogAop;
import com.example.demo.Graduation.entity.Result;
import com.example.demo.Graduation.entity.SysUploadEntity;
import com.example.demo.Graduation.service.SysNoticeService.SysUploadService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
//上传资源管理
@Controller
@RequestMapping("/uploadfile")
public class SysUploadFileController {
    @Value("${upload.path}")
    private String uploadPath;
    @Autowired
    private SysUploadService sysUploadService;
    @Value("${fileurl}")
    private String fileurl;

    @RequestMapping("")
    public String GetUploadList(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, @RequestParam(value = "name", defaultValue = "") String name) {
        PageInfo<SysUploadEntity> sysuploadpage = sysUploadService.FindAllUploadInfo(pageNo, pageSize, name);
        model.addAttribute("sysuploadlist", sysuploadpage);

        model.addAttribute("name", name);
        return "SysUpload/uploadfilelist";
    }


    //上传资源
    @LogAop(value = "上传资源")
    @PostMapping(value = "/uploads")
    @RequiresPermissions("resources:add")
    @ResponseBody
    public Result uploads(@RequestParam("file") MultipartFile file, @RequestParam("name") String name) throws Exception {
        if (file.isEmpty()) {
            return Result.error(0, "文件为空");
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        fileName = UUID.randomUUID().toString() + suffixName;
        String filePath = uploadPath;
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            Result result = sysUploadService.AddQiNiuResource(filePath + fileName, fileName);
            if (result.getCode() == 1) {
                Result resultUpload = sysUploadService.AddUploadReource(name, fileurl + fileName);
                if (resultUpload.getCode() == 1) {
                    return Result.success(1, "上传成功");
                } else {
                    return Result.error(0, "上传失败");
                }
            } else {
                return Result.error(0, "上传失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error(0, "上传失败");
        }
    }


    //跳转到上传资源界面
    @GetMapping(value = "/GetUploads")
    public String GetUploads() {
        return "SysUpload/addupload";
    }

    //删除资源
    @LogAop(value = "删除资源")
    @PostMapping(value = "/DeleteUploads")
    @RequiresPermissions("resources:delete")
    @ResponseBody
    public Result DeleteUploads(@RequestParam("id") String id) {
        Result result = sysUploadService.DeleteResource(id);
        return result;
    }

    //跳转到修改资源界面

    @GetMapping(value = "/GetUpdateUploads")
    public String UpdateUploads(@RequestParam("id") String id, Model model) {
        SysUploadEntity sysUploadEntity = sysUploadService.IdFindUploadInfo(id);
        model.addAttribute("sysUpload", sysUploadEntity);
        return "SysUpload/updateupload";
    }


    //修改资源、
    @LogAop(value = "修改资源")
    @RequiresPermissions("resources:update")
    @PostMapping(value = "/UpdateUploads")
    @ResponseBody
    public Result UpdateUploads(@RequestParam("file") MultipartFile file, @RequestParam("name") String name, @RequestParam("id") String id) throws Exception {
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        fileName = UUID.randomUUID().toString() + suffixName;
        String filePath = uploadPath;
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            Result result = sysUploadService.UpdateResource(id, filePath + fileName, fileName, name);
            if (result.getCode() == 1) {
                return Result.success(1, "修改成功");
            } else {
                return Result.error(0, "修改失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error(0, "修改失败");
        }
    }


}
