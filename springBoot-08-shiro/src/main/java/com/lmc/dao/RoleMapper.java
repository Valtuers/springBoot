package com.lmc.dao;

import com.lmc.bean.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoleMapper {

    @Select("SELECT ur.role_id id,r.name NAME,r.remark remark FROM user_role ur LEFT JOIN role r ON r.id=ur.role_id WHERE ur.user_id=#{userId}")
    @Results(
            value = {
                    @Result(id = true,property = "id",column = "id"),
                    @Result(property = "name",column = "name"),
                    @Result(property = "remark",column = "remark"),
                    @Result(
                            property = "permissionList",column = "id",many = @Many(select = "com.lmc.dao.PermissionMapper.findPermissionByRoleId",fetchType = FetchType.DEFAULT)
                    ),
            }
    )
    List<Role> findRoleByUserId(@Param("userId")int id);
}
