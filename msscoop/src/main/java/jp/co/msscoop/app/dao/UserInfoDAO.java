package jp.co.msscoop.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.msscoop.app.dto.UserInfo;


@Mapper
public interface UserInfoDAO {
	UserInfo findById(@Param("username") String username);
	List<UserInfo> findAll();
}
