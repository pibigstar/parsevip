package com.pibigstar.repository;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.pibigstar.domain.User;

/**
 * 这里只是做一个mybatis的示范
 * @author pibigstar
 *
 */
public interface UserMapper {
	
	@Select("select * from t_user where id=#{id}")
	User getUserById(Integer id);

	@Select("select * from t_user where username=#{username} and password=#{username}")
	User login(@Param("username")String username,@Param("password")String password);
	
}
