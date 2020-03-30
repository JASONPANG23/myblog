package com.jason.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 保存用户的一些信息
 */
public class CustomUserDetails extends User {

    private String nickname;
    private com.jason.model.entity.User user ;

    public String getNickname() {
        return nickname;
    }

    public com.jason.model.entity.User getUser(){
        return user;
    }

    public CustomUserDetails(com.jason.model.entity.User user, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.user = user ;
    }

    public CustomUserDetails(com.jason.model.entity.User user, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.user = user ;
    }

}
