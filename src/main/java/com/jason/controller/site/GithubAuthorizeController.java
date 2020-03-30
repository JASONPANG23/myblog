package com.jason.controller.site;

import com.alibaba.fastjson.JSON;
import com.jason.model.dto.GithubUser;
import com.jason.provider.GithubProvider;
import com.jason.model.dto.AccessTokenDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * TODO
 * github 授权
 */
@Controller
@RequestMapping("/github")
@Slf4j
public class GithubAuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;


    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code,
                           @RequestParam("state") String state,
                           @RequestParam("")
                           HttpServletResponse response){

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        String info = JSON.toJSONString(githubUser);

        if (githubUser != null && githubUser.getId() != null) {
            Cookie cookie = new Cookie("githubUser",info);
            cookie.setMaxAge(60 * 60 * 24 * 30 * 6);
            response.addCookie(cookie);
            return "redirect:/";
        } else {
            log.error("callback get github error,{}", githubUser);
            // 登录失败，重新登录
            return "redirect:/";
        }

        //todo
    }
}
