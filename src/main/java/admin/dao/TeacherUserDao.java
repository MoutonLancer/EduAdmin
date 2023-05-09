package admin.dao;

import admin.model.PO.TeacherUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherUserDao extends BaseMapper<TeacherUser> {
}
