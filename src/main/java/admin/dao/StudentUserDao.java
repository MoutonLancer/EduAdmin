package admin.dao;

import admin.domain.StudentUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentUserDao extends BaseMapper<StudentUser> {
}
