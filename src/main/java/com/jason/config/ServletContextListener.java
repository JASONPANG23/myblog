package com.jason.config;

import com.jason.model.entity.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@WebListener
public class ServletContextListener implements javax.servlet.ServletContextListener {

    private final Logger logger = LoggerFactory.getLogger(ServletContextListener.class);
    @Override
    public void contextInitialized(ServletContextEvent sce) {


        InputStream inputStream = ServletContextListener.class.getClassLoader().getResourceAsStream("userInfo.properties");
        logger.info("inputStream :{} ",inputStream);
        Properties properties = new Properties();
        logger.info("properties :{} ",properties);

        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("无法加载配置文件");
        }
        String icon = properties.getProperty("icon");
        String nickname = properties.getProperty("nickname");
        String introduction = properties.getProperty("introduction");
        String blogTitle = properties.getProperty("blog_title");
        String blogSubtitle = properties.getProperty("blog_subtitle");
        String description = properties.getProperty("description");
        String contactMe = properties.getProperty("contact_me");
        String descriptionFooter = properties.getProperty("description_footer");
        String weibo = properties.getProperty("weibo") ;
        String github = properties.getProperty("github");
        String qq = properties.getProperty("qq");
        UserInfo userInfo = new UserInfo(icon, nickname, introduction, blogTitle, blogSubtitle, description,contactMe,descriptionFooter,weibo,github,qq);
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("userInfo",userInfo);
        logger.info("======ServletContext初始化完成======");
    }
}
