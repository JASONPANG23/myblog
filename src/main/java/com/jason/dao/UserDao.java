package com.jason.dao;

import com.jason.model.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    User getUserByUsername(String username);
}
