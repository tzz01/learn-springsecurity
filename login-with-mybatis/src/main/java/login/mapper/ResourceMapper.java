package login.mapper;



import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import login.po.Resource;
import login.po.Role;

import java.util.List;

@Mapper
public interface ResourceMapper {

    /**
     * 查询resource表得到resource_id和pattern，并使用resource_id去资源角色表中获取role_id，并使用role_id查询得到Role对象，
     * 最后再组装成Resource对象。
     * @return List<Resource>
     */
    @Results(id = "AllResourceMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "pattern", property = "pattern"),
            @Result(column = "id", property = "roles", javaType = List.class,
                    many = @Many(select = "login.mapper.ResourceMapper.getResourceRoles", fetchType = FetchType.EAGER))
    })
    @Select("SELECT id, pattern FROM resource")
    List<Resource> getAllResource();

    @Result(column = "name_zw", property = "nameZw")
    @Select("SELECT id, name, name_zw FROM role WHERE id in (SELECT role_id FROM resource_roles WHERE resource_id = #{resourceId})")
    Role getResourceRoles(@Param("resourceId") String resourceId);
}
