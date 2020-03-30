package com.jason.security;

import com.jason.dao.UserDao;
import com.jason.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.switchuser.SwitchUserGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao ;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUserByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }

        List<SimpleGrantedAuthority> authorityList = null ;
        if(user.getRole() != null){
            authorityList = new ArrayList<>();

            authorityList.add(new SimpleGrantedAuthority(user.getRole())) ;
        }
        return new CustomUserDetails(user,username,user.getPassword(),authorityList);
    }

}
