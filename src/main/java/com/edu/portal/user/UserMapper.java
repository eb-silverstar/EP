package com.edu.portal.user;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    public int cntUsers(Map<String, String> map);
    public List<UserDTO> getUsers(Map<String, String> map);
    public UserDTO getUser(int uno);
    public UserDTO getActiveUser(Map<String, Object> map);
    public UserDTO getLoginUser(Map<String, Object> map);
    public int insertUser(UserDTO user);
    public int updateUser(UserDTO user);
    public int updatePswd(UserDTO user);
    public int updatePswdErr(UserDTO user);
    public int deleteUser(int uno);

}
