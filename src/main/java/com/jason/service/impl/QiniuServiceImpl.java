package com.jason.service.impl;

import com.google.gson.Gson;
import com.jason.config.QiNiuProperties;
import com.jason.service.QiniuService;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class QiniuServiceImpl implements QiniuService {

    private static final Logger logger = LoggerFactory.getLogger(QiniuServiceImpl.class) ;
    @Autowired
    private UploadManager uploadManager ;
    @Autowired
    private BucketManager bucketManager ;
    @Autowired
    private Auth auth ;
    @Autowired
    private QiNiuProperties qiNiuProperties ;

    @Override
    public String uploadFile(FileInputStream fileInputStream, String key) throws QiniuException {
        Response response = uploadFile(fileInputStream,key,false);
        // 解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        //返回上传后文件地址
        return qiNiuProperties.getDomain() + "/" + putRet.key;
    }

    @Override
    public List<FileInfo[]> getFileList() {
        //文件名前缀
        String prefix = "";
        //每次迭代的长度限制，最大1000，推荐值 1000
        int limit = 1000;
        //指定目录分隔符，列出所有公共前缀（模拟列出目录效果）。缺省值为空字符串
        String delimiter = "";
        //列举空间文件列表
        BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(
                qiNiuProperties.getBucketName(),
                prefix,
                limit,
                delimiter);

        List<FileInfo[]> fileInfos = new ArrayList<>() ;
        while (fileListIterator.hasNext()) {
            //处理获取的file list结果
            FileInfo[] items = fileListIterator.next();
            fileInfos.add(items) ;
        }
        return fileInfos;
    }

    private Response uploadFile(FileInputStream file, String key, boolean existed) throws QiniuException {
        Response response ;
        if(existed){
            response = this.uploadManager.put(file,key,getUploadToken(key),null,null) ;
        }else{

            response = this.uploadManager.put(file,key,getUploadToken(),null,null) ;

            //尝试3次
            int retry = 0 ;
            while(response.needRetry() && retry < 3){
                response = this.uploadManager.put(file,key,getUploadToken(),null,null) ;
                retry ++ ;
            }
        }
        return response ;
    }

    /**
     * 获取上传凭证，覆盖上传
     * @param fileName
     * @return
     */
    private String getUploadToken(String fileName){
        return this.auth.uploadToken(qiNiuProperties.getBucketName(),fileName) ;
    }

    /**
     * 获取上传凭证，普通上传
     * @return
     */
    private String getUploadToken(){
        return this.auth.uploadToken(qiNiuProperties.getBucketName()) ;
    }
}
