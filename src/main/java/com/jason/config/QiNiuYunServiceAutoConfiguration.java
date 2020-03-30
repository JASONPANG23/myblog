package com.jason.config;


import com.jason.service.QiniuService;
import com.jason.service.impl.QiniuServiceImpl;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
// 自动装备配置文件类
@EnableConfigurationProperties(QiNiuProperties.class)
// 自动装配条件为IQiniuService这个存在classpath路径下
@ConditionalOnClass(QiniuService.class)
// 当配置文件中 qinniu = true时 实例化此类，默认为true
@ConditionalOnProperty(prefix = "qiniu",value = "true",matchIfMissing = true)
public class QiNiuYunServiceAutoConfiguration {

    @Autowired
    private QiNiuProperties qiNiuProperties ;

    @Bean
    public com.qiniu.storage.Configuration region(){
        switch (qiNiuProperties.getZone()){
            case "region0": return new com.qiniu.storage.Configuration(Region.region0());
            case "region1": return new com.qiniu.storage.Configuration(Region.region1());
            case "region2": return new com.qiniu.storage.Configuration(Region.region2());
            case "regionNa0": return new com.qiniu.storage.Configuration(Region.regionNa0());
            case "regionAs0": return new com.qiniu.storage.Configuration(Region.regionAs0());
            default:{
                throw new RuntimeException("parameter:zone illegal") ;
            }
        }
    }

    @Bean
    public UploadManager uploadManager(@Qualifier("region") com.qiniu.storage.Configuration region){
        return new UploadManager(region);
    }

    @Bean
    public Auth auth(){
        return Auth.create(qiNiuProperties.getAccessKey(),qiNiuProperties.getSecretKey()) ;
    }

    @Bean
    public BucketManager bucketManager(@Qualifier("auth") Auth auth,
                                       @Qualifier("region") com.qiniu.storage.Configuration region){
        return new BucketManager(auth,region);

    }


}
