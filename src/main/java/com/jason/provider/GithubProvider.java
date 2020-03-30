package com.jason.provider;

import com.alibaba.fastjson.JSON;
import com.jason.model.dto.AccessTokenDTO;
import com.jason.model.dto.GithubUser;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GithubProvider {

    /**
     * 通过code 换取token
     * @param accessTokenDTO
     * @return
     */
    public String getAccessToken(AccessTokenDTO accessTokenDTO){

        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            return string.split("&")[0].split("=")[1];
        } catch (Exception e) {
            log.error("getAccessToken error,{}", accessTokenDTO, e);
        }
        return null;
    }

    /**
     * 通过token换取用户信息
     * @param accessToken
     * @return
     */
    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            return JSON.parseObject(string, GithubUser.class);
        } catch (Exception e) {
            log.error("getUser error,{}", accessToken, e);
        }
        return null;
    }

}
