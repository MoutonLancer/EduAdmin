package admin.dao;

import admin.model.PO.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentInfoDao extends BaseMapper<Student> {
}
