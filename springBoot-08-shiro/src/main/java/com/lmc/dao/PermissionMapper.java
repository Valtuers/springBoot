package com.lmc.dao;

import com.lmc.bean.Permission;
import com.lmc.bean.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionMapper {

    @Select("SELECT p.id id,p.name NAME,p.url url FROM role_permission rp LEFT JOIN permission p " +
            "ON rp.permission_id=p.id where role_id=#{roleId}")
    List<Permission> findPermissionByRoleId(@Param("roleId")int id);
}
