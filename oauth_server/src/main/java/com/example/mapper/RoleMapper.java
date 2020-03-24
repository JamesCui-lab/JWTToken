package com.example.mapper;

import com.example.domain.SysRole;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * @author john
 * @date 2020/1/11 - 20:20
 */
public interface RoleMapper {
    @Select("select r.id,r.role_name roleName ,r.role_desc roleDesc " +
            "FROM sys_role r,sys_user_role ur " +
            "WHERE r.id=ur.rid AND ur.uid=#{uid}")
    public List<SysRole> findByUid(Integer uid);
}
