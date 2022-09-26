package com.boot.mybatis20220923.repository;

import com.boot.mybatis20220923.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {
    public int save(User user);

}
