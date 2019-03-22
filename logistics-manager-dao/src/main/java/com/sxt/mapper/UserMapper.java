package com.sxt.mapper;

import com.sxt.pojo.User;
import com.sxt.pojo.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    //自定义的方法：增加一个用户的同时给他赋予角色
    void insertUserIdAndRoleId(Integer userId,Integer roleId);
    
    //自定义方法：根据用户id，查出他所有的角色id
    List<Integer> selectRoleIdByUserId(Integer userId);
    
  //自定义方法：根据用户id，删除他所有的角色id
    void deleteRoleIdByUserId(Integer userId);


}