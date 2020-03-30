package com.jason.controller.admin;

import com.jason.config.QiNiuProperties;
import com.jason.model.entity.AttachInfo;
import com.jason.service.QiniuService;
import com.qiniu.storage.model.FileInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Controller
@RequestMapping("/admin/attach")
public class AdminAttachController {

    @Autowired
    private QiniuService qiniuService ;

    @Autowired
    private QiNiuProperties qiNiuProperties;

    @ResponseBody
    @PostMapping("/image")
    public Map<String, Object> imageUpload(HttpServletRequest request, HttpServletResponse response,
                                           @RequestParam(value = "editormd-image-file", required = false) MultipartFile attach){
        log.info("文件上传");
        Map<String,Object> map = new HashMap<>() ;
        String trueFileName = attach.getOriginalFilename();

        String fileName = trueFileName + System.currentTimeMillis();
        try {

            InputStream inputStream = attach.getInputStream();
            String path = qiniuService.uploadFile((FileInputStream) inputStream,fileName) ;
            path = "http://"+path ;
            log.info("图片上传地址 :{}",path);
            map.put("url",path) ;
            map.put("success",1) ;
            map.put("message","upload success!");

        } catch (Exception e) {
            e.printStackTrace();
            map.put("url","") ;
            map.put("success",0) ;
            map.put("message","upload failed!");
        }
        return map;
    }

    @GetMapping("/images")
    public String listFile(Model model){

        List<AttachInfo> attachInfoList = new ArrayList<>() ;
        List<FileInfo[]> fileList = qiniuService.getFileList();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        for (FileInfo[] fileInfos : fileList) {
            for (FileInfo fileInfo : fileInfos) {
                String key = fileInfo.key ;
                String date = simpleDateFormat.format(new Date(fileInfo.putTime));
                double fileSize = fileInfo.fsize / 1024.0 / 1024.0 ;
                String url = "http://"+qiNiuProperties.getDomain()+"/"+key ;
                AttachInfo attachInfo = new AttachInfo(url,key,date,fileSize) ;
                attachInfoList.add(attachInfo) ;
            }
        }
        System.out.println(attachInfoList.size());

        model.addAttribute("attaches",attachInfoList) ;
        return "admin/attach";
    }


}
