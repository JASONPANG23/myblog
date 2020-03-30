## 个人博客网站
图片1
![myblog1](https://github.com/JASONPANG23/myblog/blob/master/images/myblog1.png)
图片2
![myblog1](https://github.com/JASONPANG23/myblog/blob/master/images/myblog2.png)
图片3
![myblog1](https://github.com/JASONPANG23/myblog/blob/master/images/myblog3.png)
图片4
![myblog1](https://github.com/JASONPANG23/myblog/blob/master/images/myblog4.png)

### 前台
文章展示、分类展示、访客评论
### 后台
管理员认证与授权、博客管理、分类管理、评论管理、图片上传

### 技术选型
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Security](https://spring.io/projects/spring-security)
- [Mybatis](https://mybatis.org/mybatis-3/)
- [Thymeleaf](https://www.thymeleaf.org/)
- [Bootstrap](https://www.bootcss.com/)
- [七牛云](https://www.qiniu.com/)

## 部署
### 依赖
- Git
- JDK
- Maven
- MySQL
### 步骤
- git clone https://github.com/JASONPANG23/myblog.git
- 执行sql文件
- mvn clean compile package -Dmaven.test.skip=true
- 初始用户名密码 admin 123456

## TODO
- 接入github访客的登录
- 加入缓存



