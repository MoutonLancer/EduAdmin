package admin.dao;

import admin.model.PO.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherInfoDao extends BaseMapper<Teacher> {
}
