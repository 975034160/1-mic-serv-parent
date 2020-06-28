package com.micuser.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.micuser.entity.User;

@Mapper
@Repository
public interface UserMapper {
	
	List<User> findAllUser();
	
	User findByUsername(String username);

	User findByID(int id);
}
